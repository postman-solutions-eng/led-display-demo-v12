from flask import Flask, request
from lednamebadge import SimpleTextAndIcons, LedNameBadge
from array import array

import argparse
import threading
import queue
import importlib.util
import os
import base64
import struct
import zlib
import time

app = Flask(__name__)

# All routes live under a versioned base path so agents can pin to a version.
API_PREFIX = '/v1'

# How many real pixels each LED dot becomes in the rendered PNG.
# Bump this for biggg pictures.
ICON_SCALE = 64
ICON_ON_COLOR = (0, 255, 0)
ICON_OFF_COLOR = (0, 0, 0)

# Simple in-process rate limiter: max requests per window (seconds).
RATE_LIMIT_MAX = 60
RATE_LIMIT_WINDOW = 60
RATE_LIMIT_RETRY_AFTER = 30
_rate_lock = threading.Lock()
_rate_hits = []

# Predefined summary messages selectable via the `type` field.
SUMMARY_MESSAGES = {
    'welcome': 'Welcome',
    'status': 'Open LED Badge - Free, hackable, and fun! :star:',
    'alert': 'Alert :HEART:',
    'info': 'Info :star:',
}


def _error(code, message, details=None, status=400):
    """Build a machine-readable error response matching the OpenAPI ErrorResponse schema.

    `code` is a stable, machine-readable identifier agents can branch on; `error`
    is the human-readable message; `details` is optional extra context.
    """
    body = {'code': code, 'error': message}
    if details is not None:
        body['details'] = details
    return body, status


def _colon_hint(text):
    """Build a corrective hint when text contains colons, which are icon-code delimiters.

    The rule: every literal colon must be escaped as '::'; a single-colon ':name:'
    sequence is only valid when `name` is a recognized icon. Guide the caller toward
    escaping rather than leaving them to guess (and wrongly strip spaces).
    """
    if isinstance(text, str) and ':' in text:
        return (" Hint: double EVERY literal colon to '::' — including after a word in prose "
                "(e.g. 'today:' -> 'today::'), which is the most commonly missed case, and in "
                "times ('16:00' -> '16::00'). A single-colon ':name:' is only valid when name is "
                "a recognized icon code (see /v1/predefined-icons). Do not strip spaces.")
    return ""


def _rate_limited():
    """Return True if the current request exceeds the rate limit window."""
    now = time.monotonic()
    with _rate_lock:
        cutoff = now - RATE_LIMIT_WINDOW
        while _rate_hits and _rate_hits[0] < cutoff:
            _rate_hits.pop(0)
        if len(_rate_hits) >= RATE_LIMIT_MAX:
            return True
        _rate_hits.append(now)
        return False


def _rate_snapshot():
    """Return (limit, remaining, reset_seconds) for the current window without mutating it."""
    now = time.monotonic()
    with _rate_lock:
        cutoff = now - RATE_LIMIT_WINDOW
        active = [t for t in _rate_hits if t >= cutoff]
        remaining = max(0, RATE_LIMIT_MAX - len(active))
        reset = int(active[0] + RATE_LIMIT_WINDOW - now) if active else RATE_LIMIT_WINDOW
        return RATE_LIMIT_MAX, remaining, max(0, reset)


@app.after_request
def _add_rate_limit_headers(response):
    """Expose the rate-limit budget on every response so agents can self-throttle."""
    limit, remaining, reset = _rate_snapshot()
    response.headers['X-RateLimit-Limit'] = str(limit)
    response.headers['X-RateLimit-Remaining'] = str(remaining)
    response.headers['X-RateLimit-Reset'] = str(reset)
    return response


def _too_many_requests():
    body, status = _error('RATE_LIMITED', 'Too many requests',
                          f'Retry after {RATE_LIMIT_RETRY_AFTER} seconds', status=429)
    return body, status, {'Retry-After': str(RATE_LIMIT_RETRY_AFTER)}


def _icon_to_png_base64(data, cols, scale=ICON_SCALE):
    """Render an 11-row LED icon bitmap to a scaled-up PNG data URI.

    Icon bitmaps store `cols` byte-columns of 11 bytes each; each byte is a
    horizontal strip of 8 pixels with the most significant bit on the left.
    """
    rows = 11
    width = 8 * cols
    out_width = width * scale
    out_height = rows * scale

    # Build raw RGB scanlines (each prefixed by a 0 filter byte).
    raw = bytearray()
    for y in range(rows):
        line = bytearray()
        for x in range(width):
            block = x // 8
            bit = x % 8
            index = block * rows + y
            byte = data[index] if index < len(data) else 0
            color = ICON_ON_COLOR if (byte & (0x80 >> bit)) else ICON_OFF_COLOR
            line.extend(bytes(color) * scale)
        for _ in range(scale):
            raw.append(0)
            raw.extend(line)

    def _chunk(tag, body):
        return (struct.pack('>I', len(body)) + tag + body
                + struct.pack('>I', zlib.crc32(tag + body) & 0xffffffff))

    ihdr = struct.pack('>IIBBBBB', out_width, out_height, 8, 2, 0, 0, 0)
    png = (b'\x89PNG\r\n\x1a\n'
           + _chunk(b'IHDR', ihdr)
           + _chunk(b'IDAT', zlib.compress(bytes(raw), 9))
           + _chunk(b'IEND', b''))

    return 'data:image/png;base64,' + base64.b64encode(png).decode('ascii')


def _process_and_write(text, command_queue=None, write_hardware=True):
    """Create the scene buffer for `text` and either write to hardware,
    post to the mock console output via `command_queue`, or both depending on flags.
    """
    creator = SimpleTextAndIcons()
    scene_bitmap = creator.bitmap(text)

    buf = array('B')
    buf.extend(LedNameBadge.header([scene_bitmap[1]], [4], [0], [0], [0], 100))
    buf.extend(scene_bitmap[0])

    if write_hardware:
        try:
            LedNameBadge.write(buf)
        except Exception as e:
            print(f"_process_and_write: hardware write failed: {e}")

    if command_queue is not None:
        # API mock expects only `text` updates
        try:
            command_queue.put({'type': 'update', 'data': {'text': text}})
        except Exception as e:
            print(f"_process_and_write: enqueue failed: {e}")


@app.route(f'{API_PREFIX}/display-text', methods=['POST'])
def display_text():
    if _rate_limited():
        return _too_many_requests()

    data = request.get_json(silent=True)
    if not isinstance(data, dict) or not isinstance(data.get('text'), str) or data.get('text') == '':
        return _error('INVALID_INPUT', 'Invalid display string format',
                      "Field 'text' is required and must be a non-empty string")

    text = data['text']

    try:
        # Validate and prepare scene; actual write/mocking handled in main
        creator = SimpleTextAndIcons()
        creator.bitmap(text)
    except (KeyError, ValueError, FileNotFoundError, OSError) as e:
        return _error('UNSUPPORTED_CHARACTER', 'Invalid display string format', str(e) + _colon_hint(text))
    except Exception as e:
        return _error('INVALID_INPUT', 'Invalid display string format', str(e) + _colon_hint(text))

    # On actual run, the main program will decide whether to write to
    # hardware and/or the mock console by providing globals.
    global _API_COMMAND_QUEUE, _API_WRITE_HARDWARE
    try:
        _process_and_write(text, command_queue=globals().get('_API_COMMAND_QUEUE'), write_hardware=globals().get('_API_WRITE_HARDWARE', True))
    except Exception as e:
        return _error('INTERNAL_ERROR', 'Failed to write to LED device', str(e), status=500)

    return {'status': 'Text displayed on LED', 'text': text}, 200


@app.route(f'{API_PREFIX}/health', methods=['GET'])
def health():
    """Lightweight liveness probe so agents can verify the server is up."""
    return {'status': 'ok'}, 200


def _truthy(value):
    """Interpret a query-string flag as a boolean."""
    return str(value).lower() in ('true', '1', 'yes', 'on')


@app.route(f'{API_PREFIX}/predefined-icons', methods=['GET'])
def get_predefined_icons():
    if _rate_limited():
        return _too_many_requests()

    # Base64 PNG previews are large; only render them when explicitly requested
    # so the default response stays small (saves tokens for agent consumers).
    include_images = _truthy(request.args.get('includeImages', 'false'))

    try:
        creator = SimpleTextAndIcons()
        icons = []
        for name, (data, cols, _ctrl) in creator.bitmap_named.items():
            entry = {
                'icon': f':{name}:',
                'name': name,
            }
            if include_images:
                entry['image'] = _icon_to_png_base64(data, cols)
            icons.append(entry)
    except Exception as e:
        return _error('INTERNAL_ERROR', 'Failed to enumerate icons', str(e), status=500)

    # The icon list is static, so allow clients to cache it for an hour.
    return {'icons': icons, 'total': len(icons)}, 200, {'Cache-Control': 'public, max-age=3600'}


@app.route(f'{API_PREFIX}/display-summary', methods=['POST'])
def display_summary():
    if _rate_limited():
        return _too_many_requests()

    data = request.get_json(silent=True) or {}
    summary_type = data.get('type')
    custom_text = data.get('customText')

    if summary_type not in SUMMARY_MESSAGES:
        return _error('INVALID_INPUT', 'Invalid summary type',
                      f"Field 'type' must be one of: {', '.join(SUMMARY_MESSAGES)}")

    if custom_text is not None and not isinstance(custom_text, str):
        return _error('INVALID_INPUT', 'Invalid custom text',
                      "Field 'customText' must be a string when provided")

    summary = SUMMARY_MESSAGES[summary_type]
    if custom_text:
        summary = f'{summary} {custom_text}'

    try:
        creator = SimpleTextAndIcons()
        creator.bitmap(summary)
    except (KeyError, ValueError, FileNotFoundError, OSError) as e:
        return _error('UNSUPPORTED_CHARACTER', 'Invalid display string format', str(e) + _colon_hint(summary))

    global _API_COMMAND_QUEUE, _API_WRITE_HARDWARE
    try:
        _process_and_write(summary, command_queue=globals().get('_API_COMMAND_QUEUE'), write_hardware=globals().get('_API_WRITE_HARDWARE', True))
    except Exception as e:
        return _error('INTERNAL_ERROR', 'Failed to write to LED device', str(e), status=500)

    return {'status': 'Summary displayed on LED', 'text': summary}, 200


def _load_mock_console_module():
    """Dynamically load `mock-led-display.py` as module `mock_console`.
    This avoids Python import issues with hyphens in the filename.
    """
    base = os.path.dirname(os.path.abspath(__file__))
    path = os.path.join(base, 'mock-led-display.py')
    if not os.path.exists(path):
        raise FileNotFoundError(path)
    spec = importlib.util.spec_from_file_location('mock_console', path)
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    return module


def main():
    parser = argparse.ArgumentParser(description='LED Name Badge API server (AI Ready)')
    parser.add_argument('--host', default='0.0.0.0')
    parser.add_argument('--port', type=int, default=5002)
    parser.add_argument('--mock', action='store_true', help='Run with mock console instead of writing to hardware')
    parser.add_argument('--both', action='store_true', help='Write to hardware and also show mock console')
    args = parser.parse_args()

    # Decide behavior
    use_mock = args.mock or args.both
    write_hardware = not args.mock

    if use_mock:
        # Prepare shared state and command queue for the console mock
        cmd_q = queue.Queue()
        globals()['_API_COMMAND_QUEUE'] = cmd_q
        globals()['_API_WRITE_HARDWARE'] = args.both

        # Start Flask server in background thread, then run console in foreground
        server_thread = threading.Thread(
            target=app.run,
            kwargs={'host': args.host, 'port': args.port, 'threaded': True, 'use_reloader': False},
            daemon=True,
        )
        server_thread.start()

        # Load and run console (this will block in the main thread)
        mock_console = _load_mock_console_module()
        # mock_console.run_mock accepts display_state and command_queue optionally; pass the queue
        mock_console.run_mock(display_state=None, command_queue=cmd_q)
    else:
        # Default: run API server and write to hardware as before
        globals()['_API_WRITE_HARDWARE'] = True
        app.run(host=args.host, port=args.port)


if __name__ == '__main__':
    main()

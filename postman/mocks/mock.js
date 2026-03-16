const http = require('http');

const server = http.createServer((req, res) => {
  const { method, url } = req;

  // @endpoint POST /display-text
  if (method === 'POST' && url === '/display-text') {
    let body = '';
    req.on('data', chunk => (body += chunk));
    req.on('end', () => {
      let text = 'Hello World! :star:';
      try {
        const parsed = JSON.parse(body);
        if (parsed.text) text = parsed.text;
      } catch (e) {}
      res.writeHead(200, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify({ status: 'Text displayed on LED', text: text }));
    });
    return;
  }

  // @endpoint GET /predefined-icons
  if (method === 'GET' && url === '/predefined-icons') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({
      icons: [
        ':ball:', ':happy:', ':happy2:', ':heart:', ':HEART:',
        ':heart2:', ':HEART2:', ':fablab:', ':bicycle:', ':bicycle_r:',
        ':owncloud:', ':octocat:', ':smile:', ':star:', ':sun:'
      ]
    }));
    return;
  }

  // @endpoint POST /display-summary
  if (method === 'POST' && url === '/display-summary') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({ status: 'Summary displayed on LED' }));
    return;
  }

  // Fallback for unmocked routes
  res.writeHead(404, { 'Content-Type': 'application/json' });
  res.end(JSON.stringify({ error: 'Mock route not defined', method, url }));
});

const PORT = process.env.PORT || 4500;
server.listen(PORT, () => console.log('Mock server running on port ' + PORT));

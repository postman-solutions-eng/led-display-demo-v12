from typing import Awaitable, Optional, Union
from .utils.to_async import to_async
from ..display_text import DisplayTextService
from ...net.sdk_config import SdkConfig
from ...models import CreateDisplayTextOkResponse, CreateDisplayTextRequest


class DisplayTextServiceAsync(DisplayTextService):
    """
    Async Wrapper for DisplayTextServiceAsync
    """

    def create_display_text(
        self,
        request_body: CreateDisplayTextRequest = None,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[CreateDisplayTextOkResponse]:
        return to_async(super().create_display_text)(
            request_body, request_config=request_config
        )

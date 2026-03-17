from typing import Awaitable, Optional
from .utils.to_async import to_async
from ..display_summary import DisplaySummaryService
from ...net.sdk_config import SdkConfig
from ...models import CreateDisplaySummaryOkResponse, CreateDisplaySummaryRequest


class DisplaySummaryServiceAsync(DisplaySummaryService):
    """
    Async Wrapper for DisplaySummaryServiceAsync
    """

    def create_display_summary(
        self,
        request_body: CreateDisplaySummaryRequest = None,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[CreateDisplaySummaryOkResponse]:
        return to_async(super().create_display_summary)(
            request_body, request_config=request_config
        )

from typing import Awaitable, Optional, Union
from .utils.to_async import to_async
from ..predefined_icons import PredefinedIconsService
from ...net.sdk_config import SdkConfig
from ...models import GetPredefinedIconsOkResponse


class PredefinedIconsServiceAsync(PredefinedIconsService):
    """
    Async Wrapper for PredefinedIconsServiceAsync
    """

    def get_predefined_icons(
        self, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[GetPredefinedIconsOkResponse]:
        return to_async(super().get_predefined_icons)(request_config=request_config)

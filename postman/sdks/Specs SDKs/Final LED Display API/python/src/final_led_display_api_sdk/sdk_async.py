from typing import Union
from .net.environment import Environment
from .sdk import FinalLedDisplayApiSdk
from .services.async_.display_text import DisplayTextServiceAsync
from .services.async_.predefined_icons import PredefinedIconsServiceAsync
from .services.async_.display_summary import DisplaySummaryServiceAsync


class FinalLedDisplayApiSdkAsync(FinalLedDisplayApiSdk):
    """
    FinalLedDisplayApiSdkAsync is the asynchronous version of the FinalLedDisplayApiSdk SDK Client.
    """

    def __init__(
        self,
        api_key: str = None,
        api_key_header: str = "X-API-KEY",
        base_url: Union[Environment, str, None] = None,
        timeout: int = 60000,
    ):
        super().__init__(
            api_key=api_key,
            api_key_header=api_key_header,
            base_url=base_url,
            timeout=timeout,
        )

        self.display_text = DisplayTextServiceAsync(base_url=self._base_url)
        self.predefined_icons = PredefinedIconsServiceAsync(base_url=self._base_url)
        self.display_summary = DisplaySummaryServiceAsync(base_url=self._base_url)

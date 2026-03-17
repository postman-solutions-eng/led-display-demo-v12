from typing import Union
from .services.display_text import DisplayTextService
from .services.predefined_icons import PredefinedIconsService
from .services.display_summary import DisplaySummaryService
from .net.environment import Environment


class FinalLedDisplayApiSdk:
    """
    Main SDK client class for FinalLedDisplayApiSdk.
    Provides centralized configuration and access to all service endpoints.
    Supports authentication, environment management, and global timeout settings.
    """

    def __init__(
        self,
        api_key: str = None,
        api_key_header: str = "X-API-KEY",
        base_url: Union[Environment, str, None] = None,
        timeout: int = 60000,
    ):
        """
        Initializes FinalLedDisplayApiSdk the SDK class.
        """

        self._base_url = (
            base_url.value if isinstance(base_url, Environment) else base_url
        )
        self.display_text = DisplayTextService(base_url=self._base_url)
        self.predefined_icons = PredefinedIconsService(base_url=self._base_url)
        self.display_summary = DisplaySummaryService(base_url=self._base_url)
        self.set_api_key(api_key, api_key_header)
        self.set_timeout(timeout)

    def set_base_url(self, base_url: Union[Environment, str]):
        """
        Sets the base URL for the entire SDK.

        :param Union[Environment, str] base_url: The base URL to be set.
        :return: The SDK instance.
        """
        self._base_url = (
            base_url.value if isinstance(base_url, Environment) else base_url
        )

        self.display_text.set_base_url(self._base_url)
        self.predefined_icons.set_base_url(self._base_url)
        self.display_summary.set_base_url(self._base_url)

        return self

    def set_api_key(self, api_key: str, api_key_header="X-API-KEY"):
        """
        Sets the api key and the api key header for the entire SDK.
        """
        self.display_text.set_api_key(api_key, api_key_header)
        self.predefined_icons.set_api_key(api_key, api_key_header)
        self.display_summary.set_api_key(api_key, api_key_header)

        return self

    def set_timeout(self, timeout: int):
        """
        Sets the timeout for the entire SDK.

        :param int timeout: The timeout (ms) to be set.
        :return: The SDK instance.
        """
        self.display_text.set_timeout(timeout)
        self.predefined_icons.set_timeout(timeout)
        self.display_summary.set_timeout(timeout)

        return self


# c029837e0e474b76bc487506e8799df5e3335891efe4fb02bda7a1441840310c

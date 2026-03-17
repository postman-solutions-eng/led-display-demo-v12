from typing import Any, Optional, Union
from .utils.validator import Validator
from .utils.base_service import BaseService
from ..net.transport.serializer import Serializer
from ..net.sdk_config import SdkConfig
from ..net.environment.environment import Environment
from ..models.utils.cast_models import cast_models
from ..models import ErrorResponse, GetPredefinedIconsOkResponse


class PredefinedIconsService(BaseService):
    """
    Service class for PredefinedIconsService operations.
    Provides methods to interact with PredefinedIconsService-related API endpoints.
    Inherits common functionality from BaseService including authentication and request handling.
    """

    def __init__(self, *args, **kwargs):
        """Initialize the service and method-level configurations."""
        super().__init__(*args, **kwargs)
        self._get_predefined_icons_config: SdkConfig = {}

    def set_get_predefined_icons_config(self, config: SdkConfig):
        """
        Sets method-level configuration for get_predefined_icons.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._get_predefined_icons_config = config
        return self

    @cast_models
    def get_predefined_icons(
        self, *, request_config: Optional[SdkConfig] = None
    ) -> GetPredefinedIconsOkResponse:
        """Returns a list of all available icon codes that can be used in display text. Icons are returned as simple string codes in the `:icon_name:` format.

        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: GetPredefinedIconsOkResponse
        """

        resolved_config = self._get_resolved_config(
            self._get_predefined_icons_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{resolved_config.get('base_url') or self.base_url or Environment.DEFAULT.url}/predefined-icons",
                [self.get_api_key(resolved_config)],
                resolved_config,
            )
            .add_error(500, ErrorResponse)
            .serialize()
            .set_method("GET")
        )

        response, status, _ = self.send_request(serialized_request)
        return GetPredefinedIconsOkResponse.model_validate(response)

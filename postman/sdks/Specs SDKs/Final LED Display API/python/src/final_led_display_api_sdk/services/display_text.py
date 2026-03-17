from typing import Any, Optional, Union
from .utils.validator import Validator
from .utils.base_service import BaseService
from ..net.transport.serializer import Serializer
from ..net.sdk_config import SdkConfig
from ..net.environment.environment import Environment
from ..models.utils.cast_models import cast_models
from ..models import (
    CreateDisplayTextOkResponse,
    CreateDisplayTextRequest,
    ErrorResponse,
)


class DisplayTextService(BaseService):
    """
    Service class for DisplayTextService operations.
    Provides methods to interact with DisplayTextService-related API endpoints.
    Inherits common functionality from BaseService including authentication and request handling.
    """

    def __init__(self, *args, **kwargs):
        """Initialize the service and method-level configurations."""
        super().__init__(*args, **kwargs)
        self._create_display_text_config: SdkConfig = {}

    def set_create_display_text_config(self, config: SdkConfig):
        """
        Sets method-level configuration for create_display_text.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._create_display_text_config = config
        return self

    @cast_models
    def create_display_text(
        self,
        request_body: CreateDisplayTextRequest = None,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> CreateDisplayTextOkResponse:
        """Updates the text and visual content displayed on the LED name badge. Accepts text and icon codes in the format `:icon_name:`. <img src="https://content.pstmn.io/05b1ef2c-9fd3-4f0e-8b2f-76998fb3f6e5/aW1hZ2UucG5n" width="240"> ## Supported Icons The following icon codes can be embedded in text using the `:icon_name:` syntax: - `:ball:` - Filled circle - `:happy:` - Simple smiley face - `:happy2:` - Larger smiley (2 columns wide) - `:heart:` - Outline heart - `:HEART:` - Filled heart - `:heart2:` - Larger outline heart (2 columns wide) - `:HEART2:` - Larger filled heart (2 columns wide) - `:fablab:` - FabLab logo - `:bicycle:` - Bicycle icon (3 columns wide) - `:bicycle_r:` - Bicycle facing right (3 columns wide) - `:owncloud:` - OwnCloud logo (3 columns wide) - `:octocat:` - GitHub Octocat - `:smile:` - Smile emoji - `:star:` - Star icon - `:sun:` - Sun icon ## Character Restrictions **Supported characters:** - Letters: A-Z, a-z - Numbers: 0-9 - Special characters: `^ !"$%&/()=?` °}\\]\\[{@ \\~ |<>,;.:-_#'+_\\` - German umlauts: äöüÄÖÜß - French/European accents: àäòöùüèéêëôöûîïÿçÀÅÄÉÈÊËÖÔÜÛÙŸŠ **NOT supported:** - Unicode emoji (e.g., 🌍) - will return a 400 error

        :param request_body: The request body., defaults to None
        :type request_body: CreateDisplayTextRequest, optional
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: CreateDisplayTextOkResponse
        """

        Validator(CreateDisplayTextRequest).is_optional().validate(request_body)

        resolved_config = self._get_resolved_config(
            self._create_display_text_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{resolved_config.get('base_url') or self.base_url or Environment.DEFAULT.url}/display-text",
                [self.get_api_key(resolved_config)],
                resolved_config,
            )
            .add_error(400, ErrorResponse)
            .add_error(500, ErrorResponse)
            .serialize()
            .set_method("POST")
            .set_body(request_body)
        )

        response, status, _ = self.send_request(serialized_request)
        return CreateDisplayTextOkResponse.model_validate(response)

from typing import Any, Optional
from .utils.validator import Validator
from .utils.base_service import BaseService
from ..net.transport.serializer import Serializer
from ..net.sdk_config import SdkConfig
from ..net.environment.environment import Environment
from ..models.utils.cast_models import cast_models
from ..models import CreateDisplaySummaryOkResponse, CreateDisplaySummaryRequest


class DisplaySummaryService(BaseService):
    """
    Service class for DisplaySummaryService operations.
    Provides methods to interact with DisplaySummaryService-related API endpoints.
    Inherits common functionality from BaseService including authentication and request handling.
    """

    def __init__(self, *args, **kwargs):
        """Initialize the service and method-level configurations."""
        super().__init__(*args, **kwargs)
        self._create_display_summary_config: SdkConfig = {}

    def set_create_display_summary_config(self, config: SdkConfig):
        """
        Sets method-level configuration for create_display_summary.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._create_display_summary_config = config
        return self

    @cast_models
    def create_display_summary(
        self,
        request_body: CreateDisplaySummaryRequest = None,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> CreateDisplaySummaryOkResponse:
        """Displays a predefined summary message on the LED badge. This endpoint provides a quick way to show common status messages without constructing custom text. Note: The API does not validate the type parameter and will return success for any type value provided.

        :param request_body: The request body., defaults to None
        :type request_body: CreateDisplaySummaryRequest, optional
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: CreateDisplaySummaryOkResponse
        """

        Validator(CreateDisplaySummaryRequest).is_optional().validate(request_body)

        resolved_config = self._get_resolved_config(
            self._create_display_summary_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{resolved_config.get('base_url') or self.base_url or Environment.DEFAULT.url}/display-summary",
                [self.get_api_key(resolved_config)],
                resolved_config,
            )
            .serialize()
            .set_method("POST")
            .set_body(request_body)
        )

        response, _, _ = self.send_request(serialized_request)
        return CreateDisplaySummaryOkResponse.model_validate(response)

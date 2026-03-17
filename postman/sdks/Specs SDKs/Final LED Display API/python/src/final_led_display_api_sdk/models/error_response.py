from pydantic import Field
from typing import Optional
from .utils.base_error import BaseError
from .utils.base_model import BaseModel


# Pydantic validation model for ErrorResponse
class ErrorResponseData(BaseModel):
    """ErrorResponse

    :param error: error, defaults to None
    :type error: str, optional
    :param details: details, defaults to None
    :type details: str, optional
    """

    error: Optional[str] = Field(default=None)
    details: Optional[str] = Field(default=None)


# Error exception class
class ErrorResponse(BaseError):
    """ErrorResponse

    :param error: error, defaults to None
    :type error: str, optional
    :param details: details, defaults to None
    :type details: str, optional
    """

    _model_class = ErrorResponseData

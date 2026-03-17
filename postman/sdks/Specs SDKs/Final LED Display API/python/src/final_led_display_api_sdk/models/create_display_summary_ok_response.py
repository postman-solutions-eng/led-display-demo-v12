from pydantic import Field
from typing import Optional
from .utils.base_model import BaseModel


class CreateDisplaySummaryOkResponse(BaseModel):
    """CreateDisplaySummaryOkResponse

    :param status: Status of the operation, defaults to None
    :type status: str, optional
    """

    status: Optional[str] = Field(default=None, description="Status of the operation")

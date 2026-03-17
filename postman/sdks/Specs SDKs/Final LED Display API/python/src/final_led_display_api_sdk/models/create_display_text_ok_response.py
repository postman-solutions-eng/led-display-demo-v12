from pydantic import Field
from typing import Optional
from .utils.base_model import BaseModel


class CreateDisplayTextOkResponse(BaseModel):
    """CreateDisplayTextOkResponse

    :param status: status, defaults to None
    :type status: str, optional
    :param text: text, defaults to None
    :type text: str, optional
    """

    status: Optional[str] = Field(default=None)
    text: Optional[str] = Field(default=None)

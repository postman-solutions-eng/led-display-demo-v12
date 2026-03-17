from pydantic import Field
from typing import Optional
from .utils.base_model import BaseModel


class CreateDisplayTextRequest(BaseModel):
    """CreateDisplayTextRequest

    :param text: The text to display, optionally including icon codes in icon_name format, defaults to None
    :type text: str, optional
    """

    text: Optional[str] = Field(
        default=None,
        description="The text to display, optionally including icon codes in icon_name format",
    )

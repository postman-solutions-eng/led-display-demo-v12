from pydantic import Field
from typing import Optional
from .utils.base_model import BaseModel


class CreateDisplaySummaryRequest(BaseModel):
    """CreateDisplaySummaryRequest

    :param type_: The type of summary message to display (e.g., welcome, status, alert, info), defaults to None
    :type type_: str, optional
    :param custom_text: Optional custom text to append to the summary, defaults to None
    :type custom_text: str, optional
    """

    type_: Optional[str] = Field(
        alias="type",
        serialization_alias="type",
        default=None,
        description="The type of summary message to display (e.g., welcome, status, alert, info)",
    )
    custom_text: Optional[str] = Field(
        alias="customText",
        serialization_alias="customText",
        default=None,
        description="Optional custom text to append to the summary",
    )

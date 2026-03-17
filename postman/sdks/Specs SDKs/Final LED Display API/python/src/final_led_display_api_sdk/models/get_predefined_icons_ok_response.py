from typing import List
from pydantic import Field
from typing import Optional
from .utils.base_model import BaseModel


class GetPredefinedIconsOkResponse(BaseModel):
    """GetPredefinedIconsOkResponse

    :param icons: Array of icon codes in :icon_name format, defaults to None
    :type icons: List[str], optional
    """

    icons: Optional[List[str]] = Field(
        default=None, description="Array of icon codes in :icon_name format"
    )

# PredefinedIconsService

A list of all methods in the `PredefinedIconsService` service. Click on the method name to view detailed information about that method.

| Methods                                       | Description                                                                                                                                         |
| :-------------------------------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------- |
| [get_predefined_icons](#get_predefined_icons) | Returns a list of all available icon codes that can be used in display text. Icons are returned as simple string codes in the `:icon_name:` format. |

## get_predefined_icons

Returns a list of all available icon codes that can be used in display text. Icons are returned as simple string codes in the `:icon_name:` format.

- HTTP Method: `GET`
- Endpoint: `/predefined-icons`

**Return Type**

`GetPredefinedIconsOkResponse`

**Example Usage Code Snippet**

```python
from final_led_display_api_sdk import FinalLedDisplayApiSdk

sdk = FinalLedDisplayApiSdk(
    api_key="YOUR_API_KEY",
    api_key_header="YOUR_API_KEY_HEADER",
    timeout=10000
)

result = sdk.predefined_icons.get_predefined_icons()

print(result)
```

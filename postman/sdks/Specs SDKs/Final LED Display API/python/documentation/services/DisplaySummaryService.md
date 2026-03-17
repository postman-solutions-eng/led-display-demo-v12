# DisplaySummaryService

A list of all methods in the `DisplaySummaryService` service. Click on the method name to view detailed information about that method.

| Methods                                           | Description                                                                                                                                                                                                                                                         |
| :------------------------------------------------ | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| [create_display_summary](#create_display_summary) | Displays a predefined summary message on the LED badge. This endpoint provides a quick way to show common status messages without constructing custom text. Note: The API does not validate the type parameter and will return success for any type value provided. |

## create_display_summary

Displays a predefined summary message on the LED badge. This endpoint provides a quick way to show common status messages without constructing custom text. Note: The API does not validate the type parameter and will return success for any type value provided.

- HTTP Method: `POST`
- Endpoint: `/display-summary`

**Parameters**

| Name         | Type                                                                    | Required | Description       |
| :----------- | :---------------------------------------------------------------------- | :------- | :---------------- |
| request_body | [CreateDisplaySummaryRequest](../models/CreateDisplaySummaryRequest.md) | ❌       | The request body. |

**Return Type**

`CreateDisplaySummaryOkResponse`

**Example Usage Code Snippet**

```python
from final_led_display_api_sdk import FinalLedDisplayApiSdk
from final_led_display_api_sdk.models import CreateDisplaySummaryRequest

sdk = FinalLedDisplayApiSdk(
    api_key="YOUR_API_KEY",
    api_key_header="YOUR_API_KEY_HEADER",
    timeout=10000
)

request_body = CreateDisplaySummaryRequest(
    type_="type",
    custom_text="customText"
)

result = sdk.display_summary.create_display_summary(request_body=request_body)

print(result)
```

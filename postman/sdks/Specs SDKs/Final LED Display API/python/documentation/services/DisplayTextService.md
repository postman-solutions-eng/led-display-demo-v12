# DisplayTextService

A list of all methods in the `DisplayTextService` service. Click on the method name to view detailed information about that method.

| Methods                                     | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| :------------------------------------------ | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [create_display_text](#create_display_text) | Updates the text and visual content displayed on the LED name badge. Accepts text and icon codes in the format `:icon_name:`. \<img src="https://content.pstmn.io/05b1ef2c-9fd3-4f0e-8b2f-76998fb3f6e5/aW1hZ2UucG5n" width="240"\> ## Supported Icons The following icon codes can be embedded in text using the `:icon_name:` syntax: - `:ball:` - Filled circle - `:happy:` - Simple smiley face - `:happy2:` - Larger smiley (2 columns wide) - `:heart:` - Outline heart - `:HEART:` - Filled heart - `:heart2:` - Larger outline heart (2 columns wide) - `:HEART2:` - Larger filled heart (2 columns wide) - `:fablab:` - FabLab logo - `:bicycle:` - Bicycle icon (3 columns wide) - `:bicycle_r:` - Bicycle facing right (3 columns wide) - `:owncloud:` - OwnCloud logo (3 columns wide) - `:octocat:` - GitHub Octocat - `:smile:` - Smile emoji - `:star:` - Star icon - `:sun:` - Sun icon ## Character Restrictions **Supported characters:** - Letters: A-Z, a-z - Numbers: 0-9 - Special characters: `^ !"$%&/()=?` °}\]\[{@ \~ \|\<\>,;.:-_#'+_\` - German umlauts: äöüÄÖÜß - French/European accents: àäòöùüèéêëôöûîïÿçÀÅÄÉÈÊËÖÔÜÛÙŸŠ **NOT supported:** - Unicode emoji (e.g., 🌍) - will return a 400 error |

## create_display_text

Updates the text and visual content displayed on the LED name badge. Accepts text and icon codes in the format `:icon_name:`. \<img src="https://content.pstmn.io/05b1ef2c-9fd3-4f0e-8b2f-76998fb3f6e5/aW1hZ2UucG5n" width="240"\> ## Supported Icons The following icon codes can be embedded in text using the `:icon_name:` syntax: - `:ball:` - Filled circle - `:happy:` - Simple smiley face - `:happy2:` - Larger smiley (2 columns wide) - `:heart:` - Outline heart - `:HEART:` - Filled heart - `:heart2:` - Larger outline heart (2 columns wide) - `:HEART2:` - Larger filled heart (2 columns wide) - `:fablab:` - FabLab logo - `:bicycle:` - Bicycle icon (3 columns wide) - `:bicycle_r:` - Bicycle facing right (3 columns wide) - `:owncloud:` - OwnCloud logo (3 columns wide) - `:octocat:` - GitHub Octocat - `:smile:` - Smile emoji - `:star:` - Star icon - `:sun:` - Sun icon ## Character Restrictions **Supported characters:** - Letters: A-Z, a-z - Numbers: 0-9 - Special characters: `^ !"$%&/()=?` °}\]\[{@ \~ \|\<\>,;.:-_#'+_\` - German umlauts: äöüÄÖÜß - French/European accents: àäòöùüèéêëôöûîïÿçÀÅÄÉÈÊËÖÔÜÛÙŸŠ **NOT supported:** - Unicode emoji (e.g., 🌍) - will return a 400 error

- HTTP Method: `POST`
- Endpoint: `/display-text`

**Parameters**

| Name         | Type                                                              | Required | Description       |
| :----------- | :---------------------------------------------------------------- | :------- | :---------------- |
| request_body | [CreateDisplayTextRequest](../models/CreateDisplayTextRequest.md) | ❌       | The request body. |

**Return Type**

`CreateDisplayTextOkResponse`

**Example Usage Code Snippet**

```python
from final_led_display_api_sdk import FinalLedDisplayApiSdk
from final_led_display_api_sdk.models import CreateDisplayTextRequest

sdk = FinalLedDisplayApiSdk(
    api_key="YOUR_API_KEY",
    api_key_header="YOUR_API_KEY_HEADER",
    timeout=10000
)

request_body = CreateDisplayTextRequest(
    text="text"
)

result = sdk.display_text.create_display_text(request_body=request_body)

print(result)
```

# PredefinedIconsService

A list of all methods in the `PredefinedIconsService` service. Click on the method name to view detailed information about that method.

| Methods                                   | Description                                                                                                                                         |
| :---------------------------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------- |
| [getPredefinedIcons](#getpredefinedicons) | Returns a list of all available icon codes that can be used in display text. Icons are returned as simple string codes in the `:icon_name:` format. |

## getPredefinedIcons

Returns a list of all available icon codes that can be used in display text. Icons are returned as simple string codes in the `:icon_name:` format.

- HTTP Method: `GET`
- Endpoint: `/predefined-icons`

**Return Type**

`GetPredefinedIconsOkResponse`

**Example Usage Code Snippet**

```typescript
import { FinalLedDisplayApiSdk } from 'final-led-display-api-sdk';

(async () => {
  const finalLedDisplayApiSdk = new FinalLedDisplayApiSdk({});

  const data = await finalLedDisplayApiSdk.predefinedIcons.getPredefinedIcons();

  console.log(data);
})();
```

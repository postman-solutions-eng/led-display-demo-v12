# DisplaySummaryService

A list of all methods in the `DisplaySummaryService` service. Click on the method name to view detailed information about that method.

| Methods                                       | Description                                                                                                                                                                                                                                                         |
| :-------------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| [createDisplaySummary](#createdisplaysummary) | Displays a predefined summary message on the LED badge. This endpoint provides a quick way to show common status messages without constructing custom text. Note: The API does not validate the type parameter and will return success for any type value provided. |

## createDisplaySummary

Displays a predefined summary message on the LED badge. This endpoint provides a quick way to show common status messages without constructing custom text. Note: The API does not validate the type parameter and will return success for any type value provided.

- HTTP Method: `POST`
- Endpoint: `/display-summary`

**Parameters**

| Name | Type                                                                    | Required | Description       |
| :--- | :---------------------------------------------------------------------- | :------- | :---------------- |
| body | [CreateDisplaySummaryRequest](../models/CreateDisplaySummaryRequest.md) | ❌       | The request body. |

**Return Type**

`CreateDisplaySummaryOkResponse`

**Example Usage Code Snippet**

```typescript
import { CreateDisplaySummaryRequest, FinalLedDisplayApiSdk } from 'final-led-display-api-sdk';

(async () => {
  const finalLedDisplayApiSdk = new FinalLedDisplayApiSdk({});

  const createDisplaySummaryRequest: CreateDisplaySummaryRequest = {
    type: 'type',
    customText: 'customText',
  };

  const data = await finalLedDisplayApiSdk.displaySummary.createDisplaySummary(
    createDisplaySummaryRequest,
  );

  console.log(data);
})();
```

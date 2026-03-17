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

| Name                        | Type                                                                    | Required | Description  |
| :-------------------------- | :---------------------------------------------------------------------- | :------- | :----------- |
| createDisplaySummaryRequest | [CreateDisplaySummaryRequest](../models/CreateDisplaySummaryRequest.md) | ❌       | Request Body |

**Return Type**

`CreateDisplaySummaryOkResponse`

**Example Usage Code Snippet**

```java
import com.finalleddisplayapisdk.FinalLedDisplayApiSdk;
import com.finalleddisplayapisdk.models.CreateDisplaySummaryOkResponse;
import com.finalleddisplayapisdk.models.CreateDisplaySummaryRequest;

public class Main {

  public static void main(String[] args) {
    FinalLedDisplayApiSdk finalLedDisplayApiSdk = new FinalLedDisplayApiSdk();

    CreateDisplaySummaryRequest createDisplaySummaryRequest = CreateDisplaySummaryRequest.builder()
      .type("type")
      .customText("customText")
      .build();

    CreateDisplaySummaryOkResponse response =
      finalLedDisplayApiSdk.displaySummary.createDisplaySummary(createDisplaySummaryRequest);

    System.out.println(response);
  }
}

```

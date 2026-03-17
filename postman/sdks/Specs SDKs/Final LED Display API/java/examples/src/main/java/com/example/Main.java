package com.example;

import com.finalleddisplayapisdk.FinalLedDisplayApiSdk;
import com.finalleddisplayapisdk.exceptions.ApiError;
import com.finalleddisplayapisdk.models.GetPredefinedIconsOkResponse;

public class Main {

  public static void main(String[] args) {
    FinalLedDisplayApiSdk finalLedDisplayApiSdk = new FinalLedDisplayApiSdk();

    try {
      GetPredefinedIconsOkResponse response =
        finalLedDisplayApiSdk.predefinedIcons.getPredefinedIcons();

      System.out.println(response);
    } catch (ApiError e) {
      e.printStackTrace();
    }

    System.exit(0);
  }
}

package com.finalleddisplayapisdk;

import com.finalleddisplayapisdk.config.ApiKeyAuthConfig;
import com.finalleddisplayapisdk.config.FinalLedDisplayApiSdkConfig;
import com.finalleddisplayapisdk.http.Environment;
import com.finalleddisplayapisdk.http.interceptors.DefaultHeadersInterceptor;
import com.finalleddisplayapisdk.http.interceptors.RetryInterceptor;
import com.finalleddisplayapisdk.services.DisplaySummaryService;
import com.finalleddisplayapisdk.services.DisplayTextService;
import com.finalleddisplayapisdk.services.PredefinedIconsService;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/** # API for controlling LED name badge displays

This API allows you to update the text and visual content displayed on LED badges, including support for alphanumeric text and predefined icon codes.
<img src="https://content.pstmn.io/05b1ef2c-9fd3-4f0e-8b2f-76998fb3f6e5/aW1hZ2UucG5n" width="240">
## Supported Icons

The following icon codes can be embedded in text using the `:icon_name:` syntax:

- `:ball:` - Filled circle
    
- `:happy:` - Simple smiley face
    
- `:happy2:` - Larger smiley (2 columns wide)
    
- `:heart:` - Outline heart
    
- `:HEART:` - Filled heart
    
- `:heart2:` - Larger outline heart (2 columns wide)
    
- `:HEART2:` - Larger filled heart (2 columns wide)
    
- `:fablab:` - FabLab logo
    
- `:bicycle:` - Bicycle icon (3 columns wide)
    
- `:bicycle_r:` - Bicycle facing right (3 columns wide)
    
- `:owncloud:` - OwnCloud logo (3 columns wide)
    
- `:octocat:` - GitHub Octocat
    
- `:smile:` - Smile emoji
    
- `:star:` - Star icon
    
- `:sun:` - Sun icon
    

## Character Restrictions

**Supported characters:**

- Letters: A-Z, a-z
    
- Numbers: 0-9
    
- Special characters: `^ !"$%&/()=?` °}\]\[{@ \~ |<>,;.:-_#'+_\`
    
- German umlauts: äöüÄÖÜß
    
- French/European accents: àäòöùüèéêëôöûîïÿçÀÅÄÉÈÊËÖÔÜÛÙŸŠ
    

**NOT supported:**

- Unicode emoji (e.g., 🌍) - will return a 400 error
    

**Escape sequences:**

- `::` - Escapes to a single literal colon
    

**Custom images:**

- Use `:path/to/image.png:` syntax to reference custom images
    
- Images must be exactly 11 pixels high
    

## Additional Capabilities (not exposed in API yet)

- **Brightness control:** 25%, 50%, 75%, 100%
    
- **Animation modes:** 0-8 (various scroll and display effects)
    
- **Speed control:** 1-8 (animation speed)
    
- **Blink effect:** Enable/disable blinking
    
- **Ant effect:** Marching border animation
    

## How the spec was created

This spec was created by having analyzed the backend Python code with Postman Agent mode with the following prompt:

> The spec and the Postman collection both seem very incomplete, not containing all possible icons and other character restrictions. Can you scan the backend API code to find out what additional endpoints and capabilities regarding icons and character restrictions there are and update the spec accordingly? */
public class FinalLedDisplayApiSdk {

  public final DisplayTextService displayText;
  public final PredefinedIconsService predefinedIcons;
  public final DisplaySummaryService displaySummary;

  private final FinalLedDisplayApiSdkConfig config;

  /**
   * Constructs a new instance of FinalLedDisplayApiSdk with default configuration.
   */
  public FinalLedDisplayApiSdk() {
    // Default configs
    this(FinalLedDisplayApiSdkConfig.builder().build());
  }

  /**
   * Constructs a new instance of FinalLedDisplayApiSdk with custom configuration.
   * Initializes all services, HTTP client, and optional OAuth token manager.
   *
   * @param config The SDK configuration including base URL, authentication, timeout, and retry settings
   */
  public FinalLedDisplayApiSdk(FinalLedDisplayApiSdkConfig config) {
    this.config = config;

    final OkHttpClient httpClient = new OkHttpClient.Builder()
      .addInterceptor(new DefaultHeadersInterceptor(config))
      .addInterceptor(new RetryInterceptor(config.getRetryConfig()))
      .readTimeout(config.getTimeout(), TimeUnit.MILLISECONDS)
      .build();

    this.displayText = new DisplayTextService(httpClient, config);
    this.predefinedIcons = new PredefinedIconsService(httpClient, config);
    this.displaySummary = new DisplaySummaryService(httpClient, config);
  }

  /**
   * Sets the environment for all API requests.
   *
   * @param environment The environment to use (e.g., DEFAULT, PRODUCTION, STAGING)
   */
  public void setEnvironment(Environment environment) {
    setBaseUrl(environment.getUrl());
  }

  /**
   * Sets the base URL for all API requests.
   *
   * @param baseUrl The base URL to use for API requests
   */
  public void setBaseUrl(String baseUrl) {
    this.config.setBaseUrl(baseUrl);
  }

  /**
   * Sets the API key for all API requests.
   *
   * @param apiKey The API key to use for authentication
   */
  public void setApiKey(String apiKey) {
    ApiKeyAuthConfig apiKeyAuthConfig = this.config.getApiKeyAuthConfig();
    apiKeyAuthConfig.setApiKey(apiKey);
  }

  /**
   * Sets the API key header name for all API requests.
   *
   * @param apiKeyHeader The header name to use for the API key
   */
  public void setApiKeyHeader(String apiKeyHeader) {
    ApiKeyAuthConfig apiKeyAuthConfig = this.config.getApiKeyAuthConfig();
    apiKeyAuthConfig.setApiKeyHeader(apiKeyHeader);
  }
}
// c029837e0e474b76bc487506e8799df5e3335891efe4fb02bda7a1441840310c

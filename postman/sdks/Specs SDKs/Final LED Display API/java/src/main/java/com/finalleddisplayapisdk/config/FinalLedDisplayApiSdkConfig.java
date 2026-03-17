package com.finalleddisplayapisdk.config;

import com.finalleddisplayapisdk.http.Environment;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

/**
 * Configuration class for SDK client settings.
 * Provides builder pattern for configuring base URLs, authentication, timeouts, and retry behavior.
 * All configuration options have sensible defaults and can be customized as needed.
 */
@Builder
@Data
public class FinalLedDisplayApiSdkConfig {

  @NonNull
  @Builder.Default
  private String userAgent = "postman-codegen/2.25.51 finalleddisplayapisdk/1.0.0 (java)";

  @Setter
  private String baseUrl;

  @NonNull
  @Builder.Default
  private RetryConfig retryConfig = RetryConfig.builder().build();

  @NonNull
  @Builder.Default
  private ApiKeyAuthConfig apiKeyAuthConfig = ApiKeyAuthConfig.builder().build();

  /** Timeout in milliseconds */
  @Builder.Default
  private long timeout = 10_000;

  public void setEnvironment(Environment environment) {
    this.baseUrl = environment.getUrl();
  }
}

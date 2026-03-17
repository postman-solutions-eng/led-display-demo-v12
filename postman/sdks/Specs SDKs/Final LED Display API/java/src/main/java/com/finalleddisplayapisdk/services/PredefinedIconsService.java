package com.finalleddisplayapisdk.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.finalleddisplayapisdk.config.FinalLedDisplayApiSdkConfig;
import com.finalleddisplayapisdk.config.RequestConfig;
import com.finalleddisplayapisdk.exceptions.ApiError;
import com.finalleddisplayapisdk.exceptions.ErrorResponseException;
import com.finalleddisplayapisdk.http.Environment;
import com.finalleddisplayapisdk.http.HttpMethod;
import com.finalleddisplayapisdk.http.ModelConverter;
import com.finalleddisplayapisdk.http.util.RequestBuilder;
import com.finalleddisplayapisdk.models.ErrorResponse;
import com.finalleddisplayapisdk.models.GetPredefinedIconsOkResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * PredefinedIconsService Service
 */
public class PredefinedIconsService extends BaseService {

  private RequestConfig getPredefinedIconsConfig;

  /**
   * Constructs a new instance of PredefinedIconsService.
   *
   * @param httpClient The HTTP client to use for requests
   * @param config The SDK configuration
   */
  public PredefinedIconsService(
    @NonNull OkHttpClient httpClient,
    FinalLedDisplayApiSdkConfig config
  ) {
    super(httpClient, config);
  }

  /**
   * Sets method-level configuration for {@code getPredefinedIcons}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public PredefinedIconsService setGetPredefinedIconsConfig(RequestConfig config) {
    this.getPredefinedIconsConfig = config;
    return this;
  }

  /**
   * Get Predefined Icons
   *
   * @return response of {@code GetPredefinedIconsOkResponse}
   */
  public GetPredefinedIconsOkResponse getPredefinedIcons() throws ApiError {
    return this.getPredefinedIcons(null);
  }

  /**
   * Get Predefined Icons
   *
   * @return response of {@code GetPredefinedIconsOkResponse}
   */
  public GetPredefinedIconsOkResponse getPredefinedIcons(RequestConfig requestConfig)
    throws ApiError {
    RequestConfig resolvedConfig =
      this.getResolvedConfig(this.getPredefinedIconsConfig, requestConfig);
    this.addErrorMapping(500, ErrorResponse.class, ErrorResponseException.class);
    Request request = this.buildGetPredefinedIconsRequest(resolvedConfig);
    Response response = this.execute(request, resolvedConfig);
    byte[] bodyBytes = ModelConverter.readBytes(response);
    return ModelConverter.convert(bodyBytes, new TypeReference<GetPredefinedIconsOkResponse>() {});
  }

  /**
   * Get Predefined Icons
   *
   * @return response of {@code CompletableFuture<GetPredefinedIconsOkResponse>}
   */
  public CompletableFuture<GetPredefinedIconsOkResponse> getPredefinedIconsAsync() throws ApiError {
    return this.getPredefinedIconsAsync(null);
  }

  /**
   * Get Predefined Icons
   *
   * @return response of {@code CompletableFuture<GetPredefinedIconsOkResponse>}
   */
  public CompletableFuture<GetPredefinedIconsOkResponse> getPredefinedIconsAsync(
    RequestConfig requestConfig
  ) throws ApiError {
    RequestConfig resolvedConfig =
      this.getResolvedConfig(this.getPredefinedIconsConfig, requestConfig);
    this.addErrorMapping(500, ErrorResponse.class, ErrorResponseException.class);
    Request request = this.buildGetPredefinedIconsRequest(resolvedConfig);
    CompletableFuture<Response> futureResponse = this.executeAsync(request, resolvedConfig);
    return futureResponse.thenApplyAsync(response -> {
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return ModelConverter.convert(
        bodyBytes,
        new TypeReference<GetPredefinedIconsOkResponse>() {}
      );
    });
  }

  private Request buildGetPredefinedIconsRequest(RequestConfig resolvedConfig) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "predefined-icons"
    )
      .setApiKeyAuth(resolveApiKeyAuthConfig(resolvedConfig))
      .build();
  }
}

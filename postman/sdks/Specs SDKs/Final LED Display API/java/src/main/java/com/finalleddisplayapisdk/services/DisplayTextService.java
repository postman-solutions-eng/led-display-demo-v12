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
import com.finalleddisplayapisdk.models.CreateDisplayTextOkResponse;
import com.finalleddisplayapisdk.models.CreateDisplayTextRequest;
import com.finalleddisplayapisdk.models.ErrorResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * DisplayTextService Service
 */
public class DisplayTextService extends BaseService {

  private RequestConfig createDisplayTextConfig;

  /**
   * Constructs a new instance of DisplayTextService.
   *
   * @param httpClient The HTTP client to use for requests
   * @param config The SDK configuration
   */
  public DisplayTextService(@NonNull OkHttpClient httpClient, FinalLedDisplayApiSdkConfig config) {
    super(httpClient, config);
  }

  /**
   * Sets method-level configuration for {@code createDisplayText}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public DisplayTextService setCreateDisplayTextConfig(RequestConfig config) {
    this.createDisplayTextConfig = config;
    return this;
  }

  /**
   * Display Text
   *
   * @return response of {@code CreateDisplayTextOkResponse}
   */
  public CreateDisplayTextOkResponse createDisplayText() throws ApiError {
    return this.createDisplayText(CreateDisplayTextRequest.builder().build());
  }

  /**
   * Display Text
   *
   * @param createDisplayTextRequest {@link CreateDisplayTextRequest} Request Body
   * @return response of {@code CreateDisplayTextOkResponse}
   */
  public CreateDisplayTextOkResponse createDisplayText(
    @NonNull CreateDisplayTextRequest createDisplayTextRequest
  ) throws ApiError {
    return this.createDisplayText(createDisplayTextRequest, null);
  }

  /**
   * Display Text
   *
   * @param createDisplayTextRequest {@link CreateDisplayTextRequest} Request Body
   * @return response of {@code CreateDisplayTextOkResponse}
   */
  public CreateDisplayTextOkResponse createDisplayText(
    @NonNull CreateDisplayTextRequest createDisplayTextRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    RequestConfig resolvedConfig =
      this.getResolvedConfig(this.createDisplayTextConfig, requestConfig);
    this.addErrorMapping(400, ErrorResponse.class, ErrorResponseException.class);
    this.addErrorMapping(500, ErrorResponse.class, ErrorResponseException.class);
    Request request = this.buildCreateDisplayTextRequest(createDisplayTextRequest, resolvedConfig);
    Response response = this.execute(request, resolvedConfig);
    byte[] bodyBytes = ModelConverter.readBytes(response);
    return ModelConverter.convert(bodyBytes, new TypeReference<CreateDisplayTextOkResponse>() {});
  }

  /**
   * Display Text
   *
   * @return response of {@code CompletableFuture<CreateDisplayTextOkResponse>}
   */
  public CompletableFuture<CreateDisplayTextOkResponse> createDisplayTextAsync() throws ApiError {
    return this.createDisplayTextAsync(CreateDisplayTextRequest.builder().build());
  }

  /**
   * Display Text
   *
   * @param createDisplayTextRequest {@link CreateDisplayTextRequest} Request Body
   * @return response of {@code CompletableFuture<CreateDisplayTextOkResponse>}
   */
  public CompletableFuture<CreateDisplayTextOkResponse> createDisplayTextAsync(
    @NonNull CreateDisplayTextRequest createDisplayTextRequest
  ) throws ApiError {
    return this.createDisplayTextAsync(createDisplayTextRequest, null);
  }

  /**
   * Display Text
   *
   * @param createDisplayTextRequest {@link CreateDisplayTextRequest} Request Body
   * @return response of {@code CompletableFuture<CreateDisplayTextOkResponse>}
   */
  public CompletableFuture<CreateDisplayTextOkResponse> createDisplayTextAsync(
    @NonNull CreateDisplayTextRequest createDisplayTextRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    RequestConfig resolvedConfig =
      this.getResolvedConfig(this.createDisplayTextConfig, requestConfig);
    this.addErrorMapping(400, ErrorResponse.class, ErrorResponseException.class);
    this.addErrorMapping(500, ErrorResponse.class, ErrorResponseException.class);
    Request request = this.buildCreateDisplayTextRequest(createDisplayTextRequest, resolvedConfig);
    CompletableFuture<Response> futureResponse = this.executeAsync(request, resolvedConfig);
    return futureResponse.thenApplyAsync(response -> {
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return ModelConverter.convert(bodyBytes, new TypeReference<CreateDisplayTextOkResponse>() {});
    });
  }

  private Request buildCreateDisplayTextRequest(
    @NonNull CreateDisplayTextRequest createDisplayTextRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "display-text"
    )
      .setApiKeyAuth(resolveApiKeyAuthConfig(resolvedConfig))
      .setJsonContent(createDisplayTextRequest)
      .build();
  }
}

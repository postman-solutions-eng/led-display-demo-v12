package com.finalleddisplayapisdk.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.finalleddisplayapisdk.config.FinalLedDisplayApiSdkConfig;
import com.finalleddisplayapisdk.config.RequestConfig;
import com.finalleddisplayapisdk.exceptions.ApiError;
import com.finalleddisplayapisdk.http.Environment;
import com.finalleddisplayapisdk.http.HttpMethod;
import com.finalleddisplayapisdk.http.ModelConverter;
import com.finalleddisplayapisdk.http.util.RequestBuilder;
import com.finalleddisplayapisdk.models.CreateDisplaySummaryOkResponse;
import com.finalleddisplayapisdk.models.CreateDisplaySummaryRequest;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * DisplaySummaryService Service
 */
public class DisplaySummaryService extends BaseService {

  private RequestConfig createDisplaySummaryConfig;

  /**
   * Constructs a new instance of DisplaySummaryService.
   *
   * @param httpClient The HTTP client to use for requests
   * @param config The SDK configuration
   */
  public DisplaySummaryService(
    @NonNull OkHttpClient httpClient,
    FinalLedDisplayApiSdkConfig config
  ) {
    super(httpClient, config);
  }

  /**
   * Sets method-level configuration for {@code createDisplaySummary}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public DisplaySummaryService setCreateDisplaySummaryConfig(RequestConfig config) {
    this.createDisplaySummaryConfig = config;
    return this;
  }

  /**
   * Display Summary
   *
   * @return response of {@code CreateDisplaySummaryOkResponse}
   */
  public CreateDisplaySummaryOkResponse createDisplaySummary() throws ApiError {
    return this.createDisplaySummary(CreateDisplaySummaryRequest.builder().build());
  }

  /**
   * Display Summary
   *
   * @param createDisplaySummaryRequest {@link CreateDisplaySummaryRequest} Request Body
   * @return response of {@code CreateDisplaySummaryOkResponse}
   */
  public CreateDisplaySummaryOkResponse createDisplaySummary(
    @NonNull CreateDisplaySummaryRequest createDisplaySummaryRequest
  ) throws ApiError {
    return this.createDisplaySummary(createDisplaySummaryRequest, null);
  }

  /**
   * Display Summary
   *
   * @param createDisplaySummaryRequest {@link CreateDisplaySummaryRequest} Request Body
   * @return response of {@code CreateDisplaySummaryOkResponse}
   */
  public CreateDisplaySummaryOkResponse createDisplaySummary(
    @NonNull CreateDisplaySummaryRequest createDisplaySummaryRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    RequestConfig resolvedConfig =
      this.getResolvedConfig(this.createDisplaySummaryConfig, requestConfig);
    Request request =
      this.buildCreateDisplaySummaryRequest(createDisplaySummaryRequest, resolvedConfig);
    Response response = this.execute(request, resolvedConfig);
    byte[] bodyBytes = ModelConverter.readBytes(response);
    return ModelConverter.convert(
      bodyBytes,
      new TypeReference<CreateDisplaySummaryOkResponse>() {}
    );
  }

  /**
   * Display Summary
   *
   * @return response of {@code CompletableFuture<CreateDisplaySummaryOkResponse>}
   */
  public CompletableFuture<CreateDisplaySummaryOkResponse> createDisplaySummaryAsync()
    throws ApiError {
    return this.createDisplaySummaryAsync(CreateDisplaySummaryRequest.builder().build());
  }

  /**
   * Display Summary
   *
   * @param createDisplaySummaryRequest {@link CreateDisplaySummaryRequest} Request Body
   * @return response of {@code CompletableFuture<CreateDisplaySummaryOkResponse>}
   */
  public CompletableFuture<CreateDisplaySummaryOkResponse> createDisplaySummaryAsync(
    @NonNull CreateDisplaySummaryRequest createDisplaySummaryRequest
  ) throws ApiError {
    return this.createDisplaySummaryAsync(createDisplaySummaryRequest, null);
  }

  /**
   * Display Summary
   *
   * @param createDisplaySummaryRequest {@link CreateDisplaySummaryRequest} Request Body
   * @return response of {@code CompletableFuture<CreateDisplaySummaryOkResponse>}
   */
  public CompletableFuture<CreateDisplaySummaryOkResponse> createDisplaySummaryAsync(
    @NonNull CreateDisplaySummaryRequest createDisplaySummaryRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    RequestConfig resolvedConfig =
      this.getResolvedConfig(this.createDisplaySummaryConfig, requestConfig);
    Request request =
      this.buildCreateDisplaySummaryRequest(createDisplaySummaryRequest, resolvedConfig);
    CompletableFuture<Response> futureResponse = this.executeAsync(request, resolvedConfig);
    return futureResponse.thenApplyAsync(response -> {
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return ModelConverter.convert(
        bodyBytes,
        new TypeReference<CreateDisplaySummaryOkResponse>() {}
      );
    });
  }

  private Request buildCreateDisplaySummaryRequest(
    @NonNull CreateDisplaySummaryRequest createDisplaySummaryRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "display-summary"
    )
      .setApiKeyAuth(resolveApiKeyAuthConfig(resolvedConfig))
      .setJsonContent(createDisplaySummaryRequest)
      .build();
  }
}

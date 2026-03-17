import { z } from 'zod';
import { BaseService } from '../base-service';
import { ContentType, HttpResponse, SdkConfig } from '../../http/types';
import { RequestBuilder } from '../../http/transport/request-builder';
import { SerializationStyle } from '../../http/serialization/base-serializer';
import { ThrowableError } from '../../http/errors/throwable-error';
import { Environment } from '../../http/environment';
import {
  CreateDisplaySummaryRequest,
  createDisplaySummaryRequestRequest,
} from './models/create-display-summary-request';
import {
  CreateDisplaySummaryOkResponse,
  createDisplaySummaryOkResponseResponse,
} from './models/create-display-summary-ok-response';

/**
 * Service class for DisplaySummaryService operations.
 * Provides methods to interact with DisplaySummaryService-related API endpoints.
 * All methods return promises and handle request/response serialization automatically.
 */
export class DisplaySummaryService extends BaseService {
  protected createDisplaySummaryConfig?: Partial<SdkConfig>;

  /**
   * Sets method-level configuration for createDisplaySummary.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setCreateDisplaySummaryConfig(config: Partial<SdkConfig>): this {
    this.createDisplaySummaryConfig = config;
    return this;
  }

  /**
 * Displays a predefined summary message on the LED badge. This endpoint provides a quick way to show common status messages without constructing custom text.
Note: The API does not validate the type parameter and will return success for any type value provided.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<CreateDisplaySummaryOkResponse>>} - Summary message displayed successfully
 */
  async createDisplaySummary(
    body: CreateDisplaySummaryRequest,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<CreateDisplaySummaryOkResponse> {
    const resolvedConfig = this.getResolvedConfig(this.createDisplaySummaryConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/display-summary')
      .setRequestSchema(createDisplaySummaryRequestRequest)
      .addApiKeyAuth(resolvedConfig?.apiKey)
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: createDisplaySummaryOkResponseResponse,
        contentType: ContentType.Json,
        status: 200,
      })
      .addHeaderParam({ key: 'Content-Type', value: 'application/json' })
      .addBody(body)
      .build();
    return this.client.callDirect<CreateDisplaySummaryOkResponse>(request);
  }
}

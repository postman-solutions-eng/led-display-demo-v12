import { z } from 'zod';
import { BaseService } from '../base-service';
import { ContentType, HttpResponse, SdkConfig } from '../../http/types';
import { RequestBuilder } from '../../http/transport/request-builder';
import { SerializationStyle } from '../../http/serialization/base-serializer';
import { ThrowableError } from '../../http/errors/throwable-error';
import { Environment } from '../../http/environment';
import {
  GetPredefinedIconsOkResponse,
  getPredefinedIconsOkResponseResponse,
} from './models/get-predefined-icons-ok-response';
import { ErrorResponse } from '../common/error-response';

/**
 * Service class for PredefinedIconsService operations.
 * Provides methods to interact with PredefinedIconsService-related API endpoints.
 * All methods return promises and handle request/response serialization automatically.
 */
export class PredefinedIconsService extends BaseService {
  protected getPredefinedIconsConfig?: Partial<SdkConfig>;

  /**
   * Sets method-level configuration for getPredefinedIcons.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setGetPredefinedIconsConfig(config: Partial<SdkConfig>): this {
    this.getPredefinedIconsConfig = config;
    return this;
  }

  /**
   * Returns a list of all available icon codes that can be used in display text. Icons are returned as simple string codes in the `:icon_name:` format.
   * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
   * @returns {Promise<HttpResponse<GetPredefinedIconsOkResponse>>} - List of all available icon codes
   */
  async getPredefinedIcons(
    requestConfig?: Partial<SdkConfig>,
  ): Promise<GetPredefinedIconsOkResponse> {
    const resolvedConfig = this.getResolvedConfig(this.getPredefinedIconsConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('GET')
      .setPath('/predefined-icons')
      .setRequestSchema(z.any())
      .addApiKeyAuth(resolvedConfig?.apiKey)
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: getPredefinedIconsOkResponseResponse,
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ErrorResponse,
        contentType: ContentType.Json,
        status: 500,
      })
      .build();
    return this.client.callDirect<GetPredefinedIconsOkResponse>(request);
  }
}

import { z } from 'zod';
import { BaseService } from '../base-service';
import { ContentType, HttpResponse, SdkConfig } from '../../http/types';
import { RequestBuilder } from '../../http/transport/request-builder';
import { SerializationStyle } from '../../http/serialization/base-serializer';
import { ThrowableError } from '../../http/errors/throwable-error';
import { Environment } from '../../http/environment';
import {
  CreateDisplayTextRequest,
  createDisplayTextRequestRequest,
} from './models/create-display-text-request';
import {
  CreateDisplayTextOkResponse,
  createDisplayTextOkResponseResponse,
} from './models/create-display-text-ok-response';
import { ErrorResponse } from '../common/error-response';

/**
 * Service class for DisplayTextService operations.
 * Provides methods to interact with DisplayTextService-related API endpoints.
 * All methods return promises and handle request/response serialization automatically.
 */
export class DisplayTextService extends BaseService {
  protected createDisplayTextConfig?: Partial<SdkConfig>;

  /**
   * Sets method-level configuration for createDisplayText.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setCreateDisplayTextConfig(config: Partial<SdkConfig>): this {
    this.createDisplayTextConfig = config;
    return this;
  }

  /**
 * Updates the text and visual content displayed on the LED name badge. Accepts text and icon codes in the format `:icon_name:`.
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
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<CreateDisplayTextOkResponse>>} - Success with Text + Heart Icon
 */
  async createDisplayText(
    body: CreateDisplayTextRequest,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<CreateDisplayTextOkResponse> {
    const resolvedConfig = this.getResolvedConfig(this.createDisplayTextConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/display-text')
      .setRequestSchema(createDisplayTextRequestRequest)
      .addApiKeyAuth(resolvedConfig?.apiKey)
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: createDisplayTextOkResponseResponse,
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ErrorResponse,
        contentType: ContentType.Json,
        status: 400,
      })
      .addError({
        error: ErrorResponse,
        contentType: ContentType.Json,
        status: 500,
      })
      .addHeaderParam({ key: 'Content-Type', value: 'application/json' })
      .addBody(body)
      .build();
    return this.client.callDirect<CreateDisplayTextOkResponse>(request);
  }
}

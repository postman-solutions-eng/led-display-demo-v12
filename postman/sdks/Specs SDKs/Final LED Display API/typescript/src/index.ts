import { Environment } from './http/environment';
import { SdkConfig } from './http/types';
import { DisplayTextService } from './services/display-text';
import { PredefinedIconsService } from './services/predefined-icons';
import { DisplaySummaryService } from './services/display-summary';

export * from './services/display-text';
export * from './services/predefined-icons';
export * from './services/display-summary';
export * from './services/common';

export * from './http';
export { Environment } from './http/environment';

export class FinalLedDisplayApiSdk {
  public readonly displayText: DisplayTextService;

  public readonly predefinedIcons: PredefinedIconsService;

  public readonly displaySummary: DisplaySummaryService;

  constructor(public config: SdkConfig) {
    this.displayText = new DisplayTextService(this.config);

    this.predefinedIcons = new PredefinedIconsService(this.config);

    this.displaySummary = new DisplaySummaryService(this.config);
  }

  set baseUrl(baseUrl: string) {
    this.displayText.baseUrl = baseUrl;
    this.predefinedIcons.baseUrl = baseUrl;
    this.displaySummary.baseUrl = baseUrl;
  }

  set environment(environment: Environment) {
    this.displayText.baseUrl = environment;
    this.predefinedIcons.baseUrl = environment;
    this.displaySummary.baseUrl = environment;
  }

  set timeoutMs(timeoutMs: number) {
    this.displayText.timeoutMs = timeoutMs;
    this.predefinedIcons.timeoutMs = timeoutMs;
    this.displaySummary.timeoutMs = timeoutMs;
  }

  set apiKey(apiKey: string) {
    this.displayText.apiKey = apiKey;
    this.predefinedIcons.apiKey = apiKey;
    this.displaySummary.apiKey = apiKey;
  }

  set apiKeyHeader(apiKeyHeader: string) {
    this.displayText.apiKeyHeader = apiKeyHeader;
    this.predefinedIcons.apiKeyHeader = apiKeyHeader;
    this.displaySummary.apiKeyHeader = apiKeyHeader;
  }
}

// c029837e0e474b76bc487506e8799df5e3335891efe4fb02bda7a1441840310c

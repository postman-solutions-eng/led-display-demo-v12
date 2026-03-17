package com.finalleddisplayapisdk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.With;
import lombok.extern.jackson.Jacksonized;
import org.openapitools.jackson.nullable.JsonNullable;

@Data
@Builder
@With
@ToString
@EqualsAndHashCode
@Jacksonized
public class CreateDisplaySummaryRequest {

  /**
   * The type of summary message to display (e.g., welcome, status, alert, info)
   */
  @JsonProperty("type")
  private JsonNullable<String> type;

  /**
   * Optional custom text to append to the summary
   */
  @JsonProperty("customText")
  private JsonNullable<String> customText;

  @JsonIgnore
  public String getType() {
    return type.orElse(null);
  }

  @JsonIgnore
  public String getCustomText() {
    return customText.orElse(null);
  }

  // Overwrite lombok builder methods
  public static class CreateDisplaySummaryRequestBuilder {

    private JsonNullable<String> type = JsonNullable.undefined();

    @JsonProperty("type")
    public CreateDisplaySummaryRequestBuilder type(String value) {
      if (value == null) {
        throw new IllegalStateException("type cannot be null");
      }
      this.type = JsonNullable.of(value);
      return this;
    }

    private JsonNullable<String> customText = JsonNullable.undefined();

    @JsonProperty("customText")
    public CreateDisplaySummaryRequestBuilder customText(String value) {
      if (value == null) {
        throw new IllegalStateException("customText cannot be null");
      }
      this.customText = JsonNullable.of(value);
      return this;
    }
  }
}

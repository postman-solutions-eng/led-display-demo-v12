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
public class CreateDisplaySummaryOkResponse {

  /**
   * Status of the operation
   */
  @JsonProperty("status")
  private JsonNullable<String> status;

  @JsonIgnore
  public String getStatus() {
    return status.orElse(null);
  }

  // Overwrite lombok builder methods
  public static class CreateDisplaySummaryOkResponseBuilder {

    private JsonNullable<String> status = JsonNullable.undefined();

    @JsonProperty("status")
    public CreateDisplaySummaryOkResponseBuilder status(String value) {
      if (value == null) {
        throw new IllegalStateException("status cannot be null");
      }
      this.status = JsonNullable.of(value);
      return this;
    }
  }
}

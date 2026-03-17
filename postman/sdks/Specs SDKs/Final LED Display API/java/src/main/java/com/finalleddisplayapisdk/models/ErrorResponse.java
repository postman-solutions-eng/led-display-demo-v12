package com.finalleddisplayapisdk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

  @JsonProperty("error")
  private JsonNullable<String> error;

  @JsonProperty("details")
  private JsonNullable<String> details;

  @JsonIgnore
  public String getError() {
    return error.orElse(null);
  }

  @JsonIgnore
  public String getDetails() {
    return details.orElse(null);
  }

  // Overwrite lombok builder methods
  public static class ErrorResponseBuilder {

    private JsonNullable<String> error = JsonNullable.undefined();

    @JsonProperty("error")
    public ErrorResponseBuilder error(String value) {
      if (value == null) {
        throw new IllegalStateException("error cannot be null");
      }
      this.error = JsonNullable.of(value);
      return this;
    }

    private JsonNullable<String> details = JsonNullable.undefined();

    @JsonProperty("details")
    public ErrorResponseBuilder details(String value) {
      if (value == null) {
        throw new IllegalStateException("details cannot be null");
      }
      this.details = JsonNullable.of(value);
      return this;
    }
  }
}

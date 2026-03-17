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
public class CreateDisplayTextOkResponse {

  @JsonProperty("status")
  private JsonNullable<String> status;

  @JsonProperty("text")
  private JsonNullable<String> text;

  @JsonIgnore
  public String getStatus() {
    return status.orElse(null);
  }

  @JsonIgnore
  public String getText() {
    return text.orElse(null);
  }

  // Overwrite lombok builder methods
  public static class CreateDisplayTextOkResponseBuilder {

    private JsonNullable<String> status = JsonNullable.undefined();

    @JsonProperty("status")
    public CreateDisplayTextOkResponseBuilder status(String value) {
      if (value == null) {
        throw new IllegalStateException("status cannot be null");
      }
      this.status = JsonNullable.of(value);
      return this;
    }

    private JsonNullable<String> text = JsonNullable.undefined();

    @JsonProperty("text")
    public CreateDisplayTextOkResponseBuilder text(String value) {
      if (value == null) {
        throw new IllegalStateException("text cannot be null");
      }
      this.text = JsonNullable.of(value);
      return this;
    }
  }
}

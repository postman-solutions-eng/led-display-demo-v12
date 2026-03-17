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
public class CreateDisplayTextRequest {

  /**
   * The text to display, optionally including icon codes in icon_name format
   */
  @JsonProperty("text")
  private JsonNullable<String> text;

  @JsonIgnore
  public String getText() {
    return text.orElse(null);
  }

  // Overwrite lombok builder methods
  public static class CreateDisplayTextRequestBuilder {

    private JsonNullable<String> text = JsonNullable.undefined();

    @JsonProperty("text")
    public CreateDisplayTextRequestBuilder text(String value) {
      if (value == null) {
        throw new IllegalStateException("text cannot be null");
      }
      this.text = JsonNullable.of(value);
      return this;
    }
  }
}

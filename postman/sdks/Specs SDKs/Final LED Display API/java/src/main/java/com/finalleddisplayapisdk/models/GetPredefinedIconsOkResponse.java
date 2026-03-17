package com.finalleddisplayapisdk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
public class GetPredefinedIconsOkResponse {

  /**
   * Array of icon codes in :icon_name format
   */
  @JsonProperty("icons")
  private JsonNullable<List<String>> icons;

  @JsonIgnore
  public List<String> getIcons() {
    return icons.orElse(null);
  }

  // Overwrite lombok builder methods
  public static class GetPredefinedIconsOkResponseBuilder {

    private JsonNullable<List<String>> icons = JsonNullable.undefined();

    @JsonProperty("icons")
    public GetPredefinedIconsOkResponseBuilder icons(List<String> value) {
      if (value == null) {
        throw new IllegalStateException("icons cannot be null");
      }
      this.icons = JsonNullable.of(value);
      return this;
    }
  }
}

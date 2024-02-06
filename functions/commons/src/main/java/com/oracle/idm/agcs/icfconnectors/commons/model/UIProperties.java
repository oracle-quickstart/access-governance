package com.oracle.idm.agcs.icfconnectors.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oracle.idm.agcs.icfconnectors.commons.enums.InputType;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Widget;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = UIProperties.UIPropertiesBuilder.class)
public class UIProperties {

  @JsonProperty("inputType")
  private InputType inputType;

  @JsonProperty("widget")
  private Widget widget;

  @JsonProperty("title")
  private String title;

  @JsonProperty("labelHint")
  private String labelHint;

  @JsonProperty("minLength")
  private Integer minLength;

  @JsonProperty("maxLength")
  private Integer maxLength;

  @JsonProperty("defaultValues")
  private List<String> defaultValues;
}

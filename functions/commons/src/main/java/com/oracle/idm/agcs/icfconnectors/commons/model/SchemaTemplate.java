package com.oracle.idm.agcs.icfconnectors.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oracle.idm.agcs.icfconnectors.commons.enums.EntityType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SchemaTemplate.SchemaTemplateBuilder.class)
public class SchemaTemplate {

  @JsonProperty("type")
  private EntityType type;

  @JsonProperty("name")
  private String name;

  @JsonProperty("displayName")
  private String displayName;

  @JsonProperty("attributes")
  private List<SchemaAttribute> attributes;

  @JsonProperty("data")
  private Map<String, String> data;
}

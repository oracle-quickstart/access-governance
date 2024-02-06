package com.oracle.idm.agcs.icfconnectors.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oracle.idm.agcs.icfconnectors.commons.enums.DataType;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Nature;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Usage;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SchemaAttribute.SchemaAttributeBuilder.class)
public class SchemaAttribute {

  @JsonProperty("name")
  private String name;

  @JsonProperty("dataType")
  private DataType dataType;

  @JsonProperty("nature")
  private List<Nature> nature;

  @JsonProperty("usage")
  private List<Usage> usage;

  @JsonProperty("relationship")
  private Relationship relationship;

  @JsonProperty("outboundTransformation")
  private OutboundTransformation outboundTransformation;

  @JsonProperty("uiProperties")
  private UIProperties uiProperties;
}

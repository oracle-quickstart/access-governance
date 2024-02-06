package com.oracle.idm.agcs.icfconnectors.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Relationship.RelationshipBuilder.class)
public class Relationship {

  @JsonProperty("relatedTo")
  private String relatedTo;

  @JsonProperty("relatedBy")
  private String relatedBy;

  @JsonProperty("relationshipProperties")
  private List<RelationshipProperties> relationshipProperties;
}

package com.oracle.idm.agcs.icfconnectors.commons.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Operation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = RequestTemplateInput.RequestTemplateInputBuilder.class)
public class RequestTemplateInput {

  @JsonProperty("connectedSystemName")
  private String connectedSystemName;

  @JsonProperty("entityName")
  private String entityName;

  @JsonProperty("operationName")
  private Operation operationName;
}

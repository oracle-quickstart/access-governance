package com.oracle.idm.agcs.icfconnectors.commons.model.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oracle.idm.agcs.icfconnectors.commons.enums.PaginationType;
import com.oracle.idm.agcs.icfconnectors.commons.enums.RequestMethod;
import com.oracle.idm.agcs.icfconnectors.commons.model.KeyValue;
import com.oracle.idm.agcs.icfconnectors.commons.model.URI;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = RequestTemplateOutput.RequestTemplateOutputBuilder.class)
public class RequestTemplateOutput {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("method")
  private RequestMethod method;

  @JsonProperty("paginationType")
  private PaginationType paginationType;

  @JsonProperty("uri")
  private URI uri;

  @JsonProperty("queryParameters")
  private List<KeyValue> queryParameters;

  @JsonProperty("headers")
  private List<KeyValue> headers;

  @JsonProperty("body")
  private Map<String, Object> body;

  @JsonProperty("subRequests")
  private List<RequestTemplateOutput> subRequests;
}

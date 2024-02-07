package com.oracle.idm.agcs.icfconnectors.commons.model.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oracle.idm.agcs.icfconnectors.commons.model.SchemaTemplate;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SchemaTemplateOutput.SchemaTemplateOutputBuilder.class)
public class SchemaTemplateOutput {

  @JsonProperty("schemaTemplates")
  private List<SchemaTemplate> schemaTemplates;
}

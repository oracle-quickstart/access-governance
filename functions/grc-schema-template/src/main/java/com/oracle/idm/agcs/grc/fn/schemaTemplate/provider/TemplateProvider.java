package com.oracle.idm.agcs.grc.fn.schemaTemplate.provider;

import com.oracle.idm.agcs.grc.fn.schemaTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.SchemaTemplateOutput;

public abstract class TemplateProvider {
  public abstract SchemaTemplateOutput getTemplateOutput(ApplicationConfig applicationConfig);
}

package com.oracle.idm.agcs.grc.fn.testTemplate.provider;

import com.oracle.idm.agcs.grc.fn.testTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.testTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.RequestTemplateOutput;

public abstract class TemplateProvider {
  public abstract RequestTemplateOutput getTemplateOutput(
      ApplicationConfig applicationConfig, ConnectedSystemConfig connectedSystemConfig);
}

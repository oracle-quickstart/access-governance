package com.oracle.idm.agcs.grc.fn.requestTemplate.provider;

import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Operation;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.RequestTemplateOutput;

public class FATemplateProvider extends TemplateProvider {

  @Override
  public RequestTemplateOutput getTemplateOutput(
      ApplicationConfig applicationConfig,
      ConnectedSystemConfig connectedSystemConfig,
      String entityName,
      Operation operationName) {
    return getTemplateOutputWithAuthorizationValue(
        applicationConfig, connectedSystemConfig, entityName, operationName);
  }
}

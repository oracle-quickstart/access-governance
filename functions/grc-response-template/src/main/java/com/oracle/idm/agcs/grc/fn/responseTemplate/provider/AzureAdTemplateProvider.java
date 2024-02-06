package com.oracle.idm.agcs.grc.fn.responseTemplate.provider;

import com.oracle.idm.agcs.grc.fn.responseTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Operation;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.ResponseTemplateOutput;

public class AzureAdTemplateProvider extends TemplateProvider {

  @Override
  public ResponseTemplateOutput getTemplateOutput(
          ApplicationConfig applicationConfig, String entityName, Operation operationName) {
    return getTemplateOutputFromResources(applicationConfig, entityName, operationName);
  }
}

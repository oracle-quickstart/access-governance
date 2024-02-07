package com.oracle.idm.agcs.grc.fn.responseTemplate.provider;

import com.oracle.idm.agcs.grc.fn.responseTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.responseTemplate.util.Util;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Operation;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.ResponseTemplateOutput;

public abstract class TemplateProvider {
  public abstract ResponseTemplateOutput getTemplateOutput(
          ApplicationConfig applicationConfig, String entityName, Operation operationName);

  public ResponseTemplateOutput getTemplateOutputFromResources(
      ApplicationConfig applicationConfig, String entityName, Operation operationName) {
    System.err.println(
        String.format(
            "start getting response template data from json file for application %s , entityName %s and operationName %s.",
            applicationConfig.getApplication().name(), entityName, operationName.name()));

    ResponseTemplateOutput templateOutputFromResources =
        Util.getTemplateOutputFromResources(
            applicationConfig.getApplication(), entityName, operationName);

    System.err.println(
        String.format(
            "finished getting response template data from json file for application %s , entityName %s and operationName %s.",
            applicationConfig.getApplication().name(), entityName, operationName.name()));

    return templateOutputFromResources;
  }
}

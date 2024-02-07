package com.oracle.idm.agcs.grc.fn.requestTemplate.provider;

import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.grc.fn.requestTemplate.util.Util;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Operation;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.RequestTemplateOutput;

public abstract class TemplateProvider {
  public abstract RequestTemplateOutput getTemplateOutput(
      ApplicationConfig applicationConfig,
      ConnectedSystemConfig connectedSystemConfig,
      String entityName,
      Operation operationName);

  public RequestTemplateOutput getTemplateOutputWithAuthorizationValue(
      ApplicationConfig applicationConfig,
      ConnectedSystemConfig connectedSystemConfig,
      String entityName,
      Operation operationName) {
    System.err.println(
        String.format(
            "start getting request template data from json file for application %s , entityName %s and operationName %s.",
            applicationConfig.getApplication().name(), entityName, operationName));

    RequestTemplateOutput templateOutputFromResources =
        Util.getTemplateOutputFromResources(
            applicationConfig.getApplication(), entityName, operationName);

    System.err.println(
        String.format(
            "finished getting request template data from json file for application %s , entityName %s and operationName %s.",
            applicationConfig.getApplication().name(), entityName, operationName));

    return Util.getTemplateWithAuthorizationToken(
        templateOutputFromResources, applicationConfig, connectedSystemConfig);
  }

  public RequestTemplateOutput getTemplateOutputFromResources(
      ApplicationConfig applicationConfig, String entityName, Operation operationName) {
    System.err.println(
        String.format(
            "start getting request template data from json file for application %s , entityName %s and operationName %s.",
            applicationConfig.getApplication().name(), entityName, operationName));

    RequestTemplateOutput templateOutputFromResources =
        Util.getTemplateOutputFromResources(
            applicationConfig.getApplication(), entityName, operationName);

    System.err.println(
        String.format(
            "finished getting request template data from json file for application %s , entityName %s and operationName %s.",
            applicationConfig.getApplication().name(), entityName, operationName));

    return templateOutputFromResources;
  }
}

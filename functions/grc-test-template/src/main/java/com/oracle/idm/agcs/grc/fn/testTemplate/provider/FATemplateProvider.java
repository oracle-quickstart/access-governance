package com.oracle.idm.agcs.grc.fn.testTemplate.provider;

import com.oracle.idm.agcs.grc.fn.testTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.testTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.grc.fn.testTemplate.util.Util;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.RequestTemplateOutput;

public class FATemplateProvider extends TemplateProvider {
  @Override
  public RequestTemplateOutput getTemplateOutput(
      ApplicationConfig applicationConfig, ConnectedSystemConfig connectedSystemConfig) {
    System.err.println(
        String.format(
            "start getting test template data from json file for application %s.",
            applicationConfig.getApplication().name()));

    RequestTemplateOutput templateOutputFromResources =
        Util.getTemplateOutputFromResources(applicationConfig.getApplication());

    System.err.println(
        String.format(
            "finished getting test template data from json file for application %s.",
            applicationConfig.getApplication().name()));

    return Util.getTemplateWithAuthorizationToken(
        templateOutputFromResources, applicationConfig, connectedSystemConfig);
  }
}

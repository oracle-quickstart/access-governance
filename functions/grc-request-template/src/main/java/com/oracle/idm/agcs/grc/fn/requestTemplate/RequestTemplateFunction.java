package com.oracle.idm.agcs.grc.fn.requestTemplate;

import com.fnproject.fn.api.FnConfiguration;
import com.fnproject.fn.api.RuntimeContext;
import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.requestTemplate.config.Config;
import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.grc.fn.requestTemplate.manager.TemplateManager;
import com.oracle.idm.agcs.grc.fn.requestTemplate.util.Util;
import com.oracle.idm.agcs.icfconnectors.commons.model.input.RequestTemplateInput;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.RequestTemplateOutput;

import java.util.List;

public class RequestTemplateFunction {
  private static List<ApplicationConfig> applicationConfigs;
  private static String configFilePath = "/config.yaml";

  @FnConfiguration
  public void config(RuntimeContext ctx) {
    System.err.println("Start RequestTemplateFunction configuration initialization.");
    Config config = Util.getConfigFromYaml(configFilePath);
    applicationConfigs = Util.getApplicationConfigsFromYaml(config.getApplications());
    System.err.println("Finished RequestTemplateFunction configuration initialization.");
  }

  public RequestTemplateOutput handleRequest(RequestTemplateInput input) {
    System.err.println("RequestTemplateFunction input is :: " + input);
    // validate input data and get corresponding application configuration
    ApplicationConfig applicationConfig =
        Util.getApplicationConfigForConnectedSystemName(applicationConfigs, input.getConnectedSystemName());
    ConnectedSystemConfig connectedSystemConfig =
        Util.getConnectedSystemConfigFromApplicationConfig(applicationConfig, input.getConnectedSystemName());
    // get request template output
    RequestTemplateOutput output =
        TemplateManager.getTemplateProvider(applicationConfig.getApplication())
            .getTemplateOutput(
                applicationConfig, connectedSystemConfig, input.getEntityName(), input.getOperationName());
    System.err.println("RequestTemplateFunction output is :: " + output);
    return output;
  }
}

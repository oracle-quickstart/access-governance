package com.oracle.idm.agcs.grc.fn.schemaTemplate.provider;

import com.oracle.idm.agcs.grc.fn.schemaTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.schemaTemplate.util.Util;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.SchemaTemplateOutput;

public class AzureAdTemplateProvider extends TemplateProvider {

  @Override
  public SchemaTemplateOutput getTemplateOutput(ApplicationConfig applicationConfig) {
    System.err.println(
        String.format(
            "start getting schema template data from json file for application %s.",
            applicationConfig.getApplication().name()));

    SchemaTemplateOutput templateOutputFromResources =
        Util.getTemplateOutputFromResources(applicationConfig.getApplication());

    System.err.println(
        String.format(
            "finished getting schema template data from json file for application %s.",
            applicationConfig.getApplication().name()));

    return templateOutputFromResources;
  }
}

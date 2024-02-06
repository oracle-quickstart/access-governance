package com.oracle.idm.agcs.grc.fn.schemaTemplate.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.oracle.idm.agcs.grc.fn.schemaTemplate.config.Application;
import com.oracle.idm.agcs.grc.fn.schemaTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.schemaTemplate.config.Config;
import com.oracle.idm.agcs.grc.fn.schemaTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.grc.fn.schemaTemplate.exception.BadRequestException;
import com.oracle.idm.agcs.grc.fn.schemaTemplate.exception.ProcessingFailedException;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.SchemaTemplateOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {

  public static final String applicationConfigFilePath =
      "/applications/<APPLICATION_NAME>/config.yaml";
  public static final String templateJsonFilePath =
      "/applications/<APPLICATION_NAME>/TEMPLATE.json";
  public static final String applicationNamePlaceHolder = "<APPLICATION_NAME>";

  public static final ObjectMapper objectMapper = new ObjectMapper();

  private Util() {
    throw new IllegalStateException("Util is a Utility class");
  }

  public static Config getConfigFromYaml(String filePath) {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    mapper.findAndRegisterModules();
    try {
      return mapper.readValue(Util.class.getResourceAsStream(filePath), Config.class);
    } catch (IOException e) {
      System.err.println(
          "SchemaTemplateFunction configuration initialization is failed while mapping config yaml to config model.");
      throw new ProcessingFailedException(
          "SchemaTemplateFunction configuration initialization is failed while mapping config yaml to config model.",
          e);
    }
  }

  public static List<ApplicationConfig> getApplicationConfigsFromYaml(
      List<String> applicationNames) {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    mapper.findAndRegisterModules();
    List<ApplicationConfig> applications = new ArrayList<>();
    for (String applicationName : applicationNames) {
      try {
        String configFilePath =
            applicationConfigFilePath.replace(applicationNamePlaceHolder, applicationName);
        applications.add(
            mapper.readValue(
                Util.class.getResourceAsStream(configFilePath), ApplicationConfig.class));
      } catch (IOException e) {
        System.err.println(
            String.format(
                "SchemaTemplateFunction application %s configuration initialization is failed while mapping config yaml to config model.",
                applicationName));
        throw new ProcessingFailedException(
            String.format(
                "SchemaTemplateFunction application %s configuration initialization is failed while mapping config yaml to config model.",
                applicationName),
            e);
      }
    }
    return applications;
  }

  public static ApplicationConfig getApplicationConfigForConnectedSystemName(
      List<ApplicationConfig> applicationConfigs, String ConnectedSystemName) {
    // validate ConnectedSystemName is blank or not
    if (null == ConnectedSystemName || ConnectedSystemName.trim().length() == 0) {
      System.err.println("SchemaTemplateFunction input is invalid. ConnectedSystemName is not present.");
      throw new BadRequestException(
          "SchemaTemplateFunction input is invalid. ConnectedSystemName is not present.");
    }
    // get ApplicationConfiguration corresponding to ConnectedSystemName
    ApplicationConfig applicationConfiguration = null;
    for (ApplicationConfig applicationConfig : applicationConfigs) {
      if (null != applicationConfig.getConnectedSystemConfigs()
          && !applicationConfig.getConnectedSystemConfigs().isEmpty()) {
        for (ConnectedSystemConfig connectedSystemConfig : applicationConfig.getConnectedSystemConfigs()) {
          if (null != connectedSystemConfig
              && null != connectedSystemConfig.getConnectedSystemName()
              && connectedSystemConfig.getConnectedSystemName().equalsIgnoreCase(ConnectedSystemName)) {
            applicationConfiguration = applicationConfig;
          }
        }
      }
    }
    // validate applicationConfig corresponding to ConnectedSystemName is present or not
    if (null == applicationConfiguration) {
      System.err.println(
          String.format("SchemaTemplateFunction input ConnectedSystemName %s is invalid", ConnectedSystemName));
      throw new BadRequestException(
          String.format("SchemaTemplateFunction input ConnectedSystemName %s is invalid", ConnectedSystemName));
    }

    return applicationConfiguration;
  }

  public static SchemaTemplateOutput getTemplateOutputFromResources(Application application) {
    try {
      String jsonFilePath =
          templateJsonFilePath.replace(applicationNamePlaceHolder, application.name());
      return objectMapper.readValue(
          Util.class.getResourceAsStream(jsonFilePath), SchemaTemplateOutput.class);
    } catch (IOException e) {
      System.err.println(
          String.format(
              "SchemaTemplateFunction is failed while mapping schema template json to output model for application %s",
              application.name()));
      throw new ProcessingFailedException(
          String.format(
              "SchemaTemplateFunction is failed while mapping schema template json to output model for application %s",
              application.name()),
          e);
    }
  }
}

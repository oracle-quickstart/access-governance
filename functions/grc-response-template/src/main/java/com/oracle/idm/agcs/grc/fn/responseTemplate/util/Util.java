package com.oracle.idm.agcs.grc.fn.responseTemplate.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.oracle.idm.agcs.grc.fn.responseTemplate.config.Application;
import com.oracle.idm.agcs.grc.fn.responseTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.responseTemplate.config.Config;
import com.oracle.idm.agcs.grc.fn.responseTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.grc.fn.responseTemplate.exception.BadRequestException;
import com.oracle.idm.agcs.grc.fn.responseTemplate.exception.ProcessingFailedException;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Operation;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.ResponseTemplateOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {

  public static final String applicationConfigFilePath =
      "/applications/<APPLICATION_NAME>/config.yaml";
  public static final String templateJsonFilePath =
      "/applications/<APPLICATION_NAME>/<ENTITY_NAME>/<OPERATION_NAME>_TEMPLATE.json";
  public static final String applicationNamePlaceHolder = "<APPLICATION_NAME>";
  public static final String entityNamePlaceHolder = "<ENTITY_NAME>";
  public static final String operationNamePlaceHolder = "<OPERATION_NAME>";

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
          "ResponseTemplateFunction configuration initialization is failed while mapping config yaml to config model.");
      throw new ProcessingFailedException(
          "ResponseTemplateFunction configuration initialization is failed while mapping config yaml to config model.",
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
                "ResponseTemplateFunction application %s configuration initialization is failed while mapping config yaml to config model.",
                applicationName));
        throw new ProcessingFailedException(
            String.format(
                "ResponseTemplateFunction application %s configuration initialization is failed while mapping config yaml to config model.",
                applicationName),
            e);
      }
    }
    return applications;
  }

  public static ApplicationConfig getApplicationConfigForConnectedSystemName(
      List<ApplicationConfig> applicationConfigs, String connectedSystemName) {
    // validate connectedSystemName is blank or not
    if (null == connectedSystemName || connectedSystemName.trim().length() == 0) {
      System.err.println("ResponseTemplateFunction input is invalid. connectedSystemName is not present.");
      throw new BadRequestException(
          "ResponseTemplateFunction input is invalid. connectedSystemName is not present.");
    }
    // get ApplicationConfiguration corresponding to connectedSystemName
    ApplicationConfig applicationConfiguration = null;
    for (ApplicationConfig applicationConfig : applicationConfigs) {
      if (null != applicationConfig.getConnectedSystemConfigs()
          && !applicationConfig.getConnectedSystemConfigs().isEmpty()) {
        for (ConnectedSystemConfig connectedSystemConfig : applicationConfig.getConnectedSystemConfigs()) {
          if (null != connectedSystemConfig
              && null != connectedSystemConfig.getConnectedSystemName()
              && connectedSystemConfig.getConnectedSystemName().equalsIgnoreCase(connectedSystemName)) {
            applicationConfiguration = applicationConfig;
          }
        }
      }
    }
    // validate applicationConfig corresponding to connectedSystemName is present or not
    if (null == applicationConfiguration) {
      System.err.println(
          String.format("ResponseTemplateFunction input connectedSystemName %s is invalid", connectedSystemName));
      throw new BadRequestException(
          String.format("ResponseTemplateFunction input connectedSystemName %s is invalid", connectedSystemName));
    }

    return applicationConfiguration;
  }

  public static ResponseTemplateOutput getTemplateOutputFromResources(
      Application application, String entityName, Operation operationName) {
    try {
      String jsonFilePath =
          templateJsonFilePath
              .replace(applicationNamePlaceHolder, application.name())
              .replace(entityNamePlaceHolder, entityName)
              .replace(operationNamePlaceHolder, operationName.name());
      return objectMapper.readValue(
          Util.class.getResourceAsStream(jsonFilePath), ResponseTemplateOutput.class);
    } catch (IOException e) {
      System.err.println(
          String.format(
              "ResponseTemplateFunction is failed while mapping response template json to output model for application %s , entityName %s and operationName %s.",
              application.name(), entityName, operationName.name()));
      throw new ProcessingFailedException(
          String.format(
              "ResponseTemplateFunction is failed while mapping response template json to output model for application %s , entityName %s and operationName %s.",
              application.name(), entityName, operationName.name()),
          e);
    }
  }
}

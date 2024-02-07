package com.oracle.idm.agcs.grc.fn.testTemplate.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.oracle.idm.agcs.grc.fn.testTemplate.config.Application;
import com.oracle.idm.agcs.grc.fn.testTemplate.config.ApplicationConfig;
import com.oracle.idm.agcs.grc.fn.testTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.grc.fn.testTemplate.manager.AuthenticationManager;
import com.oracle.idm.agcs.grc.fn.testTemplate.config.Config;
import com.oracle.idm.agcs.grc.fn.testTemplate.exception.BadRequestException;
import com.oracle.idm.agcs.grc.fn.testTemplate.exception.ProcessingFailedException;
import com.oracle.idm.agcs.icfconnectors.commons.model.KeyValue;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.RequestTemplateOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {

  public static final String HEADER_NAME_AUTHORIZATION = "Authorization";
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
          "TestTemplateFunction configuration initialization is failed while mapping config yaml to config model.");
      throw new ProcessingFailedException(
          "TestTemplateFunction configuration initialization is failed while mapping config yaml to config model.",
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
                "TestTemplateFunction application %s configuration initialization is failed while mapping config yaml to config model.",
                applicationName));
        throw new ProcessingFailedException(
            String.format(
                "TestTemplateFunction application %s configuration initialization is failed while mapping config yaml to config model.",
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
      System.err.println("TestTemplateFunction input is invalid. connectedSystemName is not present.");
      throw new BadRequestException(
          "TestTemplateFunction input is invalid. connectedSystemName is not present.");
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
          String.format("TestTemplateFunction input connectedSystemName %s is invalid", connectedSystemName));
      throw new BadRequestException(
          String.format("TestTemplateFunction input connectedSystemName %s is invalid", connectedSystemName));
    }

    return applicationConfiguration;
  }

  public static ConnectedSystemConfig getConnectedSystemConfigFromApplicationConfig(
      ApplicationConfig applicationConfig, String connectedSystemName) {
    ConnectedSystemConfig connectedSystemConfig =
        applicationConfig.getConnectedSystemConfigs().stream()
            .filter(obj -> obj.getConnectedSystemName().equalsIgnoreCase(connectedSystemName))
            .findFirst()
            .orElse(null);
    // validate connectedSystemConfig is present or not
    if (null == connectedSystemConfig || null == connectedSystemConfig.getAuthenticationDetail()) {
      System.err.println(
          String.format(
              "TestTemplateFunction connectedSystemConfig or its authenticationProperties is not preset for input connectedSystemName %s",
                  connectedSystemName));
      throw new BadRequestException(
          String.format(
              "TestTemplateFunction connectedSystemConfig or its authenticationProperties is not preset for input connectedSystemName %s",
                  connectedSystemName));
    }
    return connectedSystemConfig;
  }

  public static RequestTemplateOutput getTemplateOutputFromResources(Application application) {
    try {
      String jsonFilePath =
          templateJsonFilePath.replace(applicationNamePlaceHolder, application.name());
      return objectMapper.readValue(
          Util.class.getResourceAsStream(jsonFilePath), RequestTemplateOutput.class);
    } catch (IOException e) {
      System.err.println(
          String.format(
              "TestTemplateFunction is failed while mapping test template json to output model for application %s",
              application.name()));
      throw new ProcessingFailedException(
          String.format(
              "TestTemplateFunction is failed while mapping test template json to output model for application %s",
              application.name()),
          e);
    }
  }

  public static RequestTemplateOutput getTemplateWithAuthorizationToken(
      RequestTemplateOutput templateOutputFromResources,
      ApplicationConfig applicationConfig,
      ConnectedSystemConfig connectedSystemConfig) {
    String authorizationValue =
        AuthenticationManager.getAuthenticationProvider(applicationConfig.getApplication())
            .getAuthorizationValue(connectedSystemConfig);
    System.err.println(
        "before value replacement templateOutputFromResources is :: "
            + templateOutputFromResources);

    replaceAuthorizationHeaderValue(templateOutputFromResources, authorizationValue);
    replaceUriSchemeValue(
        templateOutputFromResources, connectedSystemConfig.getApplicationInstanceDetail().get("scheme"));
    replaceUriHostValue(
        templateOutputFromResources, connectedSystemConfig.getApplicationInstanceDetail().get("host"));

    System.err.println(
        "after value replacement templateOutputFromResources is :: " + templateOutputFromResources);
    return templateOutputFromResources;
  }

  private static void replaceAuthorizationHeaderValue(
      RequestTemplateOutput testTemplateOutputFromResources, String authorizationValue) {
    for (KeyValue obj : testTemplateOutputFromResources.getHeaders()) {
      if (obj.getName().equalsIgnoreCase(HEADER_NAME_AUTHORIZATION)) {
        obj.setValue(authorizationValue);
      }
    }
  }

  private static void replaceUriSchemeValue(
      RequestTemplateOutput testTemplateOutputFromResources, String schemeValue) {
    testTemplateOutputFromResources.getUri().setScheme(schemeValue);
    if (null != testTemplateOutputFromResources.getSubRequests()
        && testTemplateOutputFromResources.getSubRequests().size() > 0) {
      for (RequestTemplateOutput subRequest : testTemplateOutputFromResources.getSubRequests()) {
        subRequest.getUri().setScheme(schemeValue);
      }
    }
  }

  private static void replaceUriHostValue(
      RequestTemplateOutput testTemplateOutputFromResources, String hostValue) {
    testTemplateOutputFromResources.getUri().setHost(hostValue);
    if (null != testTemplateOutputFromResources.getSubRequests()
        && testTemplateOutputFromResources.getSubRequests().size() > 0) {
      for (RequestTemplateOutput subRequest : testTemplateOutputFromResources.getSubRequests()) {
        subRequest.getUri().setHost(hostValue);
      }
    }
  }
}

package com.oracle.idm.agcs.grc.fn.testTemplate.config;

import lombok.Data;

import java.util.Map;

@Data
public class ConnectedSystemConfig {
  private String connectedSystemName;
  private Map<String, String> authenticationDetail;
  private Map<String, String> applicationInstanceDetail;
}

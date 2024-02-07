package com.oracle.idm.agcs.grc.fn.schemaTemplate.config;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationConfig {
  private Application application;
  private List<ConnectedSystemConfig> connectedSystemConfigs;
}

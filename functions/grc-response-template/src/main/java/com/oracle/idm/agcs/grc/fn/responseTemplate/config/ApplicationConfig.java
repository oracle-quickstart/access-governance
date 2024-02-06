package com.oracle.idm.agcs.grc.fn.responseTemplate.config;

import java.util.List;
import lombok.Data;

@Data
public class ApplicationConfig {
  private Application application;
  private List<ConnectedSystemConfig> connectedSystemConfigs;
}

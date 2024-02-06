package com.oracle.idm.agcs.grc.fn.responseTemplate.manager;

import com.oracle.idm.agcs.grc.fn.responseTemplate.config.Application;
import com.oracle.idm.agcs.grc.fn.responseTemplate.exception.BadRequestException;
import com.oracle.idm.agcs.grc.fn.responseTemplate.provider.AzureAdTemplateProvider;
import com.oracle.idm.agcs.grc.fn.responseTemplate.provider.FATemplateProvider;
import com.oracle.idm.agcs.grc.fn.responseTemplate.provider.IDCSTemplateProvider;
import com.oracle.idm.agcs.grc.fn.responseTemplate.provider.TemplateProvider;

public class TemplateManager {

  public static TemplateProvider getTemplateProvider(Application provider) {
    switch (provider) {
      case idcs:
        return new IDCSTemplateProvider();
      case fa:
        return new FATemplateProvider();
      case azuread:
        return new AzureAdTemplateProvider();
      default:
        System.err.println(
            String.format(
                "input provider %s is invalid corresponding TemplateProvider not yet implemented",
                provider.name()));
        throw new BadRequestException(
            String.format(
                "input provider %s is invalid corresponding TemplateProvider not yet implemented",
                provider.name()));
    }
  }
}

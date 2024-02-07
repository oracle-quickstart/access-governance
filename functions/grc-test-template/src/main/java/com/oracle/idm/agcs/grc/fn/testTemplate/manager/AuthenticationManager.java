package com.oracle.idm.agcs.grc.fn.testTemplate.manager;

import com.oracle.idm.agcs.grc.fn.testTemplate.config.Application;
import com.oracle.idm.agcs.grc.fn.testTemplate.exception.BadRequestException;
import com.oracle.idm.agcs.grc.fn.testTemplate.provider.AuthenticationProvider;
import com.oracle.idm.agcs.grc.fn.testTemplate.provider.AzureAdAuthenticationProvider;
import com.oracle.idm.agcs.grc.fn.testTemplate.provider.FAAuthenticationProvider;
import com.oracle.idm.agcs.grc.fn.testTemplate.provider.IDCSAuthenticationProvider;

public class AuthenticationManager {

  public static AuthenticationProvider getAuthenticationProvider(Application provider) {
    switch (provider) {
      case idcs:
        return new IDCSAuthenticationProvider();
      case fa:
        return new FAAuthenticationProvider();
      case azuread:
        return new AzureAdAuthenticationProvider();
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

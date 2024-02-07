package com.oracle.idm.agcs.grc.fn.requestTemplate.manager;

import com.oracle.idm.agcs.grc.fn.requestTemplate.config.Application;
import com.oracle.idm.agcs.grc.fn.requestTemplate.exception.BadRequestException;
import com.oracle.idm.agcs.grc.fn.requestTemplate.provider.AuthenticationProvider;
import com.oracle.idm.agcs.grc.fn.requestTemplate.provider.AzureAdAuthenticationProvider;
import com.oracle.idm.agcs.grc.fn.requestTemplate.provider.FAAuthenticationProvider;
import com.oracle.idm.agcs.grc.fn.requestTemplate.provider.IDCSAuthenticationProvider;

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

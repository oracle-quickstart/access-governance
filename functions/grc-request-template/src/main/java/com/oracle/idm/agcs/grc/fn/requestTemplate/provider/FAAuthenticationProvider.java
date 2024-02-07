package com.oracle.idm.agcs.grc.fn.requestTemplate.provider;

import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ConnectedSystemConfig;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FAAuthenticationProvider extends AuthenticationProvider {
  @Override
  public String getAuthorizationValue(ConnectedSystemConfig connectedSystemConfig) {
    String authHeader =
        connectedSystemConfig
            .getAuthenticationDetail()
            .get("clientCode")
            .concat(":")
            .concat(connectedSystemConfig.getAuthenticationDetail().get("clientSecret"));
    return TOKEN_PREFIX_BASIC
        + Base64.getEncoder().encodeToString(authHeader.getBytes(StandardCharsets.UTF_8));
  }
}

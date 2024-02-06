package com.oracle.idm.agcs.grc.fn.requestTemplate.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ConnectedSystemConfig;

public abstract class AuthenticationProvider {

  public static final String TOKEN_PREFIX_BASIC = "Basic ";
  public static final String TOKEN_PREFIX_BEARER = "Bearer ";
  public static final String HEADER_NAME_CONTENT_TYPE = "Content-Type";
  public static final String HEADER_NAME_AUTHORIZATION = "Authorization";
  public static final String ACCESS_TOKEN_ATTRIBUTE = "access_token";
  public static final String HEADER_VALUE_CONTENT_TYPE_FORM_URL_ENCODED =
      "application/x-www-form-urlencoded";

  public static final ObjectMapper objectMapper = new ObjectMapper();

  public abstract String getAuthorizationValue(ConnectedSystemConfig connectedSystemConfig);
}

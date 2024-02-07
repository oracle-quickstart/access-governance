package com.oracle.idm.agcs.grc.fn.requestTemplate.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.grc.fn.requestTemplate.exception.ProcessingFailedException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class IDCSAuthenticationProvider extends AuthenticationProvider {

  @Override
  public String getAuthorizationValue(ConnectedSystemConfig connectedSystemConfig) {
    String url =
        connectedSystemConfig
            .getAuthenticationDetail()
            .get("scheme")
            .concat("://")
            .concat(connectedSystemConfig.getAuthenticationDetail().get("host"))
            .concat(connectedSystemConfig.getAuthenticationDetail().get("path"));
    String requestBody = "grant_type=client_credentials&scope=urn%3Aopc%3Aidm%3A__myscopes__";
    String authHeader =
        connectedSystemConfig
            .getAuthenticationDetail()
            .get("clientCode")
            .concat(":")
            .concat(connectedSystemConfig.getAuthenticationDetail().get("clientSecret"));
    final HttpClient client = HttpClient.newHttpClient();
    final HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header(HEADER_NAME_CONTENT_TYPE, HEADER_VALUE_CONTENT_TYPE_FORM_URL_ENCODED)
            .header(
                HEADER_NAME_AUTHORIZATION,
                TOKEN_PREFIX_BASIC
                    + Base64.getEncoder()
                        .encodeToString(authHeader.getBytes(StandardCharsets.UTF_8)))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();
    HttpResponse<String> response;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      System.err.println(
          "IDCSAuthenticationProvider Auth Token API HttpRequest failed due to IOException."
              + e.getMessage());
      throw new ProcessingFailedException(
          "IDCSAuthenticationProvider Auth Token API HttpRequest failed due to IOException.", e);
    } catch (InterruptedException e) {
      System.err.println(
          "IDCSAuthenticationProvider Auth Token API HttpRequest failed due to InterruptedException."
              + e.getMessage());
      throw new ProcessingFailedException(
          "IDCSAuthenticationProvider Auth Token API HttpRequest failed due to InterruptedException.",
          e);
    }
    System.err.println(
        "IDCSAuthenticationProvider Auth Token API HttpResponse is :: " + response.body());
    JsonNode jsonNode;
    try {
      jsonNode = objectMapper.readTree(response.body());
    } catch (JsonProcessingException e) {
      System.err.println(
          "IDCSAuthenticationProvider Auth Token API HttpResponse parsing to json is failed.");
      throw new ProcessingFailedException(
          "IDCSAuthenticationProvider Auth Token API HttpResponse parsing to json is failed.", e);
    }
    return TOKEN_PREFIX_BEARER.concat(jsonNode.get(ACCESS_TOKEN_ATTRIBUTE).textValue());
  }
}

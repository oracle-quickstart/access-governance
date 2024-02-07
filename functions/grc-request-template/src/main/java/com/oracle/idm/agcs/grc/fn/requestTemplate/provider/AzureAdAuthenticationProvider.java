package com.oracle.idm.agcs.grc.fn.requestTemplate.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.oracle.idm.agcs.grc.fn.requestTemplate.config.ConnectedSystemConfig;
import com.oracle.idm.agcs.grc.fn.requestTemplate.exception.ProcessingFailedException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AzureAdAuthenticationProvider extends AuthenticationProvider {
  @Override
  public String getAuthorizationValue(ConnectedSystemConfig connectedSystemConfig) {
    String url =
        connectedSystemConfig
            .getAuthenticationDetail()
            .get("scheme")
            .concat("://")
            .concat(connectedSystemConfig.getAuthenticationDetail().get("host"))
            .concat(connectedSystemConfig.getAuthenticationDetail().get("path"));
    String requestBody =
        "client_id="
            + connectedSystemConfig.getAuthenticationDetail().get("clientCode")
            + "&client_secret="
            + connectedSystemConfig.getAuthenticationDetail().get("clientSecret")
            + "&grant_type="
            + connectedSystemConfig.getAuthenticationDetail().get("grantType")
            + "&scope="
            + connectedSystemConfig.getAuthenticationDetail().get("scope");

    final HttpClient client = getHttpClient(connectedSystemConfig);
    final HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header(HEADER_NAME_CONTENT_TYPE, HEADER_VALUE_CONTENT_TYPE_FORM_URL_ENCODED)
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();
    HttpResponse<String> response;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      System.err.println(
          "AzureAdAuthenticationProvider Auth Token API HttpRequest failed due to IOException."
              + e.getMessage());
      throw new ProcessingFailedException(
          "AzureAdAuthenticationProvider Auth Token API HttpRequest failed due to IOException.", e);
    } catch (InterruptedException e) {
      System.err.println(
          "AzureAdAuthenticationProvider Auth Token API HttpRequest failed due to InterruptedException."
              + e.getMessage());
      throw new ProcessingFailedException(
          "AzureAdAuthenticationProvider Auth Token API HttpRequest failed due to InterruptedException.",
          e);
    }
    System.err.println(
        "AzureAdAuthenticationProvider Auth Token API HttpResponse is :: " + response.body());
    JsonNode jsonNode;
    try {
      jsonNode = objectMapper.readTree(response.body());
    } catch (JsonProcessingException e) {
      System.err.println(
          "AzureAdAuthenticationProvider Auth Token API HttpResponse parsing to json is failed.");
      throw new ProcessingFailedException(
          "AzureAdAuthenticationProvider Auth Token API HttpResponse parsing to json is failed.",
          e);
    }
    return TOKEN_PREFIX_BEARER.concat(jsonNode.get(ACCESS_TOKEN_ATTRIBUTE).textValue());
  }

  private HttpClient getHttpClient(ConnectedSystemConfig connectedSystemConfig) {
    if (null == connectedSystemConfig.getAuthenticationDetail().get("proxyHost")
        || null == connectedSystemConfig.getAuthenticationDetail().get("proxyPort")) {
      return HttpClient.newHttpClient();
    }
    return HttpClient.newBuilder()
        .proxy(
            ProxySelector.of(
                new InetSocketAddress(
                    connectedSystemConfig.getAuthenticationDetail().get("proxyHost"),
                    Integer.parseInt(connectedSystemConfig.getAuthenticationDetail().get("proxyPort")))))
        .build();
  }
}

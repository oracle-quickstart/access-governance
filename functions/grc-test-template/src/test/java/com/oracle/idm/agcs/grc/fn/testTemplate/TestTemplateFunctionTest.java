package com.oracle.idm.agcs.grc.fn.testTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnproject.fn.testing.FnResult;
import com.fnproject.fn.testing.FnTestingRule;
import com.oracle.idm.agcs.icfconnectors.commons.model.input.TestTemplateInput;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.RequestTemplateOutput;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class TestTemplateFunctionTest {

  @Rule public final FnTestingRule testing = FnTestingRule.createDefault();

  ObjectMapper mapper = new ObjectMapper();
  String functionMethodName = "handleRequest";
  static String idcsConnectedSystemName;
  static String faConnectedSystemName;
  static String azureAdConnectedSystemName;

  @BeforeClass
  public static void loadConfig() {
    String resourceName = "config.properties";
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    Properties props = new Properties();
    try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
      props.load(resourceStream);
      idcsConnectedSystemName = getPropertyValue(props, "idcsConnectedSystemName");
      faConnectedSystemName = getPropertyValue(props, "faConnectedSystemName");
      azureAdConnectedSystemName = getPropertyValue(props, "azureAdConnectedSystemName");
    } catch (IOException e) {
	    throw new RuntimeException(e);
    }
  }

  @Test
  public void shouldReturnTestTemplateForIdcs() throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    TestTemplateInput input = TestTemplateInput.builder().connectedSystemName(idcsConnectedSystemName).build();
    RequestTemplateOutput output = getTestTemplateOutput(input);
    assertEquals("Test connectivity invoking schema API", output.getName());
  }

  @Test
  public void shouldReturnTestTemplateForFa() throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    TestTemplateInput input = TestTemplateInput.builder().connectedSystemName(faConnectedSystemName).build();
    RequestTemplateOutput output = getTestTemplateOutput(input);
    assertEquals("Test connectivity invoking schema API", output.getName());
  }

  @Test
  public void shouldReturnTestTemplateForAzureAd() throws JsonProcessingException {
    Assume.assumeNotNull(azureAdConnectedSystemName);
    TestTemplateInput input = TestTemplateInput.builder().connectedSystemName(azureAdConnectedSystemName).build();
    RequestTemplateOutput output = getTestTemplateOutput(input);
    assertEquals("Test connectivity invoking get Users API", output.getName());
  }

  private RequestTemplateOutput getTestTemplateOutput(TestTemplateInput input)
      throws JsonProcessingException {
    String requestBody = mapper.writeValueAsString(input);
    testing.givenEvent().withBody(requestBody).enqueue();
    testing.thenRun(TestTemplateFunction.class, functionMethodName);

    FnResult result = testing.getOnlyResult();
    RequestTemplateOutput output =
        mapper.readValue(result.getBodyAsString(), RequestTemplateOutput.class);
    return output;
  }

  private static String getPropertyValue(Properties props, String key) {
    return props.getProperty(key).trim().isEmpty() ? null : props.getProperty(key).trim();
  }
}

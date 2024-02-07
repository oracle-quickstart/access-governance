package com.oracle.idm.agcs.grc.fn.schemaTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnproject.fn.testing.*;
import com.oracle.idm.agcs.icfconnectors.commons.model.input.SchemaTemplateInput;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.SchemaTemplateOutput;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.*;

public class SchemaTemplateFunctionTest {

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
  public void shouldReturnFiveEntityInSchemaTemplateForIdcsConnectedSystem() throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    SchemaTemplateInput input = SchemaTemplateInput.builder().connectedSystemName(idcsConnectedSystemName).build();
    SchemaTemplateOutput output = getSchemaTemplateOutput(input);
    assertEquals(5, output.getSchemaTemplates().size());
  }

  @Test
  public void shouldReturnFiveEntityInSchemaTemplateForFaConnectedSystem() throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    SchemaTemplateInput input = SchemaTemplateInput.builder().connectedSystemName(faConnectedSystemName).build();
    SchemaTemplateOutput output = getSchemaTemplateOutput(input);
    assertEquals(6, output.getSchemaTemplates().size());
  }

  @Test
  public void shouldReturnFiveEntityInSchemaTemplateForazureadConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(azureAdConnectedSystemName);
    SchemaTemplateInput input = SchemaTemplateInput.builder().connectedSystemName(azureAdConnectedSystemName).build();
    SchemaTemplateOutput output = getSchemaTemplateOutput(input);
    assertEquals(8, output.getSchemaTemplates().size());
  }

  private SchemaTemplateOutput getSchemaTemplateOutput(SchemaTemplateInput input)
      throws JsonProcessingException {
    String requestBody = mapper.writeValueAsString(input);
    testing.givenEvent().withBody(requestBody).enqueue();
    testing.thenRun(SchemaTemplateFunction.class, functionMethodName);

    FnResult result = testing.getOnlyResult();
    SchemaTemplateOutput output =
        mapper.readValue(result.getBodyAsString(), SchemaTemplateOutput.class);
    return output;
  }

  private static String getPropertyValue(Properties props, String key) {
    return props.getProperty(key).trim().isEmpty() ? null : props.getProperty(key).trim();
  }
}

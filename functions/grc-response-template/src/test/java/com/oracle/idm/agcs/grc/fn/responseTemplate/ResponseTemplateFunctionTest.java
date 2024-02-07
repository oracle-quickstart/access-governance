package com.oracle.idm.agcs.grc.fn.responseTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnproject.fn.testing.FnResult;
import com.fnproject.fn.testing.FnTestingRule;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Operation;
import com.oracle.idm.agcs.icfconnectors.commons.model.input.ResponseTemplateInput;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.ResponseTemplateOutput;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class ResponseTemplateFunctionTest {

  @Rule public final FnTestingRule testing = FnTestingRule.createDefault();

  static String idcsConnectedSystemName;
  static String faConnectedSystemName;

  ObjectMapper mapper = new ObjectMapper();
  String functionMethodName = "handleRequest";
  String UserAsAccountEntity = "UserAsAccount";
  String UserAsIdentityEntity = "UserAsIdentity";
  String countriesEntityName = "countries";
  String languagesEntityName = "languages";
  String groupAsEntitlementEntityName = "GroupAsEntitlement";
  String roleAsEntitlementEntityName = "RoleAsEntitlement";
  String organizationEntityName = "Organization";

  @BeforeClass
  public static void loadConfig() {
    String resourceName = "config.properties";
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    Properties props = new Properties();
    try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
      props.load(resourceStream);
      idcsConnectedSystemName = getPropertyValue(props, "idcsConnectedSystemName");
      faConnectedSystemName = getPropertyValue(props, "faConnectedSystemName");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void shouldReturnCreateUserAsAccountResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.CREATE)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<JP>$.id</JP>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnCreateUserAsAccountResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.CREATE)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<JP>$.id</JP>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnGetUserAsAccountResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.GET)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<JP>$.id</JP>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnGetUserAsAccountResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.GET)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<JP>$.id</JP>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnSearchUserAsAccountResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.SEARCH)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("<JP>$.Resources[*]</JP>", output.getItems());
  }

  @Test
  public void shouldReturnSearchUserAsAccountResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.SEARCH)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("<JP>$.Resources[*]</JP>", output.getItems());
  }

  @Test
  public void shouldReturnUpdateUserAsAccountResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.UPDATE)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<EL>attributes.get('uid').get(0)</EL>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnDisableUserAsAccountResponseTemplateForIdcsConnectedSystem()
          throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
            ResponseTemplateInput.builder()
                    .connectedSystemName(idcsConnectedSystemName)
                    .entityName(UserAsAccountEntity)
                    .operationName(Operation.DISABLE)
                    .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<EL>attributes.get('uid').get(0)</EL>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnEnableUserAsAccountResponseTemplateForIdcsConnectedSystem()
          throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
            ResponseTemplateInput.builder()
                    .connectedSystemName(idcsConnectedSystemName)
                    .entityName(UserAsAccountEntity)
                    .operationName(Operation.ENABLE)
                    .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<EL>attributes.get('uid').get(0)</EL>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnUpdateUserAsAccountResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.UPDATE)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<EL>attributes.get('uid').get(0)</EL>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnAddChildDataUserAsAccountResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.ADD_CHILD_DATA)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<EL>attributes.get('uid').get(0)</EL>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnAddChildDataUserAsAccountResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.ADD_CHILD_DATA)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<EL>attributes.get('uid').get(0)</EL>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnRemoveChildDataUserAsAccountResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.REMOVE_CHILD_DATA)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<EL>attributes.get('uid').get(0)</EL>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnRemoveChildDataUserAsAccountResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.REMOVE_CHILD_DATA)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<EL>attributes.get('uid').get(0)</EL>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnGetGroupAsEntitlementResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(groupAsEntitlementEntityName)
            .operationName(Operation.GET)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<JP>$.id</JP>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnSearchGroupAsEntitlementResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(groupAsEntitlementEntityName)
            .operationName(Operation.SEARCH)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("<JP>$.Resources[*]</JP>", output.getItems());
  }

  @Test
  public void shouldReturnGetGroupAsEntitlementResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(roleAsEntitlementEntityName)
            .operationName(Operation.GET)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<JP>$.id</JP>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnSearchGroupAsEntitlementResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(roleAsEntitlementEntityName)
            .operationName(Operation.SEARCH)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("<JP>$.Resources[*]</JP>", output.getItems());
  }

  @Test
  public void shouldReturnGetUserAsIdentityResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsIdentityEntity)
            .operationName(Operation.GET)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<JP>$.id</JP>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnGetUserAsIdentityResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsIdentityEntity)
            .operationName(Operation.GET)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("uid", output.getAttributes().get(0).getName());
    assertEquals("<JP>$.id</JP>", output.getAttributes().get(0).getValue());
  }

  @Test
  public void shouldReturnSearchUserAsIdentityResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsIdentityEntity)
            .operationName(Operation.SEARCH)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("<JP>$.Resources[*]</JP>", output.getItems());
  }

  @Test
  public void shouldReturnSearchUserAsIdentityResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsIdentityEntity)
            .operationName(Operation.SEARCH)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("<JP>$.Resources[*]</JP>", output.getItems());
  }

  @Test
  public void shouldReturnSearchOrganizationResponseTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(organizationEntityName)
            .operationName(Operation.SEARCH)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("<JP>$.items[*]</JP>", output.getItems());
  }

  @Test
  public void shouldReturnGetCountriesResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(countriesEntityName)
            .operationName(Operation.SEARCH)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("<JP>$.attrValues[*]</JP>", output.getItems());
  }

  @Test
  public void shouldReturnGetLanguagesResponseTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    ResponseTemplateInput input =
        ResponseTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(languagesEntityName)
            .operationName(Operation.SEARCH)
            .build();
    ResponseTemplateOutput output = getResponseTemplateOutput(input);
    assertEquals("<JP>$.attrValues[*]</JP>", output.getItems());
  }

  private ResponseTemplateOutput getResponseTemplateOutput(ResponseTemplateInput input)
      throws JsonProcessingException {
    String requestBody = mapper.writeValueAsString(input);
    testing.givenEvent().withBody(requestBody).enqueue();
    testing.thenRun(ResponseTemplateFunction.class, functionMethodName);

    FnResult result = testing.getOnlyResult();
    ResponseTemplateOutput output =
        mapper.readValue(result.getBodyAsString(), ResponseTemplateOutput.class);
    return output;
  }

  private static String getPropertyValue(Properties props, String key) {
    return props.getProperty(key).trim().isEmpty() ? null : props.getProperty(key).trim();
  }
}

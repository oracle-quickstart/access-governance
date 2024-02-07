package com.oracle.idm.agcs.grc.fn.requestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnproject.fn.testing.FnResult;
import com.fnproject.fn.testing.FnTestingRule;
import com.oracle.idm.agcs.icfconnectors.commons.enums.Operation;
import com.oracle.idm.agcs.icfconnectors.commons.model.input.RequestTemplateInput;
import com.oracle.idm.agcs.icfconnectors.commons.model.output.RequestTemplateOutput;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class RequestTemplateFunctionTest {
  @Rule public final FnTestingRule testing = FnTestingRule.createDefault();

  ObjectMapper mapper = new ObjectMapper();

  static String faConnectedSystemName;
  static String idcsConnectedSystemName;
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
      faConnectedSystemName =  getPropertyValue(props, "faConnectedSystemName");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void shouldReturnCreateUserAsAccountRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.CREATE)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Create User", output.getName());
  }

  @Test
  public void shouldReturnCreateUserAsAccountRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.CREATE)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Create User", output.getName());
  }

  @Test
  public void shouldReturnGetUserAsAccountRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.GET)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Get User By ID", output.getName());
  }

  @Test
  public void shouldReturnGetUserAsAccountRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.GET)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Get User By ID", output.getName());
  }

  @Test
  public void shouldReturnSearchUserAsAccountRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.SEARCH)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Search Users sort by displayName", output.getName());
  }

  @Test
  public void shouldReturnSearchUserAsAccountRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.SEARCH)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Search Users sort by displayName", output.getName());
  }

  @Test
  public void shouldReturnDeleteUserAsAccountRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.DELETE)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Delete User", output.getName());
  }

  @Test
  public void shouldReturnDeleteUserAsAccountRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.DELETE)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Delete User", output.getName());
  }

  @Test
  public void shouldReturnUpdateUserAsAccountRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.UPDATE)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Update User", output.getName());
  }

  @Test
  public void shouldReturnDisableUserAsAccountRequestTemplateForIdcsConnectedSystem()
          throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
            RequestTemplateInput.builder()
                    .connectedSystemName(idcsConnectedSystemName)
                    .entityName(UserAsAccountEntity)
                    .operationName(Operation.DISABLE)
                    .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Disable User", output.getName());
  }

  @Test
  public void shouldReturnEnableUserAsAccountRequestTemplateForIdcsConnectedSystem()
          throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
            RequestTemplateInput.builder()
                    .connectedSystemName(idcsConnectedSystemName)
                    .entityName(UserAsAccountEntity)
                    .operationName(Operation.ENABLE)
                    .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Enable User", output.getName());
  }

  @Test
  public void shouldReturnUpdateUserAsAccountRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.UPDATE)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Update User", output.getName());
  }

  @Test
  public void shouldReturnAddChildDataUserAsAccountRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.ADD_CHILD_DATA)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("User Add Group membership", output.getName());
  }

  @Test
  public void shouldReturnAddChildDataUserAsAccountRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.ADD_CHILD_DATA)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("User Add Role membership", output.getName());
  }

  @Test
  public void shouldReturnRemoveChildDataUserAsAccountRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.REMOVE_CHILD_DATA)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("User Remove Group membership", output.getName());
  }

  @Test
  public void shouldReturnRemoveChildDataUserAsAccountRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsAccountEntity)
            .operationName(Operation.REMOVE_CHILD_DATA)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("User Remove Role membership", output.getName());
  }

  @Test
  public void shouldReturnGetGroupAsEntitlementRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(groupAsEntitlementEntityName)
            .operationName(Operation.GET)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Get Group", output.getName());
  }

  @Test
  public void shouldReturnGetRoleAsEntitlementRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(roleAsEntitlementEntityName)
            .operationName(Operation.GET)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Get Role", output.getName());
  }

  @Test
  public void shouldReturnSearchGroupAsEntitlementRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(groupAsEntitlementEntityName)
            .operationName(Operation.SEARCH)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Search Groups sort by displayName", output.getName());
  }

  @Test
  public void shouldReturnSearchRoleAsEntitlementRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(roleAsEntitlementEntityName)
            .operationName(Operation.SEARCH)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Search Role sort by category", output.getName());
  }

  @Test
  public void shouldReturnGetUserAsIdentityRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsIdentityEntity)
            .operationName(Operation.GET)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Get User As Identity By ID", output.getName());
  }

  @Test
  public void shouldReturnGetUserAsIdentityRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsIdentityEntity)
            .operationName(Operation.GET)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Get User By ID", output.getName());
  }

  @Test
  public void shouldReturnSearchUserAsIdentityRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(UserAsIdentityEntity)
            .operationName(Operation.SEARCH)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Search Users As Identity sort by displayName", output.getName());
  }

  @Test
  public void shouldReturnSearchUserAsIdentityRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(UserAsIdentityEntity)
            .operationName(Operation.SEARCH)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Search Users sort by displayName", output.getName());
  }

  @Test
  public void shouldReturnSearchOrganizationRequestTemplateForFaConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(faConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(faConnectedSystemName)
            .entityName(organizationEntityName)
            .operationName(Operation.SEARCH)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Search Organization sort by name", output.getName());
  }

  @Test
  public void shouldReturnGetCountriesRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(countriesEntityName)
            .operationName(Operation.SEARCH)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Get Country allowed values", output.getName());
  }

  @Test
  public void shouldReturnGetLanguagesRequestTemplateForIdcsConnectedSystem()
      throws JsonProcessingException {
    Assume.assumeNotNull(idcsConnectedSystemName);
    RequestTemplateInput input =
        RequestTemplateInput.builder()
            .connectedSystemName(idcsConnectedSystemName)
            .entityName(languagesEntityName)
            .operationName(Operation.SEARCH)
            .build();
    RequestTemplateOutput output = getRequestTemplateOutput(input);
    assertEquals("Get languages allowed values", output.getName());
  }

  private RequestTemplateOutput getRequestTemplateOutput(RequestTemplateInput input)
      throws JsonProcessingException {
    String requestBody = mapper.writeValueAsString(input);
    testing.givenEvent().withBody(requestBody).enqueue();
    testing.thenRun(RequestTemplateFunction.class, functionMethodName);

    FnResult result = testing.getOnlyResult();
    RequestTemplateOutput output =
        mapper.readValue(result.getBodyAsString(), RequestTemplateOutput.class);
    return output;
  }

  private static String getPropertyValue(Properties props, String key) {
    return props.getProperty(key).trim().isEmpty() ? null : props.getProperty(key).trim();
  }
}

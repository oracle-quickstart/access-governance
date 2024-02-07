## Pre-requirements

1. Maven 3.6.0, or higher
2. JDK 17
3. Latest container runtime docker/podman
4. Oracle OCI Subscription with access to manage Oracle Functions

## Deploy The Functions
1. Follow the [OCI Guide](https://docs.oracle.com/en-us/iaas/Content/Functions/Tasks/functionsquickstartlocalhost.htm#functionsquickstartlocalhost_topic_setup_your_tenancy) to setup your tenancy for function deployment.
2. [Create an application](https://docs.oracle.com/en-us/iaas/Content/Functions/Tasks/functionsquickstartlocalhost.htm#functionsquickstartlocalhost_topic_setup_Create_application).
3. [Setup your local environment](https://docs.oracle.com/en-us/iaas/Content/Functions/Tasks/functionsquickstartlocalhost.htm#functionsquickstartlocalhost_topic_start_setting_up_local_dev_environment).
4. Create an application in OCI. Replace with your subnetId.
```shell
fn create app agcs-generic-rest-connector --annotation oracle.com/oci/subnetIds='["<subnetId>"]'
```
5. Populate the config yaml files inside `src/main/resources` folder of each function accordingly.
```text
For Azure AD application
* Populate src/main/resources/azuread/config.yaml of each function

For IDCS application
* Populate src/main/resources/idcs/config.yaml of each function

For FA application
* Populate src/main/resources/fa/config.yaml of each function
```
6. Populate the `config.properties` inside `src/test/resources` folder of each function if you need to run the test cases.
7. Navigate to `functions` directory 
```shell
cd functions
```
8. Compile and package the functions.
```shell
mvn clean package
```
9. Navigate to the function which you want to deploy.
```shell
cd grc-schema-template
```
10. Deploy the function to OCI
```shell
fn -v deploy --app agcs-generic-rest-connector
```
11. Verify if the function is deployed either from [OCI console](https://docs.oracle.com/en-us/iaas/Content/Functions/Tasks/functionsviewingfunctionsapps.htm#top) or by executing the following command
```shell
fn ls functions agcs-generic-rest-connector | awk {'print Function, $1 "\t\t" $3'}
```



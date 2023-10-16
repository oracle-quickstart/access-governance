import os
import oci
import auth_util_ip
import connected_system
import json
import base64


def create_instance(ag_cp_composite_client, signer):
    token = auth_util_ip.get_idcs_access_token(signer)
    details = oci.access_governance_cp.models.CreateGovernanceInstanceDetails(
        display_name=os.environ["SERVICE_INSTANCE_DISPLAY_NAME"],
        description=os.environ["SERVICE_INSTANCE_DESCRIPTION"],
        license_type=os.environ["AG_LICENSE_TYPE"],
        tenancy_namespace=get_name_space(),
        compartment_id=os.environ["SERVICE_INSTANCE_COMPARTMENT_OCID"],
        idcs_access_token=token)
    response = ag_cp_composite_client.create_governance_instance_and_wait_for_state(details,
                                                                                    wait_for_states=["ACTIVE",
                                                                                                     "NEEDS_ATTENTION"])
    json_res = json.dumps(str(response.__dict__['data']), indent=2)
    si_name = response.__dict__['data'].display_name
    connected_system.execute_add_connected_system(si_name)
    output = base64.b64encode(json_res.encode()).decode()
    print(output)


def get_name_space():
    signer_obj = auth_util_ip.get_si_signer()
    object_storage_client = oci.object_storage.ObjectStorageClient(config={}, signer=signer_obj)
    namespace_response = object_storage_client.get_namespace()
    return namespace_response.__dict__['data']


if __name__ == '__main__':
    auth_util_ip.init()
    service_endpoint = ("https://access-governance." + os.environ["ADMIN_REGION_SERVICE_INSTANCE"]
                        + ".oci.oraclecloud.com")
    signer_object = auth_util_ip.get_si_signer()
    access_governance_cp_client = oci.access_governance_cp.AccessGovernanceCPClient(config={}, signer=signer_object,
                                                                                    service_endpoint=service_endpoint)
    agCompositeClient = oci.access_governance_cp.AccessGovernanceCPClientCompositeOperations(
        access_governance_cp_client, config={}, signer=signer_object, service_endpoint=service_endpoint)
    create_instance(agCompositeClient, signer_object)

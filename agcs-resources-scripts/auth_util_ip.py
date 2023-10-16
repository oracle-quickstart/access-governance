import os
import oci
import requests
from requests.auth import HTTPBasicAuth


def init():
    init_connected_system_user_user_pvt_keys()


def replace_space_with_new_line(str_input):
    result = str_input
    if str_input.count(' ') > 4:
        start = "-----BEGIN PRIVATE KEY-----"
        end = "-----END PRIVATE KEY-----"
        extract_key = str_input[str_input.find(start) + len(start):str_input.rfind(end)]
        extract_key = extract_key.replace(' ', "\\n")
        result = start + extract_key + end
    return result


def init_connected_system_user_user_pvt_keys():
    parent_dir = os.path.realpath('.')
    connected_system_key_filename = os.path.join(parent_dir, 'resource', 'user_connected_system_pvt_key.pem')
    connected_system_pvt_key_file = open(connected_system_key_filename, "w+")
    connected_system_user_private_key = os.environ["AGCS_USER_PRIVATE_KEY_OCI_SYSTEM"]
    if connected_system_user_private_key and not connected_system_user_private_key.isspace():
        connected_system_user_private_key = replace_space_with_new_line(connected_system_user_private_key)
        connected_system_pvt_key_file.write(connected_system_user_private_key.encode('raw_unicode_escape')
                                            .decode('unicode_escape'))
    connected_system_pvt_key_file.close()


def get_si_signer():
    return oci.auth.signers.InstancePrincipalsDelegationTokenSigner(delegation_token=os.environ["OCI_obo_token"])


def get_auth_url():
    return os.environ["IDCS_ENDPOINT"] + "/oauth2/v1/token"


def get_idcs_access_token(signer):
    token_url = get_auth_url()
    headers = {'Content-type': 'application/x-www-form-urlencoded'}
    body = {
        "grant_type": "urn:ietf:params:oauth:grant-type:token-exchange",
        "scope": "urn:opc:idm:__myscopes__",
        "requested_token_type": "urn:ietf:params:oauth:token-type:access_token"
    }
    response = requests.post(token_url, auth=signer, headers=headers, data=body)
    response_json = response.json()
    return response_json['access_token']


def get_ag_authorization_token(user, password, ag_instance_url):
    token_url = get_auth_url()
    headers = {'Content-type': 'application/x-www-form-urlencoded'}
    body = {
        "grant_type": "client_credentials",
        "scope": ag_instance_url
    }
    response = requests.post(token_url, auth=HTTPBasicAuth(user, password), headers=headers, data=body)
    response_json = response.json()

    return response_json['access_token']


def get_connected_system_pvt_key():
    parent_dir = os.path.realpath('.')
    connected_sys_file = open(os.path.join(parent_dir, 'resource', 'user_connected_system_pvt_key.pem'), "r")
    content_cs = connected_sys_file.read()
    connected_sys_file.close()
    return content_cs

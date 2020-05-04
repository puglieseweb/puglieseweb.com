# HashiCorp Vault

[Vault](https://www.vaultproject.io/docs/commands/) is a key/value secret storage. A secret is anything that you want to tightly control access, such as API keys, passwords, certificates, and other sensitive information. Vault provides a unified interface to any secret while providing tight access control and recording a detailed audit log.

Vault allows to centralise the Identity management solution across different  platform \(replacing IAM, Azure AD, Cloud IAM, etc.\). This implies that each application need to directly access Vault.

Vault Act as a Root or Intermediate Certificate Authority and Provide Encryption as a Service \(you provide the clear text password and vault encrypt. 

What Problems does Vault solve: 

1. Centralize secret across the organisation
2. Increase security posture by elimination long-lived credential

### How to Authenticate

To authenticate to Vault we require a Role ID and a Secret ID. These are used to generate and Vault authentication token. In a subsequent request to Vault the authentication token is used to retrieve secrets from Vault from a secret path. Normally the authentication token last a day while the Secret ID requires to be rotated every year.

What Problems Does Vault Solve? 

* Centralize secrets across the organization
* eliminate log-lived credentials 
* Provide encryption as a Service to the organization 
* Automate the generation of certificates for authentication

##  Secret Engines

* Vault components which store, generate, or encrypt data
* Secret engines are enabled and isolated at a "path"

### Authentication Methods

Authentication Methods are responsible for assigning identity and policies to a user

Multiple authentication methods are available. 

Once authenticated Vault will issue a client token used to make subsequent Vault requests \(read/write\)

### Vault Paths

Everything is Vault is path--based 

The path prefix tells Vault which Vault component a request should be routed

Secretes engine and authentication methods are "mounted" as a specified path

Paths available are dependent on the features enabled in Vault, such as Authentication Methods and Secrets Engine. 

Permissions, or policies, are granted based upon the path 

Some secret engines and authentication methods have some predefined paths beneath the mount point. For example, the database secret has: 

* database/config/config name= connection information
* database/roles/role name= defines statement to execute to create the database credential
* database/creds/rolename = credential generation

## Starting Vault

```text
docker run -d -p 8200:8200 --name vault -e 'VAULT_DEV_ROOT_TOKEN_ID=myroot' -e 'VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200' vault
```

Enter in the Vault container and authenticate to Vault 

```text
docker exec -i -t vault sh
export VAULT_ADDR='http://localhost:8200'
vault loging myroot
```

White a secret in Vault:

```text
vault kv put secret/configclient client.pseudo.property="Property value loaded from Vault"
```


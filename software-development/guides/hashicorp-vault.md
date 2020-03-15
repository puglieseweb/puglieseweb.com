# HashiCorp Vault

Vault is a tool for securely accessing secrets. A secret is anything that you want to tightly control access, such as API keys, passwords, certificates, and other sensitive information. Vault provides a unified interface to any secret while providing tight access control and recording a detailed audit log.

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
vault write secret/configclient client.pseudo.property="Property value loaded from Vault"

```


# Secret Manager

securely stores, encrypts, and rotates database credential and other secrets.

* Encryption in transit and at reast using KMS
* Automatically rotates credentials
* Apply fine-grained access control usign IAM policies
* Cost moneys but is highly scalable

IMPORTANT: if you enable rotation, Secrets Manager immediately rotates the secret once to test the configuration

# Secret Manager

securely stores, encrypts, and rotates database credential and other secrets.

* Encryption in transit and at reast using KMS&#x20;
* Automatically rotates credentials
* Apply fine-grained access control usign IAM policies&#x20;
* Cost moneys but is highly scalable

IMPORTANT: if you enable rotation, Secrets Manager immediately rotates the secret once to test the configuration



AWS KMS vs AWS Secret Manager

AWS KMS:

1. Primary function: Manages cryptographic keys
2. Use cases:
   * Encryption and decryption of data
   * Digital signing and verification
3. Features:
   * Creates and controls encryption keys
   * Integrates with many AWS services for data encryption
   * Provides audit trails for key usage

AWS Secrets Manager:

1. Primary function: Stores, rotates, and manages secrets
2. Use cases:
   * Managing database credentials
   * API keys
   * OAuth tokens
3. Features:
   * Centralized secret storage
   * Automatic secret rotation
   * Fine-grained access control
   * Integration with AWS services and applications

Key differences:

1. Purpose: KMS focuses on key management, while Secrets Manager is designed for storing and managing secrets.
2. Functionality: KMS provides cryptographic operations, whereas Secrets Manager offers secure storage and retrieval of sensitive information.
3. Rotation: Secrets Manager has built-in secret rotation capabilities, while KMS focuses on key rotation.

While they serve different primary purposes, these services can be used together. For example, you might use KMS to encrypt the secrets stored in Secrets Manager for an additional layer of security


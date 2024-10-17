# Secret Manger VS System Manager's Parameter Store

## AWS Secrets Manager VS AWS Systems Manager's Parameter Store&#x20;

Both AWS Secrets Manager and AWS Systems Manager Parameter Store are services for storing and managing configuration data, but they have some key differences that make them suitable for different use cases. Here's a brief overview of when to use each:

AWS Secrets Manager:

1. **Sensitive information**. Use for storing highly sensitive data like database credentials, API keys, or encryption keys.
2. **Automatic rotation**. When you need built-in secret rotation capabilities, especially for supported AWS services.
3. **Fine-grained access control**. When you require detailed **IAM policies** for accessing secrets.
4. **Cross-region replication**. If you need to replicate secrets across multiple AWS regions.
5. **Larger secrets**. For storing secrets up to **65,536 bytes in size.**

AWS Systems Manager's Parameter Store:

1. **Non-sensitive configuration**. For storing non-sensitive application configuration data, environment variables, or settings.
2. **Hierarchical storage.** When you want to organize parameters in a hierarchical structure.
3. **Version tracking**. If you need to maintain a history of parameter changes.
4. **Integration with other AWS services**. When working closely with other AWS Systems Manager features or services that integrate well with Parameter Store.
5. **Cost-sensitive scenarios**. Parameter Store has a free tier and is generally less expensive for storing a large number of small parameters.
6. **Smaller parameter values.** For storing values up to **8,192 bytes in size** (4,096 bytes for standard parameters).

In summary, use Secrets Manager for sensitive data that requires advanced features like automatic rotation, and use Parameter Store for general configuration data and when cost is a significant factor.

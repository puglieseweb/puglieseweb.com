# Security

## AWS Identity Management Types (Most Secure to Least Secure)

1. **IAM Roles with AWS Security Token Service (STS)**
   * Temporary, automatically rotated credentials
   * Least privilege access
   * No long-term credentials to manage
2. **IAM Instance Profiles**
   * Automatically provides and rotates credentials for EC2 instances
   * No need to store credentials on the instance
3. **Web Identity Federation**
   * Uses external identity providers (e.g., Google, Facebook)
   * Temporary credentials, no AWS credentials to manage
4. **AWS Single Sign-On (SSO)**
   * Centralized access management for multiple AWS accounts
   * Integration with existing identity systems
5. **IAM Users with Multi-Factor Authentication (MFA)**
   * Additional layer of security beyond username and password
   * Significantly reduces the risk of unauthorized access
6. **IAM Users with Programmatic Access (Access Keys)**
   * Long-term credentials
   * Requires careful management and regular rotation
7. **IAM Users with Console Access (Username/Password)**
   * Long-term credentials
   * Susceptible to phishing and brute-force attacks if not properly secured
8. **Root Account**
   * Full, unrestricted access to all AWS services and resources
   * Highest risk if compromised, should be used sparingly and secured with MFA

Note: The exact order can be debated and may vary based on specific implementation details and security practices.

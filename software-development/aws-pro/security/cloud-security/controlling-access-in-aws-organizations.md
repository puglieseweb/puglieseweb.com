# Controlling Access in AWS Organizations

<figure><img src="../../../../.gitbook/assets/image (28) (1).png" alt=""><figcaption></figcaption></figure>

### Service Control Policies (SCPs)

Service Control Policies are a fundamental tool for managing permissions across an AWS organization. Key characteristics include:

* Can be applied at three levels:
  * Entire organization
  * Organizational units (OUs)
  * Individual accounts (though not recommended)
* Use IAM policy syntax
* Only deny permissions, never grant them
* Effects are inherited by all accounts below the target level

<figure><img src="../../../../.gitbook/assets/image (22) (1).png" alt=""><figcaption></figcaption></figure>

#### SCP Implementation Types

1. **Deny Lists**
   * **Explicitly denies specific actions**
   * All other actions remain available (subject to IAM permissions)
2. **Allow Lists**
   * Implicitly **denies all actions not explicitly listed**
   * More restrictive approach

<figure><img src="../../../../.gitbook/assets/image (19) (1).png" alt=""><figcaption></figcaption></figure>

#### Permission Evaluation

Effective permissions are determined by the combination of:

* Service Control Policy restrictions
* IAM policy permissions

An action is only permitted when it is both:

* Explicitly allowed by an IAM policy
* Not denied by any applicable SCP

<figure><img src="../../../../.gitbook/assets/image (23) (1).png" alt=""><figcaption></figcaption></figure>



### AWS Config

AWS Config serves as a monitoring and compliance tool with the following features:

* Monitors best practices across the organization
* Includes pre-configured guardrails from AWS
* Provides detective controls for compliance monitoring
* Maintains compliance history across accounts
* Allows creation of custom rules
* Can identify specific non-compliant resources

<figure><img src="../../../../.gitbook/assets/image (24) (1).png" alt=""><figcaption></figcaption></figure>

### IAM Identity Center (formerly AWS SSO)

IAM Identity Center is the recommended solution for managing user access across AWS accounts:

<figure><img src="../../../../.gitbook/assets/image (25) (1).png" alt=""><figcaption></figcaption></figure>

#### Key Features:

* Maps users and groups from identity providers to IAM roles
* Integrates with SAML 2.0 providers (e.g., Azure AD)
* Can function as a standalone user directory

#### Access Management Capabilities:

* Supports mapping users to multiple roles across different accounts
* Enables definition of permission sets for different access levels
* Allows users to assume different roles based on their group membership
* Facilitates granular access control between development and production environments

<figure><img src="../../../../.gitbook/assets/image (26) (1).png" alt=""><figcaption></figcaption></figure>

#### Implementation Example:

* Users can be organized into groups (e.g., admins, developers)
* Permission sets can be created for different access levels
* Access can be tailored per environment:
  * Development: Multiple permission sets possible
  * Production: Restricted access based on role requirements

You can map one user to many permission sets to many accouts:

<figure><img src="../../../../.gitbook/assets/image (27) (1).png" alt=""><figcaption></figcaption></figure>

### Best Practices

1. **Service Control Policies:**
   * Prefer OU-level application over individual account restrictions
   * Consider creating separate OUs when security needs differ significantly
2. **Access Management:**
   * Use IAM Identity Center for multi-account user access
   * Implement clear permission set hierarchies
   * Maintain separate access controls for development and production environments
3. **Compliance:**
   * Regularly monitor AWS Config rules
   * Address non-compliance issues promptly
   * Maintain documentation of compliance history

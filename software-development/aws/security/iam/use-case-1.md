# Use case 1

A company is using an on-premises Active Directory service for user authentication. The company wants to use the same authentication service to sign in to the company’s AWS accounts, which are using AWS Organizations. AWS Site-to-Site VPN connectivity already exists between the on-premises environment and all the company’s AWS accounts. The company’s security policy requires conditional access to the accounts based on user groups and roles. User identities must be managed in a single location. Which solution will meet these requirements?

* A. Configure AWS IAM Identity Center (AWS Single Sign-On) to connect to Active Directory by using SAML 2.0. Enable automatic provisioning by using the System for Cross-domain Identity Management (SCIM) v2.0 protocol. Grant access to the AWS accounts by using attribute-based access controls (ABACs).
* B. Configure AWS IAM Identity Center (AWS Single Sign-On) by using IAM Identity Center as an identity source. Enable automatic provisioning by using the System for Cross-domain Identity Management (SCIM) v2.0 protocol. Grant access to the AWS accounts by using IAM Identity Center permission sets.
* C. In one of the company’s AWS accounts, configure AWS Identity and Access Management (IAM) to use a SAML 2.0 identity provider. Provision IAM users that are mapped to the federated users. Grant access that corresponds to appropriate groups in Active Directory. Grant access to the required AWS accounts by using cross-account IAM users.
* D. In one of the company’s AWS accounts, configure AWS Identity and Access Management (IAM) to use an OpenID Connect (OIDC) identity provider. Provision IAM roles that grant access to the AWS account for the federated users that correspond to appropriate groups in Active Directory. Grant access to the required AWS accounts by using cross-account IAM roles.

Let's analyze this step by step:

1. Key Requirements:
   * Use existing on-premises Active Directory for authentication
   * Authenticate to multiple AWS accounts managed by AWS Organizations
   * Need conditional access based on user groups and roles
   * Single location for identity management
   * Already has Site-to-Site VPN connectivity
2.  Evaluate each option: A. Using AWS IAM Identity Center with SAML 2.0 and Active Directory:

    * ✓ Supports Active Directory integration via SAML
    * ✓ Centralized identity management
    * ✓ SCIM for automatic user provisioning
    * ✓ ABAC supports conditional access based on groups/roles
    * ✓ Works with AWS Organizations This option meets all requirements.

    B. Using IAM Identity Center as identity source:

    * ✗ Doesn't use existing Active Directory
    * ✗ Creates separate identity store This doesn't meet the requirement for using existing AD.

    C. Using IAM with SAML and cross-account IAM users:

    * ✗ Cross-account IAM users are not a best practice
    * ✗ More complex to manage
    * ✗ Doesn't scale well across multiple accounts This introduces unnecessary complexity and security risks.

    D. Using IAM with OIDC:

    * ✗ Active Directory typically uses SAML, not OIDC
    * ✓ Cross-account roles are better than users
    * ✗ More complex to manage than IAM Identity Center This doesn't align well with Active Directory.
3. Best Solution: Option A is the correct answer because:

* It leverages existing Active Directory via SAML 2.0
* Provides centralized identity management
* SCIM enables automatic user provisioning
* ABAC allows fine-grained conditional access
* Integrates well with AWS Organizations
* Follows AWS best practices for multi-account access

The other options either don't use the existing Active Directory, introduce unnecessary complexity, or don't follow AWS best practices for multi-account access management.


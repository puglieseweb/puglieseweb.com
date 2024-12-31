# ASW Identity Sources

In AWS, there are several identity sources available for authentication and authorization:

1. AWS Native Identity Sources:
   * AWS Identity and Access Management (IAM) Users
   * AWS IAM Roles
   * AWS IAM User Groups
   * AWS Organizations for multi-account management
2. AWS Identity Center (formerly AWS SSO) Supported Identity Sources:
   * Built-in AWS Identity Center directory
   * AWS Managed Microsoft AD
   * AD Connector
   * External Identity Providers via SAML 2.0
   * Self-managed Active Directory
   * Microsoft Azure AD (now Entra ID)
   * Okta Universal Directory
3. AWS Cognito Identity Sources:
   * Cognito User Pools (built-in user directory)
   * Social Identity Providers:
     * Facebook
     * Google
     * Apple
     * Amazon
   * SAML Identity Providers
   * OpenID Connect Providers
   * LDAP directories
4. AWS Directory Service Options:
   * AWS Managed Microsoft AD
   * AD Connector (proxy to on-premises AD)
   * Simple AD (standalone directory based on Samba 4)
5. External Identity Provider Integration:
   * Any SAML 2.0 compliant provider
   * OpenID Connect providers
   * Custom identity broker applications
   * Corporate directories via federation

Each of these sources can be used depending on your specific needs, such as enterprise integration, application user management, or cloud-native implementations.

\

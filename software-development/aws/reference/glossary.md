# Glossary

## SAML (single sign-on)

SAML stands for Security Assertion Markup Language. It's an open standard for exchanging authentication and authorization data between parties, particularly between an identity provider (IdP) and a service provider (SP). Here's a brief overview:

1. Purpose: SAML allows for single sign-on (SSO) across different systems and organizations.
2. XML-based: It uses XML format for encoding security assertions.
3. Key components:
   * Identity Provider (IdP): Authenticates users and issues SAML assertions
   * Service Provider (SP): Consumes SAML assertions to grant access to resources
4. Flow:
   * User attempts to access a service
   * Service redirects to IdP for authentication
   * IdP authenticates user and sends SAML assertion back to service
   * Service grants access based on the assertion
5. Benefits:
   * Centralized authentication
   * Reduced password fatigue for users
   * Increased security through standardized tokens
6. Common use: Enterprises use SAML to allow employees to access multiple web applications using their corporate credentials.

SAML is widely used in enterprise environments for secure, streamlined access management across various services and applications.

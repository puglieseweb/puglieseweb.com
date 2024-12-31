# OAuth VS OpenID Connect

* Core Purpose
  * OAuth 2.0: It's primarily an authorization framework. It determines what resources a client can access on behalf of a user
  * OpenID Connect: It's an identity layer built on top of OAuth 2.0 that adds authentication. It verifies who the user is
* Token Types
  * OAuth 2.0: Issues Access Tokens and Refresh Tokens
    * Access Token: Grants access to specific resources
    * Refresh Token: Used to obtain new access tokens when they expire
  * OpenID Connect: Adds ID Tokens (JWT format) on top of OAuth tokens
    * ID Token: Contains user identity information (claims)
    * Still uses Access and Refresh tokens from OAuth
* Scope Differences
  * OAuth 2.0: Scopes define resource access permissions (e.g., read\_calendar, write\_posts)
  * OpenID Connect: Introduces specific identity scopes like:
    * profile: basic user info
    * email: email address
    * address: postal address
    * phone: phone number
* Endpoints
  * OAuth 2.0: Has authorization and token endpoints
  * OpenID Connect: Adds additional endpoints:
    * UserInfo Endpoint: Returns claims about the user
    * End-Session Endpoint: Handles logout
    * Discovery Endpoint: Provides OIDC configuration information
* User Information
  * OAuth 2.0: No standardized way to get user information
  * OpenID Connect: Standardized claims about the user in the ID Token or via UserInfo endpoint
* Use Cases
  * OAuth 2.0:
    * API authorization
    * Delegated access
    * Third-party application integration
  * OpenID Connect:
    * Single Sign-On (SSO)
    * Mobile app authentication
    * Enterprise identity management
* Implementation Complexity
  * OAuth 2.0: More flexible but requires more implementation decisions
  * OpenID Connect: More structured with clear specifications for handling identity

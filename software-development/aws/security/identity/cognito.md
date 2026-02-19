# Amazon Cognito – Complete Guide (February 2026)

**Amazon Cognito** is a fully managed AWS service that handles **authentication**, **authorization**, and **user management** for web, mobile, and serverless applications — without requiring you to build or maintain custom user directories, password hashing, token logic, or session management.

Users can sign in directly with credentials they create in your app (username/password), or federate through third-party identity providers (IdPs) such as:

- Google
- Facebook
- Login with Amazon
- Sign in with Apple
- SAML 2.0 providers (e.g., Microsoft Entra ID / Active Directory)
- OpenID Connect (OIDC) providers

Cognito supports secure sign-up, sign-in, MFA (SMS/email/TOTP), password policies, custom attributes, user groups, token issuance, and session revocation — all with built-in security and compliance features.

## Core Components

Cognito has **two main services** that solve complementary problems:

| Component          | Purpose                                      | Key Outputs                              | Typical Use Cases                                      | Can Be Used Alone? |
|--------------------|----------------------------------------------|------------------------------------------|--------------------------------------------------------|--------------------|
| **User Pools**     | **Authentication** + user directory          | ID Token, Access Token, Refresh Token (JWTs) | User sign-up/sign-in, federated login, API protection | Yes                |
| **Identity Pools** | **Authorization** — temporary AWS credentials | Temporary IAM credentials (access key, secret key, session token) | Direct client access to S3, DynamoDB, Lambda, etc.     | Yes (but usually paired) |

- **User Pools** act as your user directory and identity provider (IdP), issuing JSON Web Tokens (JWTs) after successful authentication.
- **Identity Pools** (formerly Federated Identities) map authenticated (or guest) users to temporary AWS IAM credentials for fine-grained access to AWS resources.
- **Best practice (2026)**: Use **User Pools** for login + API auth. Add **Identity Pools** only when clients need direct AWS service access (e.g., browser uploads to S3).

User Pools and Identity Pools can be used **separately** or **together** — the most common modern pattern is **User Pools → Identity Pools** for authenticated flows.

## Cognito Tokens – The Three Types

After successful authentication in a **User Pool**, Cognito issues three JWTs (or opaque tokens):

1. **ID Token**  
   - **Purpose**: Proves **who** the user is (authentication / OpenID Connect identity).  
   - **Contains**: User claims (sub/user ID, email, name, phone, custom attributes, groups).  
   - **Best used for**: Frontend UI (display name/email), identity-based decisions in your app.  
   - **Not** primarily for API authorization (contains sensitive PII — avoid sending unnecessarily).  
   - Lifetime: Default 1 hour (configurable).

2. **Access Token**  
   - **Purpose**: Authorizes actions/scopes the user can perform (OAuth 2.0 authorization).  
   - **Contains**: Scopes (e.g., `openid`, `profile`, custom scopes), groups, client_id.  
   - **Best used for**: Calling your APIs (via API Gateway JWT authorizer), user self-service operations (e.g., UpdateUserAttributes — requires `aws.cognito.signin.user.admin` scope).  
   - **2026 best practice**: Preferred token for backend API authorization to follow least-privilege and separation of concerns.

3. **Refresh Token**  
   - **Purpose**: Obtain new ID + Access tokens without re-login.  
   - **Opaque/encrypted** — readable only by Cognito.  
   - Lifetime: Default 30 days (configurable up to 10 years).  
   - **Security**: Store securely (HttpOnly cookie or secure storage), never expose client-side. Revoke on logout.

**Token best practices (2026)**:
- Use **Access Token** for API calls when possible (scopes + least privilege).
- Use **ID Token** safely with Cognito's native API Gateway authorizer (AWS verifies it securely).
- Never store tokens in localStorage (XSS risk) — use secure cookies or Amplify Auth helpers.
- Enable **token revocation** to invalidate tokens on logout, compromise, or account disable.

## Typical Authentication Flow

```mermaid
sequenceDiagram
    autonumber
    actor User
    participant Frontend as Client App (Browser/Mobile)
    participant Cognito as AWS Cognito User Pool
    participant Backend as Your API / Lambda
    participant Resource as Protected Resource / AWS Service

    User->>Frontend: Open app & initiate sign-in
    Frontend->>Cognito: Redirect to Hosted UI / SDK sign-in (username/password or federated)
    User->>Cognito: Authenticate
    Cognito-->>Frontend: ID Token + Access Token + Refresh Token

    Frontend->>Frontend: Store tokens securely (memory + HttpOnly cookie for refresh)

    User->>Frontend: Request protected data
    Frontend->>Backend: API request + Authorization: Bearer <Access Token> (or ID Token)
    Backend->>Cognito: Validate JWT (native authorizer or custom verification)
    Backend-->>Frontend: Return protected data

    Note over Frontend,Cognito: Periodic refresh
    Frontend->>Cognito: Refresh request (with Refresh Token)
    Cognito-->>Frontend: New ID + Access Tokens

    alt Optional: Direct AWS access (Identity Pool)
        Frontend->>Cognito: Exchange tokens via Identity Pool SDK
        Cognito-->>Frontend: Temporary AWS IAM Credentials
        Frontend->>Resource: Call S3 / DynamoDB / etc. directly
    end
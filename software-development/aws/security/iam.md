---
description: Identity and Access Management
---

# IAM

**Use AWS SSO for internal user management and Cognito for external.**&#x20;



AWS IAM (Identity and Access Management) provides several types of identities to manage access to AWS resources:

1. IAM Users:
   * Individual identities within your AWS account
   * Have long-term credentials (username/password or access keys)
   * Ideal for specific individuals needing AWS access
2. IAM Groups:
   * Collections of IAM users
   * Used to manage permissions for multiple users at once
   * Users can belong to multiple groups
3. IAM Roles:
   * Temporary identities with specific permissions
   * Can be **assumed by users, applications, or AWS services**
   * Don't have long-term credentials associated with them
   * **There is no need to rotate credentials**&#x20;
4. Federated Users:
   * **Users managed outside of AWS** (e.g., corporate directory)
   * Gain temporary access to AWS through federation
   * Often use SAML 2.0 or custom identity broker
5. Root User:
   * Has complete access to all AWS services and resources
   * Created when you first set up your AWS account
   * Should be secured and used sparingly
6. Service-linked Roles:
   * Predefined roles linked to specific AWS services
   * Have permissions required for the service to interact with other AWS resources.

### User Groups

Create IAM Groups and assign your users to groups. Group permissions are assigned using IAM policy documents. Your users will automatically inherit the permissions of the group.

### Roles

Roles are the preferred option from a security perspective.&#x20;

Roles avoid hard coding your credentials (access  key IDs and secret access key)&#x20;

policies control a role's persission.&#x20;

you can update a policy attached to a role, and it will take immediate effect.&#x20;

attaching and detaching roles to running EC2 instances without having to stop or terminate these instances.

### Corporate Identity Provider&#x20;

To set up identities from your corporate environment in AWS, you'll typically use AWS Identity and Access Management (IAM) with federation. Here's a concise overview of the process:

1. Set up an identity provider (IdP) in your corporate environment, such as Active Directory Federation Services (ADFS) or Okta.
2. Configure AWS IAM to trust your corporate IdP:
   * In the IAM console, create a SAML identity provider
   * Upload the IdP's metadata document
3. Create IAM roles that map to your corporate groups or users
4. Configure your IdP to send SAML assertions to AWS
5. Set up AWS Single Sign-On (SSO) for a more streamlined experience
6. Test the federation to ensure users can access AWS resources

This setup allows your corporate users to access AWS resources using their existing corporate credentials.
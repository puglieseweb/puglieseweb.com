# Cross-Account Role Access

Duplicating IAM accounts creates a security vulnerability. **Cross-account role access** gives you the ability to set up temporary access you can easily control.

Any Temporary employees should use role access only! Avoid permanent credentials (AIM or access keys)

**Role Assumption is temporary and is it the most secure way of set up temporary access to accounts.**

For example, this can be used by an external auditor that needs to cross-exam the organisation accounts. The most efficient and secure way of allowing a third party access to our AWS account is to use Cross-account role access to:

1. grant read-only access
2. allow the auditor to use their own AWS accout

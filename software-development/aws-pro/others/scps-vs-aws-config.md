# SCPs vs AWS Config

Let me break down the key differences between Service Control Policies (SCPs) and AWS Config:

Service Control Policies (SCPs):

* Part of AWS Organizations
* Preventive control - blocks actions before they happen
* Sets maximum permissions (guardrails) that apply to all accounts in an organization or organizational unit
* Cannot grant permissions, only restrict them
* Takes precedence over IAM permissions - even if IAM allows an action, SCP can block it
* Common use case: Preventing accounts from leaving the organization, blocking specific regions, or restricting certain service usage

AWS Config:

* Detective control - monitors and evaluates resources after deployment
* Assesses resources against rules and compliance standards
* Provides audit history of resource changes
* Can trigger automated remediation actions through SSM Automation
* Records configuration changes and relationships between resources
* Common use case: Ensuring resources meet compliance requirements, tracking resource modifications, and enforcing tagging standards

Key Distinctions:

1. Timing: SCPs prevent actions before they occur, while Config detects non-compliance after the fact
2. Scope: SCPs work at organization level affecting permissions, while Config works at resource level checking configurations
3. Purpose: SCPs are for access control and governance, Config is for compliance assessment and resource tracking
4. Flexibility: Config can be customized with custom rules and remediation actions, SCPs are more rigid permission controls

Both tools are often used together as part of a comprehensive governance strategy - SCPs to prevent unauthorized actions and Config to ensure deployed resources remain compliant.

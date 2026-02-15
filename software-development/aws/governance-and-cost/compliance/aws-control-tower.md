# AWS Control Tower

{% hint style="info" %}
You need to have AWS Organizations enabled to use AWS Control Tower. Here's why:

AWS Control Tower is built on top of AWS Organizations and requires it as a foundational service because:

1. AWS Control Tower uses Organizations to create and manage multiple AWS accounts in a hierarchical structure
2. It leverages Organizations' Service Control Policies (SCPs) to implement preventive guardrails across accounts
3. The AWS Organizations master account becomes the Control Tower management account where you set up and manage Control Tower

In fact, when you set up AWS Control Tower, it will:

* Either use your existing AWS Organizations setup
* Or automatically create a new AWS Organizations structure if you haven't enabled it yet

The setup process also creates two additional mandatory accounts:

* A Log Archive account (for centralized logging)
* An Audit account (for centralized security and compliance monitoring)
{% endhint %}

AWS Control Tower helps with data governance and prevents deviation from best practices. This is done using guardrails that provide preventive controls. Guardrails can make sure that security logs and permissions for cross-account access are maintained unaltered.

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/u7odHFCWUq-b?a=7363&#x26;x=211&#x26;y=137&#x26;w=1521&#x26;h=932&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%20f60cb2948c738efafae4c72f842c8878f1079c9258ab4c1a157f9b12d7ddfb43-ts%3D1727628378" alt=""><figcaption></figcaption></figure>

Guardrails can be categorized based on their behavior or guidance. The behavior of a guardrail can be either preventive or detective:

* **Preventive guardrails** disallow any actions violating compliance rules and can be in either enforced or not enabled states. **They are implemented using service control policies (SCPs).**
* **Detective guardrails** provide alerts of violations of policy and non-compliance occurrences through a dashboard. They can be in either clear, in violation, or not enforced states and are **implemented through AWS Config rules**.

AWS Control Tower provides mandatory, strongly recommended, and elective guardrails as guidance categories:

* **Mandatory guardrails** are enforced at all times.&#x20;
* **Strongly recommended guardrails** are created for ensuring best practices.&#x20;
* **Elective guardrails** allow you to lock down any action that is not permitted in an AWS environment for an enterprise.



AWS Control Tower is the quickest way to create and manage a secure, compliant, multi-account environment based on best practices

* Governance: Easy way to set up and govern an AWS multi-account environment.&#x20;
* Orchestration: Automate account creation and security controls via other AWS services
* Extension: **Extends AWS Organizations to prevent governance drift, and leverages different guardrails**&#x20;
* New AWS Accounts: users can provision new AWS accounts quickly, using central admin established compliance policies

Features:

* Landing zone: Well-architected, multi-account environment based on compliance and security best practices.
* Guardrails: High-level rules providing continuous governance for the AWS environment.

Two different rules types:

| Preventive                                                            | Detective                                                          |
| --------------------------------------------------------------------- | ------------------------------------------------------------------ |
| Ensures accounts maintain governance by disallowing violating actions | Detects and alerts on non compliant resources  within all accounts |
| Leverages **service control policie**s                                | Leverages AWS Config rules                                         |
| Statuses of enforced or not enabled                                   | Status of clear, in violation, or not enabled                      |
| Supported in all Regions                                              | Only apply to certain Regions                                      |

* Account Factory: Configurable account template for standardising pre-approved configs on new accounts.&#x20;
* CloudFormation StackSet: Automated deployments of templates deploying repeated resources for governance.
* Shared Accounts: Three accounts used by Control Tower created during landing zone creation. &#x20;

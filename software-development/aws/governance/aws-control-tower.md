# AWS Control Tower

`Leverage preventive or detective guardrails:`

* `preventive guardrails using SCPs`&#x20;
* `detective guardrails using AWS Config`



AWS Control Tower is the quickest way to create and manage a secure, compliant, multi-account environment based on best practices

* Governance: Easy way to set up and govern an AWS multi-account environment.&#x20;
* Orchestration: Automate account creation and security controls via other AWS services
* Extension: Extends AWS Organizations to prevent governance drift, and leverages different guardrails&#x20;
* New AWS Accounts: users can provision new AWS accounts quickly, using central admin established compliance policies

Features:

* Landing zone: Well-architected, multi-account environment based on compliance and security best practices.
* Guardrails: High-level rules providing continuous governance for the AWS environment.

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/u7odHFCWUq-b?a=7363&#x26;x=211&#x26;y=137&#x26;w=1521&#x26;h=932&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%20f60cb2948c738efafae4c72f842c8878f1079c9258ab4c1a157f9b12d7ddfb43-ts%3D1727628378" alt=""><figcaption></figcaption></figure>

Two different rules types:

| Preventive                                                            | Detective                                                          |
| --------------------------------------------------------------------- | ------------------------------------------------------------------ |
| Ensures accounts maintain governance by disallowing violating actions | Detects and alerts on non compliant resources  within all accounts |
| Leverages **service control policie**s                                | Leverages AWS Config rules                                         |
| Statuses of enforced or not enabled                                   | Status of clear, in violation, or not enabled                      |
| Supported in all Regions                                              | Only apply to certain Regions                                      |
|                                                                       |                                                                    |
|                                                                       |                                                                    |

* Account Factory: Configurable account template for standardising pre-approved configs on new accounts.&#x20;
* CloudFormation StackSet: Automated deployments of templates deploying repeated resources for governance.
* Shared Accounts: Three accounts used by Control Tower created during landing zone creation. &#x20;

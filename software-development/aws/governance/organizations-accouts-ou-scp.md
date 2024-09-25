# Organizations

In an organization there are two types of Accouts

* One Management Account: also called the Payer accout, this is the primary account that hosts and manages the organization
* Member Accounts: All other AWS accouts that belong to the organisation. **You normally separates accounts by environments and team ownership.**&#x20;

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/jK-bc5LWb30b?a=6739&#x26;x=270&#x26;y=145&#x26;w=1481&#x26;h=711&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%20e97776b0e8a4df917be426d749f3d9c7d3374f04d2f40036aa300df9b60d25f2-ts%3D1727260949" alt=""><figcaption></figcaption></figure>

**Features:**

* Consalidated Billing: Rolls all bills up to the payer account. Single payment method. You can use the Cost explore to view you billing in a granular fashion.
* Usage Discounts: Consolidated Billing allows for aggregate usage discounts.
* Shared Saving: Easily share Reserved instances and Saving Plans across the org.

Concepts:

* Multi-accounts Strategy: Allows to easily achive a multi-account design while maintaining centralised management.
* Tag enforcement: enforces specific tags for all AWS resources for categorization and tracking.
* Organization Unit (OU): Logical grouping of multiple accounts to allow for easy management and separation.&#x20;
* Service Control Policies (SCPs): Json policies that get applied to OUs or accounts to restrict actions that are or are not allowed.
* Management Account: SCPs do not affect the management account like they do all member accounts.
* Account Best Practices: Create a centralized logging account for organizational CloudTrail logs. Also leverage cross-account roles for accessing member accounts.&#x20;


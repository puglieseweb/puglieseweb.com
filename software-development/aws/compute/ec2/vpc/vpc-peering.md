# VPC peering

Somtimes you may need to have several VPCs for different environments, and it may be necessary to connect these to each other.

For Example:

* Production Web VPC&#x20;
* Content VPC&#x20;
* Intranet VPC

Key characteristics:

* Allows to connect 1 VPC with another via a direct network route using private IP addresses.&#x20;
* Instances behave as if they were on the same private network&#x20;
* You can peer VPCs with other AWS accounts.
* **Peering is a start configuration** (e.g., 1 central VPC peer with 4 others). No transitive peering!
* **You can peer between regions.**
* **We cannot have overlapping CIDR address ranges.**



{% @lucid/lucid-component url="https://lucid.app/lucidchart/53875b19-93a1-4800-81d1-8c84d6351a09/edit?invitationId=inv_20ab51d1-0eaa-47f7-8d87-4aaee89152c1" %}

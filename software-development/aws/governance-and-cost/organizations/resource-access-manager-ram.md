# Resource Access Manager (RAM)

A free service that allows you to share AWS resources with other accounts inside or outside the organization. AWS RAM allows to easily share resources rather than having to create duplicate copies in your different accounts.

Sharable resources are:

* Transit Gateway&#x20;
* VPC Subnets
* License Manager&#x20;
* Route 53 Resolver (Rules and Endpoints)&#x20;
* Dedicated Hosts
* Many more

Owners and Participants:

* Ownership: Owner create and manage the VPC resources that get shared. Cannot delete or modify resources deployed by participant accounts.&#x20;
* Participant Accounts: Able to provision services into the shared VPC subnet. Cannot modify or delete the shared resources!

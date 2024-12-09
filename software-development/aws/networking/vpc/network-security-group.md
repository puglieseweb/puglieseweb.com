# Network Security Group

Security groups are associated with the following:

1. EC2 instances
2. Elastic Network Interfaces (ENIs)



**Security groups can't do deny rules. Instead use NACLs to block IP ranges.**



A network security group is not directly associated with a subnet, VPN, Availability Zone (AZ), or Region. Instead,&#x20;



To clarify:

* Security groups operate at the instance level, not at the subnet level.
* They can be applied to instances in any subnet, AZ, or VPC within a region.
* Multiple instances across different subnets or AZs can use the same security group.
* Security groups are specific to a VPC and region. You can't use a security group from one region or VPC in another.

It's important to note that while security groups are not associated with subnets, there is a similar concept called Network Access Control Lists (NACLs) that do operate at the subnet level.

Key differences:

1. Security Groups: Instance-level firewall, stateful
2. NACLs: Subnet-level firewall, stateless

# VIF VS ENI

ENI is like a network card for an EC2 instances, while VIF is more like a virtual cable connecting on-premises network to AWS.

The key differences between ENI (Elastic Network Interface) and VIF (Virtual Interface) are:

ENI (Elastic Network Interface):

* It's a virtual network card for EC2 instances within AWS
* Lives inside a VPC
* Operates at the instance level
* Primary uses:
  * Attaching multiple network interfaces to an EC2 instance
  * Creating management networks
  * Using network/security appliances
  * Moving network interfaces between instances
  * Having multiple IP addresses per instance
* Each ENI belongs to a specific subnet in a VPC
* Can have security groups attached
* Attributes include: private IP addresses, MAC address, source/destination check flag

VIF (Virtual Interface):

* It's a configuration object for AWS Direct Connect
* Lives at the Direct Connect level
* Operates at the connection level between AWS and on-premises
* Primary uses:
  * Creating private connections to VPCs (Private VIF)
  * Accessing AWS public services (Public VIF)
  * Connecting to Transit Gateway (Transit VIF)
* Requires BGP for routing
* Has attributes like VLAN tags and BGP ASN
* Can connect to Direct Connect Gateway or VPC

Simplified comparison:

```
ENI:
On-premises → Direct Connect → VPC → ENI → EC2 Instance
(Network interface inside AWS)

VIF:
On-premises → VIF → Direct Connect → AWS
(Connection interface between on-premises and AWS)
```

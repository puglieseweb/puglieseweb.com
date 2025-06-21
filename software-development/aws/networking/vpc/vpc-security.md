# VPC security

To open an application up to other VPCs, we can either:

* Open the VPC up to the internet:
  * Security considerations; everything in the public subnet is public
  * A lot more to manage
* Use VPC Peering:
  * You will need to create and manage may different peering relationships.
  * The whole network will be accessible. This isn's good if you have multiple applications within your VPC

### Does VPC Peering use a Virtual private gateway?

No, VPC Peering does not use a Virtual Private Gateway (VGW).

**VPC Peering** creates a direct network connection between two VPCs using AWS's internal network infrastructure. The connection is established through:

* A peering connection resource that you create between the VPCs
* Route table entries that direct traffic through the peering connection
* AWS's underlying network fabric

**Virtual Private Gateway** is a different AWS component used specifically for:

* VPN connections between your VPC and on-premises networks
* AWS Direct Connect connections
* Transit Gateway attachments

**Key differences**:

* **VPC Peering**: VPC-to-VPC communication within AWS
* **Virtual Private Gateway**: VPC-to-external network communication (on-premises, other cloud providers)

**What VPC Peering actually uses**:

* AWS's internal network backbone
* Peering connection endpoints within each VPC
* Modified route tables to direct traffic
* Security groups and NACLs for access control

The peering connection appears as a logical network interface in your route tables, but it's implemented through AWS's software-defined networking rather than through a virtual gateway appliance.

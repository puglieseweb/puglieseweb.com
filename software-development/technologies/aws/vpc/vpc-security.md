# VPC security

To open an application up to other VPCs, we can either:

* Open the VPC up to the internet:
  * Security considerations; everything in the public subnet is public
  * A lot more to manage
* Use VPC Peering:
  * You will need to create and manage may different peering relationships.
  * The whole network will be accessible. This isn's good if you have multiple applications within your VPC
*

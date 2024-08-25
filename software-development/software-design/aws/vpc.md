---
description: Virtual Private Cloud
---

# VPC

Fully Customizable Network. You can leverage multiple layers of security, including security groups and network access control lists, to help control access to Amazon EC2 instances in each subnet.

Think of a VPC as a Virtual Data Center in the cloud.

Every region as a default VPC that is setup automatically.

Logically isolate part of AWS cloud where you can define your own network.

A VPC gives complete control of Virtual Network, including IP address ranges, subnets, route tables, and network gateways.



In a three-tier architecture we have:

1. Web Server tier. Public-facing subnet (port 80 or port 443)
2. Application tier. Private subnet. Can only speak to web tier or database tier.&#x20;
3. Database tier. Priavte subnet. Can only speak to application tier.

Additional, you can create a hardware Virtual Private Network (VPN) connecting a corporate data center to the VPC and leverage the AWS Cloud as an extension of your corporate data center.



{% embed url="https://lucid.app/lucidchart/53875b19-93a1-4800-81d1-8c84d6351a09/edit?invitationId=inv_20ab51d1-0eaa-47f7-8d87-4aaee89152c1&page=4UR63qqFV6Co" %}

You can calculate the IP address's ranges using [https://cidr.xyz/](https://cidr.xyz/)

These IP address ranges are used for private networks. Here's when to use each:

1. **10.0.0.0/8** (10.0.0.0 to 10.255.255.255):
   * For large enterprise networks
   * Provides the most available IP addresses (over 16 million)
   * Best for organizations needing many subnets or devices
2. **172.16.0.0/12** (172.16.0.0 to 172.31.255.255):
   * For medium-sized networks
   * Offers about 1 million IP addresses
   * Useful for mid-sized companies or when 192.168.0.0 is too small
3. **192.168.0.0/16** (192.168.0.0 to 192.168.255.255):
   * For small networks
   * Provides 65,536 IP addresses
   * Commonly used in home networks and small offices

Choose based on your network size and potential growth. For most home and small business users, 192.168.0.0 is sufficient. Larger organizations may opt for 10.0.0.0 to accommodate more devices and subnets.



### What can we do with a VPC?

* Launch instances. Launch instances into a subnet of your choosing.
* Custom IP addresses. Assign custom IP addresses ranges in each subnet.
* Route Tables. Configure route tables between subnets.
* Internet Gateway. Create an internet gateway and attach to the VPC.
* More Control. Much better security control over AWS resources.
* Access control Lists. Subnet network access control lists.

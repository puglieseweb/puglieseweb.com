# VPC-to-VPC

### Connection Options Overview

#### VPC Peering

* Direct connection between two VPCs over AWS network backbone
* Can peer across accounts and regions
* Non-transitive (if A connects to B and C, B and C aren't automatically connected)
* Best for simple, static VPC connections
* Ideal for smaller environments with few VPC connections

#### Transit Gateway

* Hub-and-spoke architecture
* Supports up to 5,000 attachments
* Single transit gateway per region
* Can peer transit gateways across regions
* Highly scalable and flexible solution
* Supports hybrid connectivity (Direct Connect/VPN)

<figure><img src="../../../../.gitbook/assets/image (85).png" alt=""><figcaption></figcaption></figure>

### Transit Gateway Architecture

#### Attachment Requirements

* Requires transit gateway attachment in each Availability Zone
* One subnet minimum per AZ needed for communication
* Other subnets in same AZ can route through the attachment
* Supports cross-account sharing

#### Implementation Scenarios

1. Cross-Region Connectivity
   * Separate transit gateway per region
   * Transit gateways can be peered
   * Enables full cross-region VPC communication

<figure><img src="../../../../.gitbook/assets/image (89).png" alt=""><figcaption></figcaption></figure>

1. Cross-Account Connectivity
   * Single transit gateway can be shared across accounts
   * Each account's VPCs connect via transit gateway attachments
   * All VPCs must be in same region for single gateway

<figure><img src="../../../../.gitbook/assets/image (90).png" alt=""><figcaption></figcaption></figure>

1. Hybrid Connectivity
   * Supports Direct Connect and VPN connections
   * Can connect on-premises datacenters
   * Enables unified connectivity across cloud and on-premises

<figure><img src="../../../../.gitbook/assets/image (91).png" alt=""><figcaption></figcaption></figure>

### CIDR Considerations

<figure><img src="../../../../.gitbook/assets/image (86).png" alt=""><figcaption></figcaption></figure>

#### CIDR Block Components

* IP address (4 octets, 0-255 per octet)
* Netmask (determines IP range size)
* AWS supports netmasks from /16 to /28

#### Key CIDR Rules

* VPCs cannot have overlapping CIDR ranges
* AWS reserves 5 IP addresses per subnet:
  * First 4 addresses
  * Last address
  * Reserved for AWS networking and future use

#### Netmask Reference

* /28 = 16 IP addresses
* Each decrease in netmask doubles available IPs
* /16 provides largest address range
* Must plan CIDR ranges carefully for VPC connectivity

<figure><img src="../../../../.gitbook/assets/image (87).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (88).png" alt=""><figcaption></figcaption></figure>

### Best Practices

1. Choose Connection Type Wisely
   * Use VPC peering for simple, static connections
   * Use transit gateway for scalable, complex networks
2. Plan CIDR Ranges
   * Avoid overlapping ranges
   * Account for future growth
   * Consider cross-region and cross-account needs
3. Consider Availability Zone Coverage
   * Ensure transit gateway attachments in each required AZ
   * Plan subnet distribution across AZs

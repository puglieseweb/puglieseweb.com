# Network to VPC Connectivity

### Managed VPN

A simple AWS-managed IPsec VPN connection over existing internet connections.

| Category | Description                                                                                                                         |
| -------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| What     | AWS managed IPsec VPN connection over your existing internet                                                                        |
| When     | Quick and usually simple way to establish a secure tunneled connection to a VPC; Redundant link for Direct Connect or other VPC VPN |
| Pros     | Supports static routes or BGP peering and routing                                                                                   |
| Cons     | Dependent on your Internet connection                                                                                               |
| How      | See next slide                                                                                                                      |

#### Key Characteristics

* Uses existing internet connection
* Supports static routes and BGP peering
* Quick to set up
* Often used as Direct Connect backup

#### Setup Process

1. Configure customer-side endpoint (gateway/router)
2. Create virtual private gateway and VPN connection in AWS
3. Use AWS-generated config files for popular routers (Cisco, Juniper, Palo Alto)
4. Configure on-premises router with provided settings
5. Generate initial traffic to establish connection
6. Optionally configure BGP routing

<figure><img src="../../../../.gitbook/assets/image (72).png" alt=""><figcaption></figcaption></figure>

To automate route addition for VPN connectivity in a VPC, you enable **propagation** for the route table associated  with the VPG. Propagation means allowing the VPG to automatically add necessary routes to the designated route table, avoiding manual configurations.

<figure><img src="../../../../.gitbook/assets/image (74).png" alt=""><figcaption></figcaption></figure>

### Direct Connect

Dedicated private network connection to AWS backbone network.

| Category | Description                                                                                                                                                             |
| -------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| What     | Dedicated network connection over private lines straight into AWS backbone                                                                                              |
| When     | Require a "big pipe" into AWS; lots of resources and services being provided on AWS to your corporate users                                                             |
| Pros     | More predictable network performance; potential bandwidth cost reduction; up to 400 Gbps provisioned connections; Supports BGP peering and routing                      |
| Cons     | May require additional telecom and hosting provider relationships and/or new network circuits                                                                           |
| How      | Work with your existing Data Networking Provider; Create Virtual Interfaces (VIF) to connect to VPCs (private VIF) or other AWS service like S3 or Glacier (public VIF) |

See&#x20;

{% content-ref url="../../../aws/networking/vpc/direct-connect.md" %}
[direct-connect.md](../../../aws/networking/vpc/direct-connect.md)
{% endcontent-ref %}

### Direct Connect + VPN

IPsec VPN running over Direct Connect private lines.



| Category | Description                                                 |
| -------- | ----------------------------------------------------------- |
| What     | IPsec VPN connection over private lines                     |
| When     | Want added security of encrypted tunnel over Direct Connect |
| Pros     | More secure (in theory) than Direct Connect alone           |
| Cons     | More complexity introduced by VPN layer                     |
| How      | Work with your existing Data Networking Provider            |

<figure><img src="../../../../.gitbook/assets/image (78).png" alt=""><figcaption></figcaption></figure>

#### Use Cases

* Additional encryption layer over Direct Connect
* Multiple companies sharing single Direct Connect line
* Segregation of traffic between subsidiary companies

#### Characteristics

* More secure than standard Direct Connect
* Added complexity from VPN layer
* Uses existing Direct Connect infrastructure

### VPN CloudHub

DIY MPLS-like network using public internet and IPsec VPN.

| Category | Description                                                                                                                          |
| -------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| What     | Connect locations in a Hub and Spoke manner using AWS's Virtual Private Gateway                                                      |
| When     | Link remote offices for backup or primary WAN access to AWS resources and each other                                                 |
| Pros     | Reuses existing Internet connection; Supports BGP routes to direct traffic (for example, use MPLS first then CloudHub VPN as backup) |
| Cons     | Dependent on Internet connection; No inherent redundancy                                                                             |
| How      | Assign multiple Customer Gateways to a Virtual Private Gateway, each with their own BGP ASN and unique IP ranges                     |

<figure><img src="../../../../.gitbook/assets/image (82).png" alt=""><figcaption></figcaption></figure>

#### Key Characteristics

* Hub-and-spoke topology
* Uses AWS virtual private gateway as hub
* Supports BGP routing
* Depends on internet connection quality
* Good for connecting remote offices
* Can serve as MPLS backup

### Software VPN

Self-managed VPN solution using custom software/appliances.

<figure><img src="../../../../.gitbook/assets/image (80).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (81).png" alt=""><figcaption></figcaption></figure>

#### Characteristics

* Maximum flexibility
* Self-managed support
* Requires custom redundancy design
* Examples include OpenVPN on EC2

### Transit VPC

Central hub for connecting multiple VPCs and external networks.

<figure><img src="../../../../.gitbook/assets/image (83).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (84).png" alt=""><figcaption></figcaption></figure>

#### Key Characteristics

* Acts as network transit center
* Supports hybrid multi-cloud deployments
* Vendor solutions available (Cisco, Juniper, Riverbed)
* Requires careful redundancy planning
* Enables connections to other cloud providers

#### Use Cases

* Connecting geographically dispersed VPCs
* Multi-cloud connectivity
* Centralized network management

# Network to VPC Connectivity

### Managed VPN

A simple AWS-managed IPsec VPN connection over existing internet connections.

<figure><img src="../../../../.gitbook/assets/image (73).png" alt=""><figcaption></figcaption></figure>

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

<figure><img src="../../../../.gitbook/assets/image (75).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (76).png" alt=""><figcaption></figcaption></figure>

#### Key Characteristics

* Speeds up to 10 Gbps
* Predictable performance
* Requires telecom provider relationships
* Longer setup time
* Single connection (not inherently redundant)

#### Components

* Private Virtual Interface (VIF) - connects to VPCs
* Public Virtual Interface (VIF) - connects to public AWS services
* Recommended secondary connection (VPN or second Direct Connect)

### Direct Connect + VPN

IPsec VPN running over Direct Connect private lines.

<figure><img src="../../../../.gitbook/assets/image (77).png" alt=""><figcaption></figcaption></figure>

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

<figure><img src="../../../../.gitbook/assets/image (79).png" alt=""><figcaption></figcaption></figure>

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

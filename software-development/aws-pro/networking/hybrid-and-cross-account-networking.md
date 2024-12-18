# Hybrid and Cross-Account Networking

## AWS Direct Connect (DX)

<figure><img src="../../../.gitbook/assets/image (102).png" alt=""><figcaption></figcaption></figure>

### Key Features and Benefits

* Creates a private, dedicated connection from on-premises to AWS without using public internet
* Works with AWS partners/ISPs to establish physical connections
* Uses BGP (Border Gateway Protocol) for routing
* Can connect to VPCs via Virtual Private Gateway (VGW) or Direct Connect Gateway
* Supports both private and public virtual interfaces

### Use Cases

* Organizations requiring consistent network performance
* Large dataset transfers where internet data transfer costs would be high
* Compliance requirements demanding private connectivity
* Workloads sensitive to network latency
* Need for predictable network throughput

### Considerations

* Higher cost compared to VPN solutions
* Long provisioning time (minimum 2+ weeks)
* Requires working with third-party providers
* Physical infrastructure dependencies

### Direct Connect Gateway

* Enables connection to multiple VPCs across regions
* **Can connect to up to 3 Virtual Private Gateways or Transit Gateways**
* Provides centralized management of Direct Connect connections
* Supports private virtual interfaces for VPC access

<figure><img src="../../../.gitbook/assets/image (101).png" alt=""><figcaption></figcaption></figure>

## Site-to-Site VPN

<figure><img src="../../../.gitbook/assets/image (104).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (106).png" alt=""><figcaption></figcaption></figure>

### Key Features

* Quick to deploy compared to Direct Connect
* Uses IPsec protocol for encrypted communications
* Operates over public internet
* Provides two tunnels per connection for redundancy
* Native AWS monitoring tools available

### Components

* Customer Gateway (CGW) - on-premises endpoint
* Virtual Private Gateway (VGW) - AWS endpoint
* VPN Connection - secure tunnel between CGW and VGW
* Transit Gateway (optional) - for connecting to multiple VPCs

## High Availability Configurations

### Direct Connect + VPN Backup

* Primary: Direct Connect
* Backup: Site-to-Site VPN



<figure><img src="../../../.gitbook/assets/image (107).png" alt=""><figcaption></figcaption></figure>

* Automatic failover using BGP routing
* Provides redundancy at lower cost than dual DX

### Dual Direct Connect

<figure><img src="../../../.gitbook/assets/image (108).png" alt=""><figcaption></figcaption></figure>

* Two Direct Connect connections from different providers
* Higher cost but completely private network
* BGP routing for automatic failover
* DX Site Link feature for inter-datacenter connectivity

<figure><img src="../../../.gitbook/assets/image (110).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (109).png" alt=""><figcaption></figcaption></figure>

### Multi-Region Architecture

* Transit Gateway peering between regions
* Direct Connect Gateway for centralized connectivity
* Multiple Virtual Private Gateways for redundancy
* Availability Zone redundancy within each region

<figure><img src="../../../.gitbook/assets/image (112).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (115).png" alt=""><figcaption></figcaption></figure>

## Exam Tips

1. **Single Points of Failure**

* Identify potential network bottlenecks
* Understand redundancy requirements
* Know failover mechanisms (BGP routing)
* Consider multi-region requirements

2. **Cost vs Performance Trade-offs**

* Direct Connect: Higher cost, better performance, longer setup
* VPN: Lower cost, variable performance, quick setup
* Hybrid: Best of both worlds but more complex

3. **Transit Gateway Knowledge**

* Central hub for network connectivity
* Simplifies complex networking scenarios
* Supports multiple VPC attachments
* Enables cross-region connectivity

4. **Direct Connect vs VPN Decision Factors**

* Budget constraints
* Time requirements
* Security/compliance needs
* Performance requirements
* Data transfer volumes

5. **Scalability Considerations**

* Number of VPCs to connect
* Regional distribution of resources
* Future growth requirements
* Cross-account networking needs

Remember for the exam: You need to be able to design and recommend appropriate hybrid networking solutions based on various business requirements, taking into account factors like security, cost, performance, and high availability.












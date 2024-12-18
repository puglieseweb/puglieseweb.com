# AWS Global Accelerator

### Introduction

AWS Global Accelerator is a networking service that improves the availability and performance of applications by providing optimized routing through AWS's global infrastructure. It accomplishes this by establishing endpoints that direct traffic through AWS network edge locations, bypassing much of the public internet infrastructure.

<figure><img src="../../../.gitbook/assets/image (118).png" alt=""><figcaption></figcaption></figure>

### Key Features

#### Static IP Addresses

* Provides static public IP addresses for application endpoints
* These addresses remain constant even when underlying infrastructure changes
* Protected by AWS Shield by default for DDoS protection

#### Network Optimization

* Routes traffic through AWS's global network infrastructure
* Connects users to the nearest edge location
* **Reduces latency by up to 60%** compared to public internet routing
* **Utilizes anycast IP addresses and BGP routing for optimal path selection**

### How It Works

1. Users attempt to access an application
2. Instead of traversing the entire public internet, traffic is directed to the nearest AWS edge location
3. Traffic then travels over AWS's optimized network infrastructure
4. Requests reach the application endpoint through a more efficient path

### Common Use Cases

<figure><img src="../../../.gitbook/assets/image (120).png" alt=""><figcaption></figcaption></figure>

#### Public Applications

* Creates global static IP endpoints
* Directs traffic to the nearest edge location
* Improves global application accessibility and performance

#### Hybrid Networking

* Enhances VPN connection performance
* Provides a cost-effective alternative to Direct Connect
* Can connect to Transit Gateway for VPC routing
* Optimizes connections between on-premises data centers and AWS environment

<figure><img src="../../../.gitbook/assets/image (119).png" alt=""><figcaption></figcaption></figure>

#### Multi-Region Failover

* Supports endpoints across up to 10 regions
* Automatically routes to the nearest healthy endpoint
* Provides seamless failover during regional outages
* Maintains consistent IP addresses during failover events

#### Security and Protection

* Origin masking for enhanced security
* Built-in AWS Shield protection
* Helps protect applications from DDoS attacks
* Provides consistent security across global endpoints

### Performance Benefits

* Up to 60% faster response times compared to public internet
* Reduced number of network hops
* Optimized routing through AWS infrastructure
* Improved global application availability

### Technical Implementation

* Uses anycast IP addresses for edge location detection
* Implements BGP routing for traffic optimization
* Automatically routes to healthy endpoints
* Supports integration with AWS networking services

### Best Practices

* Use for applications requiring global reach
* Implement for multi-region architectures
* Consider as a cost-effective alternative to Direct Connect
* Utilize for applications requiring DDoS protection

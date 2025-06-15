# Concept



### Essential Networking Knowledge

* AZ and Region concepts
* VPC fundamentals
* Public vs Private subnets
* NAT and Source/Destination Checks
* Routing tables (default and local routes)
* IPv4 addressing and subnet masks (/16, /24)
* Basic networking terms (MAC address, ports, gateways vs routers)

### OSI Model Layers

1. Physical (Layer 1) - Physical cables
2. Data Link (Layer 2) - MAC addresses
3. Network (Layer 3) - IP, IPX, ARP
4. Transport (Layer 4) - TCP
5. Session (Layer 5)
6. Presentation (Layer 6)
7. Application (Layer 7) - Web browsers, HTTP

_Mnemonic: "Please Do Not Throw Sausage Pizza Away"_

<figure><img src="../../../../.gitbook/assets/image (66) (1).png" alt=""><figcaption></figcaption></figure>

**TLS runs ON TOP OF TCP:**

```
Application Data
    ↓
TLS Encryption (Layer 6)
    ↓
TCP Segments (Layer 4)
    ↓
IP Packets (Layer 3)
```

### AWS Responsibility Demarcation

* AWS responsibility: Generally Layers 1-2
* Customer responsibility: Generally Layers 3-7
* Some overlap exists at the boundary
* Virtual components (like ENIs) span both zones

### Network Protocols

1. TCP
   * Connection-based
   * Stateful
   * Acknowledges receipt
   * Example: Web traffic
2. UDP
   * Connectionless
   * Stateless
   * No acknowledgment required
   * Used in: Streaming media, DNS
3. ICMP
   * Network device communication
   * Used for: ping, traceroute
   * Network health monitoring

### Ephemeral Ports

* Short-lived transport protocol ports
* Range: Usually 49152-65535
* Above well-known ports (1024+)
* OS-dependent default settings
* Important for NACL and security group configuration

<figure><img src="../../../../.gitbook/assets/image (68) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (69) (1).png" alt=""><figcaption></figcaption></figure>

### VPC Reserved IP Addresses

In each subnet, five IPs are reserved:

1. Network address (.0)
2. VPC router (.1)
3. AWS DNS (.2)
4. Future use (.3)
5. Broadcast address (last IP)

_Note: Numbers are positional, not absolute_

<figure><img src="../../../../.gitbook/assets/image (70) (1).png" alt=""><figcaption></figcaption></figure>

### Availability Zone Assignment

* Physical-to-logical AZ mapping is account-specific

<figure><img src="../../../../.gitbook/assets/image (71).png" alt=""><figcaption></figcaption></figure>

* Benefits:
  * Load balancing across datacenters
  * Enhanced security through randomization
  * Example: us-west-2a may differ between accounts

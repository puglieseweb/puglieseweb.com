# IPv4 Address Classes and Subnet Masks

### Overview

IPv4 addresses are divided into different classes to organize and allocate IP address ranges efficiently. Each class has a specific range of first octet addresses and a corresponding default subnet mask.



The network portion of an IP address is the network ID that uniquely identify the network

### Class Details

#### Class A

* First Octet Range: 1 - 126
* Default Subnet Mask: 255.0.0.0 (/8 ⇒ 11111111.0.0.0)
* Format: NETWORK.HOST.HOST.HOST
* Available Host Addresses: Up to 16 million hosts per network
* Suitable for: Very large networks, such as the one used by Internet Providers

#### Class B

* First Octet Range: 128 - 191
* Default Subnet Mask: 255.255.0.0 (**/16** ⇒ 11111111.11111111.0.0)
* Format: NETWORK.NETWORK.HOST.HOST
* Available Host Addresses: Up to **65.000  hosts** per network
* Suitable for: Medium to large networks for medium to large organisations&#x20;

#### Class C

* First Octet Range: 192 - 223
* Default Subnet Mask: 255.255.255.0 (/24 ⇒ 11111111.11111111.11111111.0)
* Format: NETWORK.NETWORK.NETWORK.HOST
* Traditional Network Available Host Addresses: Up to 254 hosts (2 reserved address: network and broadcast)
* AWS Available Host Addresses: Up to 251 hosts per network (AWS reserves 5 addresses: network, VPC router, DNS, future use, broadcast)
* Suitable for: Small networks for small organisations and homes.

### Binary Structure Example

Using Class A as an example:

```
Subnet Mask: 255.0.0.0
Binary:      11111111.00000000.00000000.00000000
             NETWORK  .   HOST   .   HOST   .   HOST
```

### Important Notes

* The range 127 is reserved for loopback testing and is not included in any class
* These classes represent the traditional classful networking scheme
* **Modern networks typically use Classless Inter-Domain Routing (CIDR) for more flexible subnet allocation**
* For Traditional network the number of possible hosts is calculated using the formula 2^n - 2, where n is the number of host bits
* **For AWS network the number of possible hosts is calculated using the formula 2^n - 5, where n is the number of host bits.**

### Host Capacity

The structure can produce up to 16 million hosts per network, making it particularly suitable for large organizations and networks requiring extensive host addressing capabilities.

# CIDR ranges examples

When you create a subnet, you specify the CIDR block for the subnet, which is a subset of the VPC CIDR block. For IPv4, you cannot create a subnet with a CIDR block larger than a /16. For IPv6, the subnet size is fixed to be a /64 CIDR block.



## AWS VPC CIDR block ranges:

### IPv4

* **Minimum size: /28 (16 IP addresses)**
* **Maximum size: /16 (65,536 IP addresses)**
* Must be from private IPv4 address ranges:
  * 10.0.0.0/8
  * 172.16.0.0/12
  * 192.168.0.0/16

### IPv6

* Fixed size: /56 for VPC
* Fixed size: /64 for subnets

| CIDR                                       | Netmask         | 2^ Calculation   | Available IPs                                 | Common Use Cases                               |
| ------------------------------------------ | --------------- | ---------------- | --------------------------------------------- | ---------------------------------------------- |
| /32                                        | 255.255.255.255 | 2^(32-32) = 2^0  | 1                                             | Single host address (e.g., firewall rules)     |
| /31                                        | 255.255.255.254 | 2^(32-31) = 2^1  | 2                                             | Point-to-point links between routers           |
| /30                                        | 255.255.255.252 | 2^(32-30) = 2^2  | 4                                             | Router-to-router WAN links                     |
| /29                                        | 255.255.255.248 | 2^(32-29) = 2^3  | 8                                             | Very small office networks, IoT segments       |
| <mark style="color:purple;">**/28**</mark> | 255.255.255.240 | 2^(32-28) = 2^4  | 1<mark style="color:purple;">**6**</mark>     | Small branch offices, home labs                |
| /27                                        | 255.255.255.224 | 2^(32-27) = 2^5  | 32                                            | Small department networks                      |
| /26                                        | 255.255.255.192 | 2^(32-26) = 2^6  | 64                                            | Medium department networks                     |
| /25                                        | 255.255.255.128 | 2^(32-25) = 2^7  | 128                                           | Large department networks                      |
| /24                                        | 255.255.255.0   | 2^(32-24) = 2^8  | 256                                           | Standard enterprise subnet, AWS default subnet |
| /23                                        | 255.255.254.0   | 2^(32-23) = 2^9  | 512                                           | Large subnet spanning departments              |
| /22                                        | 255.255.252.0   | 2^(32-22) = 2^10 | 1,024                                         | Campus networks, data center segments          |
| /21                                        | 255.255.248.0   | 2^(32-21) = 2^11 | 2,048                                         | Small ISP allocations                          |
| /20                                        | 255.255.240.0   | 2^(32-20) = 2^12 | 4,096                                         | Medium ISP allocations                         |
| /19                                        | 255.255.224.0   | 2^(32-19) = 2^13 | 8,192                                         | Large ISP allocations                          |
| /18                                        | 255.255.192.0   | 2^(32-18) = 2^14 | 16,384                                        | Regional ISP networks                          |
| /17                                        | 255.255.128.0   | 2^(32-17) = 2^15 | 32,768                                        | Large regional networks                        |
| <mark style="color:purple;">**/16**</mark> | 255.255.0.0     | 2^(32-16) = 2^16 | <mark style="color:purple;">**65,536**</mark> | AWS VPC default, organization networks         |
| /8                                         | 255.0.0.0       | 2^(32-8) = 2^24  | 16,777,216                                    | Largest orgs, legacy Class A                   |
| /0                                         | 0.0.0.0         | 2^(32-0) = 2^32  | 4,294,967,296                                 | Entire IPv4 address space                      |

## Examples

1. What's the number of available IP addresses in:

* /24 = 256 IPs (254 usable)
* /28 = 16 IPs (14 usable)
* /27 = 32 IPs (30 usable)
* /16 = 65,536 IPs (65,534 usable)

2. Given VPC 10.0.0.0/16:

* What's the largest possible subnet? (/16)
* What's a valid subnet CIDR? (10.0.1.0/24)
* Is 11.0.0.0/24 valid? (No, outside VPC range)

3. For 192.168.1.0/24:

* First usable IP? (192.168.1.1)
* Last usable IP? (192.168.1.254)
* Broadcast address? (192.168.1.255)

4. Can these subnets overlap? 10.0.0.0/24 and 10.0.0.128/25 (Yes) 10.0.0.0/24 and 10.0.1.0/24 (No)
5. Split 10.0.0.0/24 into four equal subnets:

* 10.0.0.0/26
* 10.0.0.64/26
* 10.0.0.128/26
* 10.0.0.192/26

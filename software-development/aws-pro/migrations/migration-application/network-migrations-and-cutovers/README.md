# Network Migrations and Cutovers

### VPC IP Address Considerations

When migrating to AWS, avoiding IP address overlaps between your VPC and on-premise networks is crucial. VPCs support netmask ranges from /16 to /28, accommodating between 65,000 and 16 hosts respectively.

<figure><img src="../../../../../.gitbook/assets/image.png" alt=""><figcaption></figcaption></figure>

#### Reserved IP Addresses

In every VPC, five IP addresses are automatically reserved:

* Network address (first address)
* Broadcast address (last address)
* Router address
* DNS address
* Future use address (reserved by AWS)

For example, in a /28 network with 16 addresses:

* Total addresses: 16
* Unusable addresses: 5 (reserved)
* Available addresses: 11

<figure><img src="../../../../../.gitbook/assets/image (1).png" alt=""><figcaption></figcaption></figure>

### Network Connectivity Strategy

#### Initial VPN Setup

Most organizations begin their AWS journey with a VPN connection, which provides a secure and cost-effective starting point.

#### Transitioning to Direct Connect

As usage grows, organizations often migrate to Direct Connect while maintaining VPN as a backup solution. The transition can be managed smoothly using BGP routing:

1. Configure both VPN and Direct Connect with identical BGP prefixes
2. AWS automatically prefers the Direct Connect pathway
3. Configure on-premise network to prefer Direct Connect through either:
   * BGP weighting
   * Static routing

This setup ensures reliable connectivity while maintaining a fallback option through VPN.

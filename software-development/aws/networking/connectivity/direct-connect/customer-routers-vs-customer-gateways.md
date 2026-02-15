# Customer Routers VS Customer Gateways

AWS VPN Connections:

* Requires a Customer Gateway (CGW) on your on-premises side
* The CGW represents your physical router or software VPN appliance
* You must configure:&#x20;
  * The Virtual Private Gateway (VPG) in AWS
  * The Customer Gateway (CGW) in AWS
  * Your on-premises router/device
* Each VPN tunnel requires configuration of the Customer Gateway
* Supports static routes or BGP for routing

AWS Direct Connect:

* Uses your on-premises router directly - no Customer Gateway needed
* Works with your existing network provider or AWS Direct Connect Partner
* Uses BGP for routing between your network and AWS
* Requires configuration of Virtual Interfaces (VIFs):
  * Private VIF: To connect to VPCs
  * Public VIF: To connect to AWS public services like S3 or Glacier

Key Differences:

1. VPN (Customer Gateway):
   * Required for IPsec VPN tunnels
   * Runs over the internet
   * Configured in AWS and on-premises
   * Can use static or dynamic routing
2. Direct Connect (Router):
   * Uses your existing router
   * Dedicated private connection
   * No Customer Gateway configuration needed
   * Uses BGP routing only
   * Higher bandwidth capacity (up to 100 Gbps)

Note: If you implement Direct Connect plus VPN (for added security), you'll need both your router for Direct Connect and a Customer Gateway for the VPN component.

\

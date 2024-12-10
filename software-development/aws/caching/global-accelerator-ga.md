# Global Accelerator (GA)

>

Global Accelerator is more focused on improving network performance for applications that require static IP addresses or fast regional failover.

A networking service that sends user's traffic through AWS's global network infrastructure via accelerators (it usese the AWS Backbone Network which  is less congestioned of the public network.&#x20;

It can increase performance and help deal with IP caching by leveraging **Anycast IP.**

**Meant for TCP or UDP traffic!** Major difference from CloudFront.&#x20;

High-Level View:

* GA uses edge locations to help speed everything up.
* GA provides 2 static Anycast IP addresses for you accelerators.
* Dual-stack receives four static IP addresses (Two IPv4 and two IPv6)
* Static IP act as a single, fixed entry for ALL client traffic.&#x20;
* Standard: traffic routed based on user location, health checks, and weights.
* Custom: Traffic routed to specified EC2 instances and ports in a VPC.

# Global Accelerator (GA)

> Global Accelerator is more focused on **improving network performance for applications that require static IP addresses** or **fast regional failover**.

A networking service that sends user's traffic through AWS's global network infrastructure via accelerators (it uses the AWS Backbone Network which  is less congestioned of the public network.&#x20;

It can increase performance and help deal with **IP caching** by leveraging **Anycast IP.**

**Meant for TCP or UDP traffic!** Major difference from CloudFront.&#x20;

High-Level View:

* GA uses edge locations to help speed everything up.
* GA provides 2 static Anycast IP addresses for you accelerators.
* Dual-stack receives four static IP addresses (Two IPv4 and two IPv6)
* Static IP act as a single, fixed entry for ALL client traffic.&#x20;
* Standard: traffic routed based on user location, health checks, and weights.
* Custom: Traffic routed to specified **EC2 instances and ports in a VPC.**



## How Anycast IP addresses help solve IP caching issues

1. Understanding IP Caching Problem:

* DNS resolvers and clients cache IP addresses for performance
* If a server goes down, cached IPs still point to the failed server
* Users experience downtime until DNS TTL expires and cache refreshes

2. How Anycast IP Solves This:

* Multiple physical servers share the same IP address
* Network routers automatically direct traffic to the nearest available server
* If one server fails, traffic naturally routes to the next closest server

3. Key Benefits:

* No DNS cache issues because the IP address remains valid
* Automatic failover at the network layer
* Users connect to the closest working server without waiting for DNS updates
* Improved latency as users always reach the nearest point of presence

4. Real-world Example:

* CDNs like Cloudflare use Anycast extensively
* Same IP address works globally but routes to local edge servers
* If one edge server fails, connections seamlessly route to another
* No impact from cached DNS records

5. Additional Advantages:

* Simpler DNS configuration (single IP for multiple locations)
* Built-in DDoS mitigation (traffic gets distributed)
* Reduced latency through optimal routing
* Higher availability through automatic failover

The key point is that Anycast shifts failover handling from DNS to the network layer, eliminating issues with cached IP addresses entirely.

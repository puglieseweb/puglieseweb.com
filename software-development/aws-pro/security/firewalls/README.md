# Firewalls

In AWS, there are several types of firewalls available to secure your infrastructure:

**Network-Level Firewalls:**

1. Network Access Control Lists (NACLs):
   * Stateless firewall that operates at the subnet level
   * Controls inbound and outbound traffic for subnets within a VPC
   * Rules are processed in order, based on rule numbers
   * Can have both allow and deny rules
2. Security Groups:
   * Stateful firewall that operates at the instance level
   * Acts as a virtual firewall for EC2 instances and other AWS resources
   * Only allow rules (implicit deny for everything else)
   * Rules are evaluated as a collective set
3. AWS Network Firewall:
   1. Managed network firewall service for VPCs
   2. Provides deep packet inspection
   3. Supports both stateful and stateless filtering
   4. Can implement intrusion prevention system (IPS) functionality
   5. Allows custom rule creation using Suricata-compatible rules



**Application-Level Firewalls:**

1. AWS WAF (Web Application Firewall):
   1. Protects web applications from common web exploits
   2. Can be attached to CloudFront, Application Load Balancer, or API Gateway
   3. Provides protection against SQL injection, cross-site scripting (XSS), and other web attacks
   4. Allows creation of custom rules based on request patterns
2. AWS Shield:
   1. While not strictly a firewall, it provides DDoS protection
   2. Available in two tiers:
      1. Shield Standard (free, basic DDoS protection)
      2. Shield Advanced (paid, enhanced DDoS protection with 24/7 support)



### AWS Firewall Manager

* Centrally manages firewall rules across multiple accounts
* Can manage Security Groups, NACLs, and Network Firewall policies, WAF rules
* Particularly useful for organizations using AWS Organizations

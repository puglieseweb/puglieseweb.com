# Firewalls

In AWS, there are several types of firewalls available to secure your infrastructure:

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
3. AWS WAF (Web Application Firewall):
   * Protects web applications from common web exploits
   * Can be attached to CloudFront, Application Load Balancer, or API Gateway
   * Provides protection against SQL injection, cross-site scripting (XSS), and other web attacks
   * Allows creation of custom rules based on request patterns
4. AWS Network Firewall:
   * Managed network firewall service for VPCs
   * Provides deep packet inspection
   * Supports both stateful and stateless filtering
   * Can implement intrusion prevention system (IPS) functionality
   * Allows custom rule creation using Suricata-compatible rules
5. AWS Shield:
   * While not strictly a firewall, it provides DDoS protection
   * Available in two tiers:
     * Shield Standard (free, basic DDoS protection)
     * Shield Advanced (paid, enhanced DDoS protection with 24/7 support)
6. AWS Firewall Manager:
   * Centrally manages firewall rules across multiple accounts
   * Can manage Security Groups, NACLs, and Network Firewall policies, WAF rules
   * Particularly useful for organizations using AWS Organizations

Each of these firewall types serves different security needs and can be used in combination to create a layered security approach, often referred to as defense in depth.

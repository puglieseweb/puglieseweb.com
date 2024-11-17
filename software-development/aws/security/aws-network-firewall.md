# AWS Network Firewall

AWS Network Firewall deploys managed network protection across VPCs that can inspect traffic before it reaches Amazon API Gateway.

Key features:

* AWS manages the underlying infrastructure
* Provides an Intrusion Prevention System (IPS) for active traffic flow inspection

Use cases:

1. Filter Internet Traffic
   * Uses Access Control List (ACL) rules
   * Performs stateful inspection
   * Includes protocol detection
   * Provides intrusion prevention capabilities
2. Filter Outbound Traffic
   * Filter by URLs/domains
   * Filter by IP addresses
   * Supports content-based outbound traffic filtering
3. Inspect VPC-to-VPC Traffic
   * Enables security monitoring between VPCs
   * Provides centralized network security management

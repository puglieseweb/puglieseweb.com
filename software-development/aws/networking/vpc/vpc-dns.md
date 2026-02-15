# VPC DNS

Each VPC comes with a built-in DNS resolver (also known as the Amazon-provided DNS server) for several important reasons:

1. **Internal DNS Resolution**
   * It allows EC2 instances within the VPC to resolve the private DNS hostnames of other instances in the same VPC
   * By default, each EC2 instance gets a private DNS hostname like `ip-10-0-0-23.ec2.internal`
2. **Integration with Route 53**
   * The VPC DNS resolver works with Amazon Route 53 Resolver to handle DNS queries
   * It can resolve AWS service endpoints (like S3, RDS, etc.)
   * Supports private hosted zones for your custom domain names within the VPC
3. **DNS Resolution Settings**
   * The DNS resolver is automatically assigned an IP address at the base of your VPC network range plus 2
   * For example, if your VPC CIDR is 10.0.0.0/16, the DNS server IP would be 10.0.0.2
4. **Hybrid Networking**
   * Can be configured to resolve DNS queries between your VPC and on-premises networks
   * Supports DNS resolution across VPC peering connections

Key Points to Remember:

* The DNS resolver is free and automatically included with each VPC
* You can't disable it, but you can choose not to use it
* You can configure your own custom DNS servers if needed
* The DNS resolver IP is always the base network address +2 (this is one of those five reserved IPs we discussed earlier)

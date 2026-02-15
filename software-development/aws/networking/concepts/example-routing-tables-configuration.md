# Example Routing tables configuration

This is an example routing configuration for your multi-account transit gateway setup. The configuration includes:

1. A transit gateway route table with routes to all three VPCs
2. Individual VPC route tables for each VPC
3. Configuration notes including CIDR allocations and future considerations
4. Security recommendations

The routing tables are designed to:

* Allow direct communication between all VPCs through the transit gateway
* Keep inter-VPC traffic off the public internet
* Support future expansion to additional VPCs and regions
* Maintain proper isolation where needed



## Transit Gateway and VPC Routing Configuration

### Transit Gateway Route Table

The transit gateway route table will contain routes for all VPC CIDR blocks and direct traffic to the appropriate VPC attachments.

```
Transit Gateway Route Table: tgw-rtb-main

| Destination CIDR| Target Type | Target ID          | Notes                   |
|-----------------|-------------|--------------------|-------------------------|
| 10.1.0.0/16     | Attachment  | tgw-attach-vpc1    | Route to VPC 1          |
| 10.2.0.0/16     | Attachment  | tgw-attach-vpc2    | Route to VPC 2          |
| 10.3.0.0/16     | Attachment  | tgw-attach-vpc3    | Route to VPC 3          |
```

### VPC Route Tables

#### VPC 1 Route Table (Account 1)

```
VPC 1 Main Route Table: rtb-vpc1-main

| Destination CIDR | Target              | Notes                               |
|-----------------|---------------------|-------------------------------------|
| 10.1.0.0/16     | local              | Local VPC traffic                   |
| 10.2.0.0/16     | tgw-attach-vpc1    | Traffic to VPC 2 via Transit GW    |
| 10.3.0.0/16     | tgw-attach-vpc1    | Traffic to VPC 3 via Transit GW    |
| 0.0.0.0/0       | igw-xxx            | Internet traffic (if needed)        |
```

#### VPC 2 Route Table (Account 1)

```
VPC 2 Main Route Table: rtb-vpc2-main

| Destination CIDR | Target              | Notes                               |
|-----------------|---------------------|-------------------------------------|
| 10.2.0.0/16     | local              | Local VPC traffic                   |
| 10.1.0.0/16     | tgw-attach-vpc2    | Traffic to VPC 1 via Transit GW    |
| 10.3.0.0/16     | tgw-attach-vpc2    | Traffic to VPC 3 via Transit GW    |
| 0.0.0.0/0       | igw-xxx            | Internet traffic (if needed)        |
```

#### VPC 3 Route Table (Account 2)

```
VPC 3 Main Route Table: rtb-vpc3-main

| Destination CIDR | Target              | Notes                               |
|-----------------|---------------------|-------------------------------------|
| 10.3.0.0/16     | local              | Local VPC traffic                   |
| 10.1.0.0/16     | tgw-attach-vpc3    | Traffic to VPC 1 via Transit GW    |
| 10.2.0.0/16     | tgw-attach-vpc3    | Traffic to VPC 2 via Transit GW    |
| 0.0.0.0/0       | igw-xxx            | Internet traffic (if needed)        |
```

### Configuration Notes

1. Each VPC has its own CIDR block:
   * VPC 1: 10.1.0.0/16
   * VPC 2: 10.2.0.0/16
   * VPC 3: 10.3.0.0/16
2. Transit Gateway Configuration:
   * Auto-accept shared attachments: Enabled
   * Default route table association: Disabled
   * Default route table propagation: Disabled
   * DNS support: Enabled
   * VPN ECMP support: Enabled
   * Default route table: tgw-rtb-main
3. Transit Gateway Attachments:
   * Each VPC has a dedicated transit gateway attachment
   * Subnet route tables in each VPC point to their respective transit gateway attachment for inter-VPC traffic
   * Transit gateway attachments are associated with the main transit gateway route table
4. Future Considerations:
   * For additional VPCs in different regions:
     * Create new transit gateway in target region
     * Set up transit gateway peering attachment
     * Add routes for the new CIDR blocks in all route tables
     * Update transit gateway route tables in both regions
   * For new VPCs in same region:
     * Create new transit gateway attachment
     * Add routes for the new CIDR block in all route tables
     * Associate attachment with transit gateway route table
5. Security Considerations:
   * Network ACLs and Security Groups should be configured to allow necessary inter-VPC traffic
   * Consider implementing network traffic monitoring
   * Implement proper IAM roles and resource sharing between accounts
   * Enable flow logs for security analysis and troubleshooting

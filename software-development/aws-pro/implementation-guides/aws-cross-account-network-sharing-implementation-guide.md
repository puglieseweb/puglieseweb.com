# AWS Cross-Account Network Sharing Implementation Guide

##

### Overview

This document outlines the implementation steps for sharing a centrally managed network across multiple AWS accounts using AWS Organizations and AWS Resource Access Manager (RAM). **This solution enables centralized network management while allowing individual accounts to deploy resources within shared subnets.**

### Architecture Components

* AWS Organizations
* AWS Resource Access Manager (RAM)
* VPC and associated networking components
* IAM roles and permissions

### Prerequisites

1. AWS Organizations set up and configured
2. A dedicated infrastructure account for network management
3. Target accounts organized into appropriate Organizational Units (OUs)
4. Administrative access to both the Organizations management account and infrastructure account

### Implementation Steps

#### Step 1: Enable Resource Sharing in AWS Organizations

1. Log in to the AWS Organizations management account
2. Navigate to AWS Organizations console
3. Go to Settings
4.  Enable sharing for AWS RAM

    ```
    aws organizations enable-sharing-with-aws-organization
    ```

#### Step 2: VPC Setup in Infrastructure Account

1. Log in to the infrastructure account
2.  Create a VPC with appropriate CIDR range

    ```
    aws ec2 create-vpc --cidr-block 10.0.0.0/16
    ```
3.  Create subnets with desired CIDR ranges

    ```
    aws ec2 create-subnet --vpc-id <vpc-id> --cidr-block 10.0.1.0/24
    aws ec2 create-subnet --vpc-id <vpc-id> --cidr-block 10.0.2.0/24
    ```
4. Configure routing tables, internet gateway, and other network components

#### Step 3: Configure Resource Sharing using AWS RAM

1. Log in to the infrastructure account
2. Navigate to AWS RAM console
3.  Create a new resource share:

    ```
    aws ram create-resource-share \
      --name "central-network-share" \
      --resource-arns arn:aws:ec2:<region>:<account-id>:subnet/<subnet-id> \
      --principals arn:aws:organizations::<org-id>:ou/<ou-id>
    ```
4. Add subnets to the resource share
5. Specify the target Organizational Units (OUs)

#### Step 4: Verify Shared Subnets in Member Accounts

1. Log in to a member account
2. Navigate to VPC console
3. Check "Shared with me" section
4. Verify subnet availability

### Network Management Controls

#### Infrastructure Account Permissions

* Full network management capabilities
* Ability to modify VPC configurations
* Control over subnet sharing
* Management of routing and security

#### Member Account Permissions

* Ability to view shared subnets
* Permission to create resources in shared subnets
* No ability to modify network configurations
* Limited to resource-level operations

### Resource Creation in Shared Subnets

#### Allowed Operations in Member Accounts

* Launch EC2 instances
* Create load balancers
* Deploy RDS instances
* Create Lambda VPC endpoints
* Deploy ECS tasks

#### Restricted Operations in Member Accounts

* Modify subnet configurations
* Change routing tables
* Modify network ACLs
* Create or modify VPC endpoints

### Best Practices

1. Use consistent tagging across accounts
2. Implement proper CIDR planning for future expansion
3. Document shared subnet allocations
4. Regularly audit resource sharing configurations
5. Implement network monitoring in the infrastructure account

### Security Considerations

1. Implement proper security groups in the infrastructure account
2. Use Network ACLs for additional security
3. Monitor VPC Flow Logs
4. Regular security audits
5. Implement proper IAM policies

### Monitoring and Maintenance

#### Infrastructure Account Responsibilities

* Monitor network utilization
* Manage subnet sharing
* Update network configurations
* Security group management
* Network troubleshooting

#### Member Account Responsibilities

* Monitor resource utilization
* Manage security groups for their resources
* Report network issues to infrastructure team
* Comply with network policies

### Troubleshooting

#### Common Issues

1. Subnet sharing not visible in member accounts
   * Verify Organizations sharing is enabled
   * Check OU assignments
   * Verify RAM configurations
2. Unable to launch resources in shared subnets
   * Check IAM permissions
   * Verify security group configurations
   * Ensure subnet has available IP addresses
3. Network connectivity issues
   * Verify routing tables
   * Check security group rules
   * Validate network ACL settings

### Documentation and Support

1. Maintain updated network diagrams
2. Document CIDR allocations
3. Keep sharing configurations documented
4. Establish support procedures
5. Regular review and updates of documentation

### Conclusion

This implementation provides centralized network management while allowing individual accounts to deploy resources efficiently. Regular monitoring and maintenance ensure optimal performance and security across the organization.

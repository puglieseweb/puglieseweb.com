# Cross-Account Route 53 Private Hosted Zone Implementation Guide

### Prerequisites

* Admin access to both AWS accounts
* VPC ID from Account B
* Hosted Zone ID from Account A
* AWS CLI configured with profiles for both accounts

### Implementation Steps

#### 1. Account A - Create Authorization

```bash
# Get Hosted Zone ID
aws route53 list-hosted-zones --profile account-a

# Create Authorization
aws route53 create-vpc-association-authorization \
    --hosted-zone-id <HOSTED_ZONE_ID> \
    --vpc VPCRegion=us-east-1,VPCId=<VPC_ID> \
    --profile account-a
```

#### 2. Account B - Create Association

```bash
# Associate VPC with Hosted Zone
aws route53 associate-vpc-with-hosted-zone \
    --hosted-zone-id <HOSTED_ZONE_ID> \
    --vpc VPCRegion=us-east-1,VPCId=<VPC_ID> \
    --profile account-b
```

#### 3. Account A - Clean Up Authorization

```bash
# Delete Authorization (after association is complete)
aws route53 delete-vpc-association-authorization \
    --hosted-zone-id <HOSTED_ZONE_ID> \
    --vpc VPCRegion=us-east-1,VPCId=<VPC_ID> \
    --profile account-a
```

### CloudFormation Implementation

#### Account A Template

```yaml
Resources:
  VPCAuthorizationRule:
    Type: 'AWS::Route53::HostedZoneAuthorizationRule'
    Properties:
      HostedZoneId: !Ref HostedZoneId
      VPC:
        VPCId: !Ref TargetVPCId
        VPCRegion: !Ref TargetVPCRegion

Parameters:
  HostedZoneId:
    Type: String
    Description: Private Hosted Zone ID
  TargetVPCId:
    Type: String
    Description: VPC ID from Account B
  TargetVPCRegion:
    Type: String
    Description: Region of VPC in Account B
```

#### Account B Template

```yaml
Resources:
  VPCHostedZoneAssociation:
    Type: 'AWS::Route53::HostedZoneVPCAssociation'
    Properties:
      HostedZoneId: !Ref HostedZoneId
      VPCId: !Ref VPCId
      VPCRegion: !Ref 'AWS::Region'

Parameters:
  HostedZoneId:
    Type: String
    Description: Private Hosted Zone ID from Account A
  VPCId:
    Type: String
    Description: VPC ID in Account B
```

### Verification Steps

1. Test DNS Resolution:

```bash
# From EC2 instance in Account B
nslookup db.example.com
dig db.example.com
```

2. Check VPC Associations:

```bash
aws route53 get-hosted-zone \
    --id <HOSTED_ZONE_ID> \
    --profile account-a
```

### Troubleshooting

1. DNS Resolution Issues:
   * Verify DHCP options set
   * Check security groups
   * Confirm VPC DNS attributes
2. Authorization Issues:
   * Verify IAM permissions
   * Check account IDs
   * Confirm VPC ID and region
3. Association Issues:
   * Maximum VPC associations (50)
   * Region compatibility
   * VPC DNS settings

### Security Considerations

1. IAM Policies

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "route53:CreateVPCAssociationAuthorization",
                "route53:DeleteVPCAssociationAuthorization"
            ],
            "Resource": "arn:aws:route53:::hostedzone/*"
        }
    ]
}
```

2. Network Access
   * Enable DNS hostnames
   * Enable DNS resolution
   * Configure proper NACL rules

### Monitoring

1. CloudWatch Metrics
   * DNS queries
   * Resolution success rates
2. CloudTrail Logs
   * API activity
   * Authorization changes

### Cost Considerations

* Route 53 hosted zone charges
* DNS query charges
* Data transfer costs

### Best Practices

1. Clean up authorizations
2. Use infrastructure as code
3. Implement proper monitoring
4. Document VPC associations
5. Regular testing

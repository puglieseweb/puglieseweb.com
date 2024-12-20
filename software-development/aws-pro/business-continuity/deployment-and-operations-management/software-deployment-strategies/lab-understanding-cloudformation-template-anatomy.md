# LAB: Understanding CloudFormation Template Anatomy

### Introduction

This lab takes an in-depth look at CloudFormation template anatomy. Each section of a CloudFormation template will be covered in detail, and, ultimately, a CloudFormation template will be constructed piece by piece. After completing this lab, the student will have a deeper understanding of constructing CloudFormation templates in both JSON and YAML.

<figure><img src="../../../../../.gitbook/assets/image (136).png" alt=""><figcaption></figcaption></figure>

### Solution

Log in with the credentials provided, and make sure you are in the `us-east-1` (N. Virginia) region.

Download the templates used in the lab [here](https://github.com/natonic/CloudFormation-Deep-Dive/tree/master/Labs/TemplateAnatomy)

```yaml
AWSTemplateFormatVersion: 2010-09-09
Description: >-
  This template creates an EC2 instance based on the region and selection of an
  AMI ID. It also will create a Security Group.
Parameters:
  MySubnet:
    Description: My subnet from my VPC
    Type: String
    Default: subnet-YYYYYYYY
  MySG:
    Description: My Security Group from my VPC
    Type: String
    Default: SG-YYYYYYYY
  KeyName:
    Description: Name of an existing EC2 KeyPair to enable SSH access to the instance
    Type: 'AWS::EC2::KeyPair::KeyName'
    ConstraintDescription: must be the name of an existing EC2 KeyPair.
  InstanceType:
    Description: WebServer EC2 instance type
    Type: String
    Default: t2.small
    AllowedValues:
      - t1.micro
      - t2.nano
      - t2.micro
      - t2.small
      - t2.medium
      - t2.large
      - t3.micro
      - m1.small
      - m1.medium
      - m1.large
      - m1.xlarge
      - m2.xlarge
      - m2.2xlarge
      - m2.4xlarge
      - m3.medium
      - m3.large
      - m3.xlarge
      - m3.2xlarge
      - m4.large
      - m4.xlarge
      - m4.2xlarge
      - m4.4xlarge
      - m4.10xlarge
      - c1.medium
      - c1.xlarge
      - c3.large
      - c3.xlarge
      - c3.2xlarge
      - c3.4xlarge
      - c3.8xlarge
      - c4.large
      - c4.xlarge
      - c4.2xlarge
      - c4.4xlarge
      - c4.8xlarge
      - g2.2xlarge
      - g2.8xlarge
      - r3.large
      - r3.xlarge
      - r3.2xlarge
      - r3.4xlarge
      - r3.8xlarge
      - i2.xlarge
      - i2.2xlarge
      - i2.4xlarge
      - i2.8xlarge
      - d2.xlarge
      - d2.2xlarge
      - d2.4xlarge
      - d2.8xlarge
      - hi1.4xlarge
      - hs1.8xlarge
      - cr1.8xlarge
      - cc2.8xlarge
      - cg1.4xlarge
    ConstraintDescription: must be a valid EC2 instance type.
  SSHLocation:
    Description: The IP address range that can be used to SSH to the EC2 instances
    Type: String
    MinLength: '9'
    MaxLength: '18'
    Default: 0.0.0.0/0
    AllowedPattern: '(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})/(\d{1,2})'
    ConstraintDescription: must be a valid IP CIDR range of the form x.x.x.x/x.
Mappings:
  AWSInstanceType2Arch:
    t1.micro:
      Arch: HVM64
    t2.nano:
      Arch: HVM64
    t2.micro:
      Arch: HVM64
    t2.small:
      Arch: HVM64
    t2.medium:
      Arch: HVM64
    t2.large:
      Arch: HVM64
    t3.micro:
      Arch: HVM64
    m1.small:
      Arch: HVM64
    m1.medium:
      Arch: HVM64
    m1.large:
      Arch: HVM64
    m1.xlarge:
      Arch: HVM64
    m2.xlarge:
      Arch: HVM64
    m2.2xlarge:
      Arch: HVM64
    m2.4xlarge:
      Arch: HVM64
    m3.medium:
      Arch: HVM64
    m3.large:
      Arch: HVM64
    m3.xlarge:
      Arch: HVM64
    m3.2xlarge:
      Arch: HVM64
    m4.large:
      Arch: HVM64
    m4.xlarge:
      Arch: HVM64
    m4.2xlarge:
      Arch: HVM64
    m4.4xlarge:
      Arch: HVM64
    m4.10xlarge:
      Arch: HVM64
    c1.medium:
      Arch: HVM64
    c1.xlarge:
      Arch: HVM64
    c3.large:
      Arch: HVM64
    c3.xlarge:
      Arch: HVM64
    c3.2xlarge:
      Arch: HVM64
    c3.4xlarge:
      Arch: HVM64
    c3.8xlarge:
      Arch: HVM64
    c4.large:
      Arch: HVM64
    c4.xlarge:
      Arch: HVM64
    c4.2xlarge:
      Arch: HVM64
    c4.4xlarge:
      Arch: HVM64
    c4.8xlarge:
      Arch: HVM64
    g2.2xlarge:
      Arch: HVMG2
    g2.8xlarge:
      Arch: HVMG2
    r3.large:
      Arch: HVM64
    r3.xlarge:
      Arch: HVM64
    r3.2xlarge:
      Arch: HVM64
    r3.4xlarge:
      Arch: HVM64
    r3.8xlarge:
      Arch: HVM64
    i2.xlarge:
      Arch: HVM64
    i2.2xlarge:
      Arch: HVM64
    i2.4xlarge:
      Arch: HVM64
    i2.8xlarge:
      Arch: HVM64
    d2.xlarge:
      Arch: HVM64
    d2.2xlarge:
      Arch: HVM64
    d2.4xlarge:
      Arch: HVM64
    d2.8xlarge:
      Arch: HVM64
    hi1.4xlarge:
      Arch: HVM64
    hs1.8xlarge:
      Arch: HVM64
    cr1.8xlarge:
      Arch: HVM64
    cc2.8xlarge:
      Arch: HVM64
  AWSInstanceType2NATArch:
    t1.micro:
      Arch: NATHVM64
    t2.nano:
      Arch: NATHVM64
    t2.micro:
      Arch: NATHVM64
    t2.small:
      Arch: NATHVM64
    t2.medium:
      Arch: NATHVM64
    t2.large:
      Arch: NATHVM64
    t3.micro:
      Arch: NATHVM64
    m1.small:
      Arch: NATHVM64
    m1.medium:
      Arch: NATHVM64
    m1.large:
      Arch: NATHVM64
    m1.xlarge:
      Arch: NATHVM64
    m2.xlarge:
      Arch: NATHVM64
    m2.2xlarge:
      Arch: NATHVM64
    m2.4xlarge:
      Arch: NATHVM64
    m3.medium:
      Arch: NATHVM64
    m3.large:
      Arch: NATHVM64
    m3.xlarge:
      Arch: NATHVM64
    m3.2xlarge:
      Arch: NATHVM64
    m4.large:
      Arch: NATHVM64
    m4.xlarge:
      Arch: NATHVM64
    m4.2xlarge:
      Arch: NATHVM64
    m4.4xlarge:
      Arch: NATHVM64
    m4.10xlarge:
      Arch: NATHVM64
    c1.medium:
      Arch: NATHVM64
    c1.xlarge:
      Arch: NATHVM64
    c3.large:
      Arch: NATHVM64
    c3.xlarge:
      Arch: NATHVM64
    c3.2xlarge:
      Arch: NATHVM64
    c3.4xlarge:
      Arch: NATHVM64
    c3.8xlarge:
      Arch: NATHVM64
    c4.large:
      Arch: NATHVM64
    c4.xlarge:
      Arch: NATHVM64
    c4.2xlarge:
      Arch: NATHVM64
    c4.4xlarge:
      Arch: NATHVM64
    c4.8xlarge:
      Arch: NATHVM64
    g2.2xlarge:
      Arch: NATHVMG2
    g2.8xlarge:
      Arch: NATHVMG2
    r3.large:
      Arch: NATHVM64
    r3.xlarge:
      Arch: NATHVM64
    r3.2xlarge:
      Arch: NATHVM64
    r3.4xlarge:
      Arch: NATHVM64
    r3.8xlarge:
      Arch: NATHVM64
    i2.xlarge:
      Arch: NATHVM64
    i2.2xlarge:
      Arch: NATHVM64
    i2.4xlarge:
      Arch: NATHVM64
    i2.8xlarge:
      Arch: NATHVM64
    d2.xlarge:
      Arch: NATHVM64
    d2.2xlarge:
      Arch: NATHVM64
    d2.4xlarge:
      Arch: NATHVM64
    d2.8xlarge:
      Arch: NATHVM64
    hi1.4xlarge:
      Arch: NATHVM64
    hs1.8xlarge:
      Arch: NATHVM64
    cr1.8xlarge:
      Arch: NATHVM64
    cc2.8xlarge:
      Arch: NATHVM64
  AWSRegionArch2AMI:
    us-east-1:
      HVM64: ami-0beafb294c86717a8
      HVMG2: ami-02354e95b39ca8dec
    us-west-2:
      HVM64: ami-041fcb43d4730cf32
      HVMG2: ami-0873b46c45c11058d
    us-west-1:
      HVM64: ami-0a1ef8665fe122a96
      HVMG2: ami-05655c267c89566dd
    eu-west-1:
      HVM64: ami-06cd7f9c4486344a5
      HVMG2: ami-07d9160fa81ccffb5
    eu-west-2:
      HVM64: ami-01ac84be28584498d
      HVMG2: ami-0a13d44dccf1f5cf6
    eu-west-3:
      HVM64: ami-0a682c61cac068f2f
      HVMG2: ami-093fa4c538885becf
    eu-central-1:
      HVM64: ami-05d403b512bf100eb
      HVMG2: ami-0c115dbd34c69a004
    eu-north-1:
      HVM64: ami-0dd62557e4298551a
      HVMG2: ami-039609244d2810a6b
    ap-northeast-1:
      HVM64: ami-0d1e3062992b86514
      HVMG2: ami-0cc75a8978fbbc969
    ap-northeast-2:
      HVM64: ami-07a28b8671656c8c1
      HVMG2: ami-0bd7691bf6470fe9c
    ap-northeast-3:
      HVM64: ami-0e740663443574970
      HVMG2: ami-043bb1ed348aaf857
    ap-southeast-1:
      HVM64: ami-00c3854c1706a382f
      HVMG2: ami-0cd31be676780afa7
    ap-southeast-2:
      HVM64: ami-09ba19d0563c3d553
      HVMG2: ami-0ded330691a314693
    ap-south-1:
      HVM64: ami-0571d68822206714c
      HVMG2: ami-0ebc1ac48dfd14136
    us-east-2:
      HVM64: ami-0f4665edc97a93bea
      HVMG2: ami-07c8bc5c1ce9598c3
    ca-central-1:
      HVM64: ami-0a7c38a2574890e5c
      HVMG2: ami-013d1df4bcea6ba95
    sa-east-1:
      HVM64: ami-0cd294bc0ed0be0e4
      HVMG2: ami-018ccfb6b4745882a
    cn-north-1:
      HVM64: ami-053617c9d818c1189
      HVMG2: NOT_SUPPORTED
    cn-northwest-1:
      HVM64: ami-0f7937761741dc640
      HVMG2: NOT_SUPPORTED
Resources: 
 EC2Instance:
  Type: 'AWS::EC2::Instance'
  Properties:
    InstanceType: !Ref InstanceType
    SubnetId: !Ref MySubnet
    SecurityGroupIds:
      - !Ref MySG
    KeyName: !Ref KeyName
    ImageId: !FindInMap 
      - AWSRegionArch2AMI
      - !Ref 'AWS::Region'
      - !FindInMap 
        - AWSInstanceType2Arch
        - !Ref InstanceType
        - Arch
Outputs:
  InstanceId:
    Description: InstanceId of the newly created EC2 instance
    Value: !Ref EC2Instance
  AZ:
    Description: Availability Zone of the newly created EC2 instance
    Value: !GetAtt 
      - EC2Instance
      - AvailabilityZone
  PublicDNS:
    Description: Public DNSName of the newly created EC2 instance
    Value: !GetAtt 
      - EC2Instance
      - PublicDnsName
  PublicIP:
    Description: Public IP address of the newly created EC2 instance
    Value: !GetAtt 
      - EC2Instance
      - PublicIp
```

\


#### Create a CloudFormation Stack

1. Navigate to CloudFormation.
2. Click **Create stack**.
3. In the new CloudFormation page, click _Designer_
4. Click the _Template_ tab at the bottom.
5. Copy everything in the `Template_Anatomy2.yaml` file ([found on GitHub](https://raw.githubusercontent.com/natonic/CloudFormation-Deep-Dive/master/Labs/TemplateAnatomy/Template_Anatomy2.yaml)), and paste it into the _Template_ window.
6. In a new browser tab, navigate to **EC2** > **Key Pairs**.
7. Click **Create Key Pair**.
8. Give it a key pair name of "tempanatomy", and click **Create**.
9. Click **Security Groups** in the left-hand menu.
10. Copy the security group ID and paste it into a text file, since we'll need it in a minute.
11. Navigate to **VPC** > **Subnets**.
12. Select one of the listed subnets, and copy its subnet ID. Paste it into a text file, since we'll also need it later.
13. Back in the CloudFormation template window, click the checkbox at the top to validate the template, and then click the cloud icon with the up arrow to create the stack.
14. Click **Next**.
15. On the stack details page, set the following values:
    * _Stack name_: **tempanatomyLab**
    * _InstanceType_: **t2.micro**
    * _KeyName_: **tempanatomy**
    * _MySG_: Paste in the security group ID you copied earlier
    * _MySubnet_: Paste in the subnet ID you copied earlier
16. Click **Next**.
17. On the stack options page, set the _Key_ as "name" and _Value_ as "tempanatomy".
18. Click **Next**.
19. Click **Create stack**. It will take a few minutes for it to fully be created.

#### Delete a CloudFormation Stack

1. Once it's created, click **Delete** at the top.
2. In the confirmation dialog, click **Delete stack**.
3. Monitor the deletion process by watching the _Events_ tab.

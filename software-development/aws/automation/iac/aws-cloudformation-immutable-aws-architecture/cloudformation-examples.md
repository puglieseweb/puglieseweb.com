# CloudFormation examples

In the CloudFormation example I provided, there's no `Mappings` section. Let me add an example of a Mappings section that's commonly tested in SAA-C03:

```yaml
Mappings:
  RegionMap:
    us-east-1:
      AMI: "ami-0b5eea76982371e91"
      InstanceType: "t2.micro"
    us-west-2:
      AMI: "ami-0ceecbb0f30a902a8"
      InstanceType: "t2.micro"
    eu-west-1:
      AMI: "ami-0d71ea30463e0ff8d"
      InstanceType: "t2.micro"

  EnvironmentMap:
    dev:
      InstanceType: "t2.micro"
      MultiAZ: false
    prod:
      InstanceType: "t2.small"
      MultiAZ: true
```

To use these mappings, you would reference them like this:

```yaml
Resources:
  EC2Instance:
    Type: AWS::EC2::Instance
    Properties:
      ImageId: !FindInMap [RegionMap, !Ref 'AWS::Region', AMI]
      InstanceType: !FindInMap [RegionMap, !Ref 'AWS::Region', InstanceType]
```

The key things to remember about Mappings for the exam:

* They provide a way to declare conditional values based on region, environment, etc.
* Use `!FindInMap` intrinsic function to retrieve values
* Common uses include:
  * Region-specific AMI IDs
  * Instance types per environment
  * Configuration settings based on environment type
  * Different settings for different AWS regions

### Other examples

##

```yaml
# 1. Basic VPC Setup
Resources:
  MyVPC:
    Type: AWS::EC2::VPC
    Properties:
      CidrBlock: 10.0.0.0/16
      EnableDnsHostnames: true
      EnableDnsSupport: true
      Tags:
        - Key: Name
          Value: MyVPC

  PublicSubnet1:
    Type: AWS::EC2::Subnet
    Properties:
      VpcId: !Ref MyVPC
      CidrBlock: 10.0.1.0/24
      AvailabilityZone: !Select [0, !GetAZs '']
      MapPublicIpOnLaunch: true

# 2. EC2 Instance with Security Group
  WebServerSecurityGroup:
    Type: AWS::EC2::SecurityGroup
    Properties:
      GroupDescription: Allow HTTP and SSH
      VpcId: !Ref MyVPC
      SecurityGroupIngress:
        - IpProtocol: tcp
          FromPort: 80
          ToPort: 80
          CidrIp: 0.0.0.0/0
        - IpProtocol: tcp
          FromPort: 22
          ToPort: 22
          CidrIp: 0.0.0.0/0

  WebServerInstance:
    Type: AWS::EC2::Instance
    Properties:
      InstanceType: t2.micro
      ImageId: ami-0123456789abcdef0  # Replace with actual AMI ID
      SubnetId: !Ref PublicSubnet1
      SecurityGroupIds: 
        - !Ref WebServerSecurityGroup
      UserData:
        Fn::Base64: !Sub |
          #!/bin/bash
          yum update -y
          yum install -y httpd
          systemctl start httpd
          systemctl enable httpd

# 3. S3 Bucket with Lifecycle Policy
  DataBucket:
    Type: AWS::S3::Bucket
    Properties:
      VersioningConfiguration:
        Status: Enabled
      LifecycleConfiguration:
        Rules:
          - Id: TransitionToIA
            Status: Enabled
            Transitions:
              - TransitionInDays: 90
                StorageClass: STANDARD_IA

# 4. Auto Scaling Group with Launch Template
  WebServerLaunchTemplate:
    Type: AWS::EC2::LaunchTemplate
    Properties:
      LaunchTemplateData:
        InstanceType: t2.micro
        ImageId: ami-0123456789abcdef0
        SecurityGroupIds:
          - !Ref WebServerSecurityGroup

  WebServerASG:
    Type: AWS::AutoScaling::AutoScalingGroup
    Properties:
      VPCZoneIdentifier: 
        - !Ref PublicSubnet1
      LaunchTemplate:
        LaunchTemplateId: !Ref WebServerLaunchTemplate
        Version: !GetAtt WebServerLaunchTemplate.LatestVersionNumber
      MinSize: 1
      MaxSize: 3
      DesiredCapacity: 2

# 5. Application Load Balancer
  ApplicationLoadBalancer:
    Type: AWS::ElasticLoadBalancingV2::LoadBalancer
    Properties:
      Subnets:
        - !Ref PublicSubnet1
      SecurityGroups:
        - !Ref WebServerSecurityGroup
      
  ALBListener:
    Type: AWS::ElasticLoadBalancingV2::Listener
    Properties:
      LoadBalancerArn: !Ref ApplicationLoadBalancer
      Port: 80
      Protocol: HTTP
      DefaultActions:
        - Type: forward
          TargetGroupArn: !Ref ALBTargetGroup

# 6. RDS Instance with Multi-AZ
  DatabaseInstance:
    Type: AWS::RDS::DBInstance
    Properties:
      DBInstanceClass: db.t3.micro
      Engine: mysql
      MultiAZ: true
      StorageType: gp2
      AllocatedStorage: 20
      MasterUsername: admin
      MasterUserPassword: !Ref DBPassword
      VPCSecurityGroups:
        - !Ref DBSecurityGroup

Parameters:
  DBPassword:
    Type: String
    NoEcho: true
    MinLength: 8
    Description: Database admin password

Outputs:
  VPCId:
    Description: VPC ID
    Value: !Ref MyVPC
  
  LoadBalancerDNS:
    Description: DNS Name of the Application Load Balancer
    Value: !GetAtt ApplicationLoadBalancer.DNSName
```

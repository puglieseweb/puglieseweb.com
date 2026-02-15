# !Ref VS !GetAtt





The `!GetAtt` (Get Attribute) is a CloudFormation intrinsic function that helps you get the value of an attribute from a resource that has been created in your CloudFormation template.

In the example I showed earlier, `!GetAtt NotificationFunction.Arn` is getting the ARN (Amazon Resource Name) of a Lambda function named "NotificationFunction".

Here are some common uses of !GetAtt:

```yaml
# Getting Lambda Function ARN
LambdaArn: !GetAtt MyLambdaFunction.Arn

# Getting S3 Bucket Domain Name
BucketDomainName: !GetAtt MyS3Bucket.DomainName

# Getting DynamoDB Table Stream ARN
StreamArn: !GetAtt MyDynamoTable.StreamArn

# Getting VPC ID
VpcId: !GetAtt MyVPC.VpcId

# Getting RDS Endpoint Address
DbEndpoint: !GetAtt MyRDSInstance.Endpoint.Address
```

The syntax is:

```yaml
!GetAtt ResourceLogicalName.AttributeName
```

Where:

* ResourceLogicalName: The logical name you gave to the resource in your template
* AttributeName: The specific attribute you want to reference

This is particularly useful because:

1. You don't need to hardcode values
2. It ensures resources are properly linked together
3. CloudFormation will automatically resolve the actual value during deployment
4. It helps maintain dependencies between resources

For example, if you're creating a Lambda function and need to reference it in an EventBridge rule, you would:

```yaml
Resources:
  # Lambda Function Definition
  NotificationFunction:
    Type: AWS::Lambda::Function
    Properties:
      FunctionName: ec2-state-change-handler
      Runtime: nodejs18.x
      Handler: index.handler
      Code:
        ZipFile: |
          exports.handler = async (event) => {
            console.log('Received EC2 state change:', JSON.stringify(event, null, 2));
            return {
              statusCode: 200,
              body: JSON.stringify('Successfully processed EC2 state change')
            };
          };
      Role: !GetAtt LambdaExecutionRole.Arn
      Timeout: 30
      MemorySize: 128

  # Lambda Execution Role
  LambdaExecutionRole:
    Type: AWS::IAM::Role
    Properties:
      AssumeRolePolicyDocument:
        Version: '2012-10-17'
        Statement:
          - Effect: Allow
            Principal:
              Service: lambda.amazonaws.com
            Action: sts:AssumeRole
      ManagedPolicyArns:
        - arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole

  # SNS Topic for Notifications
  NotificationTopic:
    Type: AWS::SNS::Topic
    Properties:
      TopicName: ec2-state-change-notifications

  # EventBridge Rule
  EC2StateChangeRule:
    Type: AWS::Events::Rule
    Properties:
      Name: ec2-state-change-rule
      Description: "Rule to monitor EC2 instance state changes"
      EventPattern:
        source:
          - "aws.ec2"
        detail-type:
          - "EC2 Instance State-change Notification"
        detail:
          state:
            - "stopped"
            - "running"
      State: "ENABLED"
      Targets:
        - Arn: !GetAtt NotificationFunction.Arn
          Id: "ProcessEC2StateChangeLambda"
        - Arn: !Ref NotificationTopic
          Id: "NotifyEC2StateChangeSNS"

  # EventBridge IAM Role
  EventBridgeInvokeLambdaRole:
    Type: AWS::IAM::Role
    Properties:
      AssumeRolePolicyDocument:
        Version: "2012-10-17"
        Statement:
          - Effect: Allow
            Principal:
              Service: events.amazonaws.com
            Action: sts:AssumeRole
      Policies:
        - PolicyName: InvokeLambdaPolicy
          PolicyDocument:
            Version: "2012-10-17"
            Statement:
              - Effect: Allow
                Action:
                  - lambda:InvokeFunction
                Resource: !GetAtt NotificationFunction.Arn
```



## !Ref

The `!Ref` (Reference) is another CloudFormation intrinsic function that allows you to refer to resources or parameters in your template. It's simpler than `!GetAtt` because it typically returns a default value for the resource.

For different resource types, `!Ref` returns different values:

1. For SNS Topics -> Returns the Topic ARN
2. For S3 Buckets -> Returns the Bucket name
3. For Parameters -> Returns the parameter value
4. For EC2 Instances -> Returns the instance ID

Here are some common examples:

```yaml
Resources:
  # Creating an SNS Topic
  MyTopic:
    Type: AWS::SNS::Topic
    Properties:
      TopicName: my-notification-topic

  # Creating an SQS Queue
  MyQueue:
    Type: AWS::SQS::Queue
    Properties:
      QueueName: my-queue

  # Using !Ref to reference these resources
  MyLambdaFunction:
    Type: AWS::Lambda::Function
    Properties:
      Environment:
        Variables:
          TOPIC_ARN: !Ref MyTopic     # Gets the Topic ARN
          QUEUE_URL: !Ref MyQueue     # Gets the Queue URL

# Using with Parameters
Parameters:
  EnvironmentName:
    Type: String
    Default: dev

  # Using !Ref with a parameter
  MyBucket:
    Type: AWS::S3::Bucket
    Properties:
      BucketName: !Ref EnvironmentName  # Gets the parameter value
```

In our previous EventBridge example:

```yaml
Targets:
  - Arn: !Ref NotificationTopic    # Gets the SNS Topic ARN
    Id: "NotifyEC2StateChangeSNS"
```

The key differences between `!Ref` and `!GetAtt`:

1. `!Ref` gets a default value:

```yaml
TopicArn: !Ref MyTopic  # Gets Topic ARN
```

2. `!GetAtt` gets a specific attribute:

```yaml
TopicName: !GetAtt MyTopic.TopicName  # Gets Topic Name specifically
```


# EventBridge Rule Example

An example of  an EventBridge rule using CloudFormation template (reusable piece of infrastructure code) is:

```yaml
AWSTemplateFormatVersion: '2010-09-09'
Description: 'AWS EventBridge Rule Template'

Resources:
  MyEventRule:
    Type: 'AWS::Events::Rule'
    Properties:
      Name: 'my-event-rule'
      Description: 'A sample EventBridge rule'
      State: 'ENABLED'
      # Define the event pattern - this example looks for EC2 instance state changes
      EventPattern:
        source:
          - 'aws.ec2'
        detail-type:
          - 'EC2 Instance State-change Notification'
        detail:
          state:
            - 'running'
            - 'stopped'
      
      # Define the target(s) - this example sends to an SNS topic
      Targets:
        - Id: 'MySNSTarget'
          Arn: !Ref MySNSTopic # Reference to an SNS topic
          InputTransformer:
            InputPathsMap:
              instance: "$.detail.instance-id"
              state: "$.detail.state"
            InputTemplate: |
              "EC2 instance <instance> has changed state to <state>"

  # Sample SNS Topic that will receive the events
  MySNSTopic:
    Type: 'AWS::SNS::Topic'
    Properties:
      TopicName: 'my-event-notifications'

```

This CloudFormation template:

1. Creates a rule that monitors EC2 instance state changes
2. Sends notifications to an SNS topic when instances start or stop
3. Includes a custom message format using InputTransformer

# Macie (S3)

Uses machine learning and pattern recognition to discover sensitive data stored in Amazon S3:

* Uses AI to identify if S3 contains sensitive data, such as PII (Personally Identifiable Information), PHI (Protected Health Information), and financial data.
* Alerts you if you have unencrypted buckets.
* Alerts you about public buckets.
* Can alert about buckets shared with AWS accounts outside of those defined in your AWS Organizations.
* Ideal for compliance frameworks like HIPAA (Health Insurance Portability and Accountability Act) and GDPR (General Data Protection Regulation).
* Can send alerts to Amazon EventBridge and integrates with your event management system.
* Supports automated remediation actions using AWS services, such as AWS Step Functions

```mermaid
flowchart LR
    S3[("S3 Bucket\nData Source")] --> Macie[Amazon Macie]
    Macie -->|Findings| EventBridge[Amazon EventBridge]
    
    subgraph Filtering
        EventBridge -->|Rule Match| SNS[Amazon SNS Topic]
    end
    
    SNS -->|Email| Users([Email Users])
    SNS -->|Lambda| Lambda([Lambda Function])
    SNS -->|SQS| Queue([SQS Queue])
    SNS -->|HTTP| Webhook([Slack/Teams])
    
    style S3 fill:#FF9900
    style Macie fill:lightblue
    style EventBridge fill:#FF4F8B
    style SNS fill:#FF4F8B
    style Users fill:#3F8624
    style Lambda fill:#FF9900
    style Queue fill:#FF4F8B
    style Webhook fill:#3F8624
```

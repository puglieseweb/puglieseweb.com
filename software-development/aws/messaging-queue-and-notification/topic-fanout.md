

```mermeid
flowchart LR
    subgraph "Topic Pattern"
        Publisher1[Publisher] --> SNS1[SNS Topic:\nNotifications]
        SNS1 --> Sub1[Subscriber:\nMobile App]
        SNS1 --> Sub2[Subscriber:\nEmail]
        SNS1 --> Sub3[Subscriber:\nWebhook]
    end

    subgraph "Fanout Pattern"
        Publisher2[Publisher] --> SNS2[SNS Topic:\nOrders]
        SNS2 --> SQS1[SQS Queue:\nOrder Processing]
        SNS2 --> SQS2[SQS Queue:\nInventory Update]
        SNS2 --> SQS3[SQS Queue:\nAnalytics]
        SQS1 --> Lambda1[Lambda:\nProcess Order]
        SQS2 --> Lambda2[Lambda:\nUpdate Stock]
        SQS3 --> Firehose[Kinesis Firehose:\nData Lake]
    end

    style Publisher1 fill:#f96,stroke:#333
    style Publisher2 fill:#f96,stroke:#333
    style SNS1 fill:#ff9900,stroke:#333
    style SNS2 fill:#ff9900,stroke:#333
    style SQS1 fill:#ff9900,stroke:#333
    style SQS2 fill:#ff9900,stroke:#333
    style SQS3 fill:#ff9900,stroke:#333
    style Lambda1 fill:#6600ff,stroke:#333
    style Lambda2 fill:#6600ff,stroke:#333
    style Firehose fill:#6600ff,stroke:#333
    style Sub1 fill:#87ceeb,stroke:#333
    style Sub2 fill:#87ceeb,stroke:#333
    style Sub3 fill:#87ceeb,stroke:#333
```

# Marketplace Application

A company wants to build an online marketplace application on AWS as a set of loosely coupled microservices. For this application, when a customer submits a new order, two microservices should handle the event simultaneously. The Email microservice will send a confirmation email, and the OrderProcessing microservice will start the order delivery process. If a customer cancels an order, the OrderCancellation and Email microservices should handle the event simultaneously. A solutions architect wants to use Amazon Simple Queue Service (Amazon SQS) and Amazon Simple Notification Service (Amazon SNS) to design the messaging between the microservices. How should the solutions architect design the solution?

Create an SNS topic with SQS queues subscribed using message filtering.

Why this works best:

* SNS enables simultaneous message delivery to multiple subscribers (fan-out)
* SQS queues ensure reliable message processing for each microservice
* Message filtering lets each queue receive only relevant events



```json
// SNS Filter Policy for OrderProcessing Queue
{
  "filter-policy": {
    "type": ["com.marketplace.order.created"]
  }
}

// SNS Filter Policy for OrderCancellation Queue
{
  "filter-policy": {
    "type": ["com.marketplace.order.cancelled"]
  }
}

// SNS Filter Policy for Email Queue
{
  "filter-policy": {
    "type": [
      "com.marketplace.order.created",
      "com.marketplace.order.cancelled"
    ]
  }
}

// Example message format
{
  "specversion": "1.0",
  "type": "com.marketplace.order.created",
  "source": "/marketplace/orders",
  "id": "order-123-456",
  "time": "2024-03-10T10:30:00Z",
  "datacontenttype": "application/json",
  "data": {
    "orderId": "123456",
    "customerId": "789",
    "items": ["item1", "item2"],
    "total": 99.99
  }
}
```

The binding happens during queue subscription to the SNS topic. Here's how:

Using AWS Console:

1. Go to SQS Queue -> Subscribe to SNS topic
2. Add subscription filter policy in JSON format

Using AWS CLI:

```bash
aws sns subscribe \
  --topic-arn arn:aws:sns:region:account-id:topic-name \
  --protocol sqs \
  --notification-endpoint arn:aws:sqs:region:account-id:queue-name \
  --attributes '{"FilterPolicy":"{\"type\":[\"com.marketplace.order.created\"]}"}'
```

Using AWS CDK:

```typescript
const queue = new sqs.Queue(this, 'OrderProcessingQueue');
const topic = new sns.Topic(this, 'OrdersTopic');

topic.addSubscription(new subs.SqsSubscription(queue, {
  filterPolicy: {
    type: sns.SubscriptionFilter.stringFilter({
      allowlist: ['com.marketplace.order.created']
    })
  }
}));
```

* Loose coupling maintained between services



```mermaid
flowchart TD
    A[Client Application] --> B[SNS Topic<br>Throughput: 300K msg/s per API action]
    B -->|New Order Events| C[SQS Queue - OrderProcessing<br>Standard: 3K msg/s default<br>FIFO: 300 msg/s per API action]
    B -->|All Events| D[SQS Queue - Email<br>Standard: 3K msg/s default<br>FIFO: 300 msg/s per API action]
    B -->|Cancel Events| E[SQS Queue - OrderCancellation<br>Standard: 3K msg/s default<br>FIFO: 300 msg/s per API action]
    
    C --> F[OrderProcessing Service]
    D --> G[Email Service]
    E --> H[OrderCancellation Service]
    
    style B fill:#f96,stroke:#333
    style C fill:#9cf,stroke:#333
    style D fill:#9cf,stroke:#333
    style E fill:#9cf,stroke:#333
```



Architecture:

1. SNS topic receives all order events
2. Three SQS queues subscribe with filters:
   * Email queue: receives all events
   * OrderProcessing: new orders only
   * OrderCancellation: cancellations only



## Potential Improvements

There are several potential improvements:

1. Event Router Pattern:

```
API Gateway → Lambda (Router) → Multiple SNS Topics → SQS Queues
```

Benefits: Better event filtering, reduced SNS costs

2. Direct Integration:

```
API Gateway → SQS → Lambda
```

Benefits: Simpler, cost-effective for single consumers

3. EventBridge Pattern:

```
EventBridge → SNS/SQS/Lambda
```



```mermaid
flowchart TD
    A[Client Application] --> B[Amazon API Gateway]
    B --> C[Amazon EventBridge<br>Default: 18,750 events/s<br>Custom Bus: 2,400 events/s]
    
    C -->|"rule: type='order.created'"| D[SQS Queue - OrderProcessing<br>Standard: 3K msg/s]
    C -->|"rule: type IN ['order.created','order.cancelled']"| E[SQS Queue - Email<br>Standard: 3K msg/s]
    C -->|"rule: type='order.cancelled'"| F[SQS Queue - OrderCancellation<br>Standard: 3K msg/s]
    
    C -.->|Archive| G[S3 Bucket<br>Event Archive]
    C -.->|Failed Events| H[DLQ<br>Dead Letter Queue]
    
    D --> I[OrderProcessing Service]
    E --> J[Email Service]
    F --> K[OrderCancellation Service]
    
    style C fill:#f96,stroke:#333
    style D fill:#9cf,stroke:#333
    style E fill:#9cf,stroke:#333
    style F fill:#9cf,stroke:#333
    style G fill:#9cf,stroke:#333
    style H fill:#ff9999,stroke:#333
```

Benefits:

* Better event filtering
* Content-based routing
* Schema validation
* Dead-letter queues
* Replay capabilities

EventBridge would be ideal here because:

* Native CloudEvents support
* Better filtering capabilities
* Built-in error handling
* Event archiving and replay
* Direct Lambda integration

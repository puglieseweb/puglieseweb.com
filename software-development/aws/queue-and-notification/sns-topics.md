# SNS (Topics)

Amazon SNS (Simple Notification Service) is a fully managed pub/sub messaging service where publishers send messages to topics and these messages are automatically delivered to all subscribed endpoints/consumers.

_Key Features:_

* Message size:
  * Standard: Up to 256 KB of text in any format
  * Extended: Up to 2 GB using **Amazon SNS Extended Client Library** (stores payload in S3)
* Encryption:
  * In-transit encryption by default
  * At-rest encryption using KMS (optional)
* Access Control:
  * IAM policies
  * SNS Access policies (define who can publish/subscribe)

_Supported Subscribers:_

* Application integration:
  * Amazon SQS
  * AWS Lambda
  * Amazon Kinesis Data Firehose
* Messaging:
  * HTTP/HTTPS endpoints
  * Email
  * SMS
  * Mobile push notifications
  * Platform application endpoints

_Message Filtering:_

* Subscription filter policies allow filtering messages before delivery
* Reduces unnecessary message processing

_Message Reliability:_

* Dead Letter Queues (DLQ) support for failed message delivery
* Custom retry policies supported for HTTP/HTTPS endpoints
* Message durability through multiple Availability Zone replication

_Messaging Types:_

* Standard Topics:
  * Best-effort ordering
  * At-least-once delivery
* FIFO Topics:
  * Strict message ordering
  * Exactly-once message delivery
  * Must have SQS FIFO queues as subscribers

_SNS Fanout Pattern:_

* Pushes messages to multiple subscribing endpoints simultaneously
* Enables parallel, asynchronous processing
* Provides complete decoupling between publishers and subscribers
* Common use cases:
  * Application alerts
  * Monitoring systems
  * Workflow processing
  * Cross-account/cross-region message delivery

_Best Practices:_

* Use filter policies to reduce unnecessary message delivery
* Implement DLQ for handling failed deliveries
* Monitor delivery metrics using CloudWatch
* Use FIFO topics when message ordering is critical





<figure><img src="../../../.gitbook/assets/AWS - SNS (1).svg" alt=""><figcaption></figcaption></figure>

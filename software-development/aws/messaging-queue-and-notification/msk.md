# MSK

Managed Streaming for Apache Kafka (MSK).

KMS provides Control Plane operations. Creates, updates and deletes clusters as required.

Leverage Kafka data-plane operation for producing and consuming streaming data.

Open-source version of Apache Kafka allows support for existing apps, tools, and plugins.

### Security and Logging

1. Integrates with Amazon KMS for SSE requirements&#x20;
2. Encryption at reast by default.
3. TLS 1.2 for encryption in transit between brokers in clusters
4. Deliver broker logs to Amazon CloudWatch, Amazon S3, adn Amazon Kinesis Data Firehose
5. Metric are gathered and sent to CloudWatch&#x20;
6. All Amazon MSK API calls are logged to AWS CloudTrail&#x20;



| MSK Serveless                                                                                                 | MSK Connect                                               |
| ------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------- |
| I a cluster type within Amazon MSK offering serverless cluster management. Automatic provisioning and scaling | Allows to stream data to and from Apache Kafka clusters.  |
| Fully Compatible with Apache Kafka                                                                            |                                                           |
|                                                                                                               |                                                           |

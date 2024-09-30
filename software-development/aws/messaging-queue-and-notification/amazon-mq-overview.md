# Amazon MQ Overview

* Message broker service allowing easiser migration of existing applications to the AWS Cloud&#x20;
* Leverages multiple programming languages, operation systems, and messaging protocols (AMQP 0-9-1, AMQP 1.0, MQTT, OpenWire, and STOMP)
* Currently supports both Apache ActiveMQ or RabbitMQ engine types
* Allows to leverage existing apps without managing and maintaining your own system&#x20;

Amazon MQ offers highly available architectures to minimize downtime during maintainance:

* Amazon MQ for Active MQ has active/standby deployments where one instance remains available at all times. Configure network of brokers with separate maintanance windows.
* Amazon MQ for RabbitMQ has cluster deployments. These are logical grouping of 3 broker nodes across multiple AZs sitting behind a Network Load Balancer.&#x20;





## SNS with SQS vs Amazon MQ

Both offers architectures with topics and queues, allowing for one-to-one or one-to-many messaging designs.&#x20;

| SNS with SQS                       | Amazon MQ                                                    |
| ---------------------------------- | ------------------------------------------------------------ |
| for new application                | Good if you are migrating existing applications              |
| simpler to use and highly scalable | Requires private networking like VPC, Direct Connect, of VPN |
| publicly accessible by default     |                                                              |

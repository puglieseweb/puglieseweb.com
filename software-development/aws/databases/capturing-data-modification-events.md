# Capturing data modification events

## RDS Data Modification Events

RDS event notifications only trigger on database instance-level events (maintenance, backup, configuration changes etc.) and NOT on individual record changes or SQL operations like deleting a car listing.

To properly capture RDS Data Modification Events we would need one of these approaches:

1. Application-Level Trigger:
   * The web application that handles the sale would need to trigger the SNS topic directly after updating the RDS database
   * This ensures the event is fired specifically when a car is sold
2. Database-Level Trigger:
   * Use a database trigger in RDS that writes to a separate audit/event table
   * Use AWS DMS (Database Migration Service) with CDC (Change Data Capture) to track changes to this table
   * DMS could then trigger a Lambda function that publishes to SNS
3. Binlog-based CDC:
   * Use tools like AWS DMS or other CDC solutions to monitor the database's binlog
   * When changes are detected for car sales, trigger the notification process



## DynamoDB Data Modification Events

DynamoDB Streams can capture data modification events at the item level, which is different from RDS. Here are the key points about DynamoDB Streams:

1. Event Types Captured:

* INSERT - when a new item is added
* MODIFY - when an item is updated
* DELETE - when an item is removed
* Both old and new images of the modified item can be captured

2. Stream Records:

* Are maintained for 24 hours
* Can be processed by AWS Lambda functions (directly integrated)
* Can be consumed by DynamoDB Streams Kinesis Adapter
* Are delivered in near real-time

3. Common Use Cases:

* Cross-region replication
* Real-time data analytics
* Event-driven applications
* Audit trails
* Notifications on data changes

This makes DynamoDB Streams much more suitable for scenarios where you need to react to individual record changes, unlike RDS which only provides instance-level event notifications.

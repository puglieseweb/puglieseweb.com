# DLQ

Dead-letter queue (DLQ)

* These are SQS queues.
* Works with both SQS and SNS!
* These are targets for messages that cannot be processed successfully.
* DLQs used with FIFO SQS queues must ALSO be FIFO queues.
* **Redrive capability** allows to move the messages back into the source queue.  For example, this is useful in case after the investigation we think there was an intermittent issue.

Benefits:&#x20;

* Configure alarms based on message availability conuts.
* Quickly identiry which logs to investigate for exceptions.&#x20;
* Analyze the SQS message contents for any errors.&#x20;
* Troubleshoot consumer permissions.&#x20;




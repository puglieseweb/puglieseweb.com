# SQS

SQS is poll-based messaging system.

SQS is not a permante storage solution.&#x20;

SQS Settings are:

1. Delivery Delay: Default is 0; can be set up to 15 minutes.
2. Message Size: can be up to 256 KB of text in any format
3. Encryption: messages are encrypted in transit by default, and can be encrypted at-rest if the option is manually enabled.&#x20;
4. Message Retentions: Default is 4 days; can be set between 1 minute and 14 days.
5. Long vs Short pooling. Long polling isn't the default, but it should be.
6. Queue Depth. This can be a trigger for EC2 autoscaling via CloudWatch.
7. Visibility timeout. The message stays in the queue for a configurable period of time after it is consumed, but other clients cannot see it. If the backed instance fails to acknowledge the message withing the visibility timeout the message will be restored in the queue. &#x20;


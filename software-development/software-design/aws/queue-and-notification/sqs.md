# SQS

## Message Ordering&#x20;

Standard SQS Queue:

* Best-effort ordering
* Duplicate messages
* Nearly unlimited transactions per second

FIFO

* Guaranteed ordering
* No message duplication&#x20;
* 300 transaction per second
* Using Batching we can achieve up to 3,000 messages per second, per API call.

FIFO High throughput:

* Process up to 9,000 transactions per second, per API action without batching&#x20;
* Up to 90,000 messages per second using batching APIs


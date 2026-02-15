# SQS (Message Queue)

SQS is a Message queue that queues messages for reliable processing.

Amazon SQS is the ideal solution to:

* Handles drastic message volume spikes up to 100k/sec
* Decouples message producers from consumers
* Automatically scales to match demand
* Allows multiple consumers to process messages independently
* Maintains message durability and availability



Multiple consumers can read from the same SQS queue, but each message will only be processed by one consumer. This is called "competing consumers" pattern:

Key points:

* Messages are distributed among consumers
* Each message is processed by only one consumer
* Message remains invisible to other consumers during processing
* Good for workload distribution but not for broadcasting
* For broadcasting to multiple consumers, use SNS instead

If you need all consumers to process every message, use SNS with multiple SQS queues as subscribers.

## Amazon SQS Queue Types and Message Ordering

### Queue Types and Their Characteristics

#### Standard Queue

* Best-effort ordering
* May have duplicate messages
* Nearly unlimited transactions per second
* Suitable for high-throughput scenarios where exact ordering isn't critical

#### FIFO (First-In-First-Out) Queue

* Guaranteed ordering of messages
* Exactly-once message processing (no duplicates)
* Base throughput:
  * 300 transactions per second
  * Up to 3,000 messages per second with batching (per API call)

#### FIFO High Throughput Queue

* Up to 9,000 transactions per second per API action without batching
* Up to 90,000 messages per second using batching APIs

### General SQS Characteristics

* Poll-based messaging system
* Not designed for permanent storage

### Queue Settings

#### Message Configuration

1. **Delivery Delay**
   * Default: 0 seconds
   * Maximum: 15 minutes
2. **Message Size**
   * Maximum: 256 KB
   * Supports any text format
3. **Encryption**
   * In-transit encryption: Enabled by default
   * At-rest encryption: Optional, must be manually enabled
4. **Message Retention**
   * Default: 4 days
   * Configurable range: 1 minute to 14 days

#### Queue Operations

5. **Polling Options**
   * Short polling: Default option
   * Long polling: Recommended option
   * Long polling reduces empty responses and API calls
6. **Queue Depth**
   * Monitored via CloudWatch
   * Can trigger EC2 auto-scaling
7. **Visibility Timeout**
   * Messages become temporarily invisible after being consumed
   * Duration is configurable
   * If not acknowledged within timeout, message returns to queue
   * Prevents multiple consumers from processing the same message


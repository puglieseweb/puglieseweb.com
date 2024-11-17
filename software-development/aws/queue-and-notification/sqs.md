# SQS

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


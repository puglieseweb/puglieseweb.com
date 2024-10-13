# DynamoDB Consistency Models and ACID Properties

DynamoDB offers two read consistency models:

1. **Eventually Consistent Reads**:
   * Default read model
   * Provides best read performance
   * May not reflect the results of a recently completed write
   * Typically consistent within a second
2. **Strongly Consistent Reads**:
   * Returns a result that reflects all writes that received a successful response prior to the read
   * Might have higher latency
   * May be less available in case of network delays or outages
   * Consumes more throughput capacity than eventually consistent reads

### ACID Properties in DynamoDB

ACID stands for Atomicity, Consistency, Isolation, and Durability. DynamoDB supports ACID transactions, but with some nuances:

1. **Atomicity**:
   * Supported through TransactWriteItems and TransactGetItems operations
   * All specified actions succeed, or none do
2. **Consistency**:
   * Immediate consistency within a single item
   * Cross-item consistency achieved through transactions
3. **Isolation**:
   * Transactions are serializable
   * Isolated from other operations
4. **Durability**:
   * Data is synchronously replicated across multiple facilities in an AWS Region

### Key Points

1. **Read Consistency Choice**:
   * Eventually consistent reads are the default
   * Strongly consistent reads must be explicitly requested
   * Strongly consistent reads use more RCUs
2. **Performance vs. Consistency Trade-off**:
   * Eventually consistent reads offer better performance and availability
   * Strongly consistent reads offer up-to-date data at the cost of higher latency
3. **Global Tables and Consistency**:
   * Multi-region replication uses eventually consistent reads
   * Strongly consistent reads are only available within the same region
4. **ACID Transactions**:
   * Supported across multiple items and tables
   * Can span up to 25 items or 4 MB of data
5. **Use Cases**:
   * Eventually consistent: High-throughput reads where slight inconsistency is acceptable
   * Strongly consistent: Financial transactions, inventory systems where immediate consistency is crucial
6. **Capacity Consumption**:
   * Strongly consistent reads consume twice the RCUs of eventually consistent reads

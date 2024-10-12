# DynamoDB capacity models

&#x20;DynamoDB offers two capacity modes: Provisioned and On-Demand.

### Provisioned Capacity

1. **Use Case**:
   * For predictable workloads
   * When you can forecast read and write capacity requirements
2. **How it works**:
   * You specify Read Capacity Units (RCUs) and Write Capacity Units (WCUs)
   * 1 RCU = 1 strongly consistent read/sec for an item up to 4 KB
   * 1 WCU = 1 write/sec for an item up to 1 KB
3. **Scaling**:
   * Can use Auto Scaling to automatically adjust provisioned capacity
   * Set upper and lower bounds for scaling
4. **Cost**:
   * Most cost-effective for predictable workloads
   * Pay for provisioned capacity whether you use it or not
5. **Performance**:
   * Consistent performance if capacity is set correctly
   * Can lead to throttling if capacity is set too low
6. **Effort**:
   * Requires monitoring and adjusting capacity based on usage patterns
   * Need to review past usage to set appropriate scaling bounds

### On-Demand Capacity

1. **Use Case**:
   * For unpredictable workloads
   * New applications with unknown traffic patterns
   * Applications with sporadic, short-lived spikes in traffic
2. **How it works**:
   * Automatically scales up and down based on actual traffic
   * No need to specify RCUs and WCUs
3. **Scaling**:
   * Instantaneous and unlimited scaling
   * Can handle sudden and large spikes in traffic
4. **Cost**:
   * Pay per request pricing
   * Generally more expensive than well-optimized Provisioned capacity
   * No charges for read and write capacity that you don't use
5. **Performance**:
   * Consistent performance regardless of load
   * No throttling due to capacity misconfigurations
6. **Effort**:
   * Simple to use - just select On-Demand mode
   * No capacity planning required

### Key Points

1. **Switching between modes**:
   * Can switch between Provisioned and On-Demand **once per 24 hours**
2. **Global Tables**:
   * Both modes support **DynamoDB Global Tables for multi-region replication**
3. **Backup and Restore**:
   * Both modes support on-demand backup and restore
4. **Read Consistency**:
   * **Both modes support eventually consistent and strongly consistent reads**
5. **DAX (DynamoDB Accelerator)**:
   * Compatible with both capacity modes
6. **Use case identification**:
   * Be prepared to recommend the appropriate capacity mode based on given scenarios


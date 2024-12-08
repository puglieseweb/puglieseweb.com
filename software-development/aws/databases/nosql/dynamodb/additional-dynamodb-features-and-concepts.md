# Additional DynamoDB Features and Concepts

##

### Data Model and Keys

* **Partition Key (Hash Key)**: Required for all items, must be unique if no sort key
* **Sort Key (Range Key)**: Optional, enables composite keys and more complex queries
* **Composite Key**: Combination of partition key and sort key
* **Item Size**: Maximum size of 400 KB including all attributes

### Secondary Indexes in Detail

**Local Secondary Index (LSI)**:

* Must be created at table creation time
* Same partition key as base table but different sort key
* Limited to 5 LSIs per table
* Shares provisioned throughput with main table

**Global Secondary Index (GSI)**:

* Can be created/deleted anytime
* Different partition key and/or sort key from base table
* Limited to 20 GSIs per table
* Has its own provisioned throughput



### Data Types

* **Scalar Types**: Number, String, Binary, Boolean, Null
* **Document Types**: List, Map
* **Set Types**: Number Set, String Set, Binary Set

### Time To Live (TTL)

* Automatically delete items after an expiry timestamp
* Useful for session data, temporary data, logs
* No additional cost for TTL deletions
* TTL deletions can be captured in DynamoDB Streams

### Error Handling

* **ProvisionedThroughputExceededException**: When requests exceed provisioned capacity
* **ThrottlingException**: When too many control plane operations
* **ResourceNotFoundException**: When accessing nonexistent table
* Exponential backoff for retry operations

### Monitoring and Metrics

* **CloudWatch Metrics**:
  * ConsumedReadCapacityUnits
  * ConsumedWriteCapacityUnits
  * ReadThrottleEvents
  * WriteThrottleEvents
  * ThrottledRequests
  * SystemErrors

### Batch Operations

* **BatchGetItem**: Retrieve up to 100 items from multiple tables
* **BatchWriteItem**: Put or delete up to 25 items in multiple tables
* Automatic retry for unprocessed items

### Query and Scan Operations

**Query**:

* More efficient than Scan
* Requires partition key value
* Optional sort key conditions
* Can use any LSI or GSI
* Forward and backward scanning
* Limit parameter to control items per page

**Scan**:

* Examines every item in the table
* Optional filter expressions
* Parallel scan capability
* Higher resource usage
* Pagination support

### Best Practices

* Use appropriate partition keys to distribute data evenly
* Implement retry logic with exponential backoff
* Use batch operations when possible
* Choose between provisioned vs on-demand based on workload predictability
* Use sparse indexes to minimize storage and write costs
* Compress large attribute values
* Use projections in secondary indexes to minimize data transfer

### Limits and Quotas

* Maximum number of tables per region: 2500
* Maximum number of concurrent table operations: 500
* Maximum partition key length: 2048 bytes
* Maximum sort key length: 1024 bytes
* Maximum number of projected attributes in LSI: 20
* Maximum number of attributes per item: No fixed limit

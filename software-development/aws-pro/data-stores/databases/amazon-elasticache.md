# Amazon ElastiCache

<figure><img src="../../../../.gitbook/assets/image (29).png" alt=""><figcaption></figcaption></figure>

### Core Characteristics

* In-memory data store service
* Faster than disk-based databases like DynamoDB
* Push-button scalability for:
  * Memory
  * Writes
  * Reads
* Pricing based on node size and usage hours

### Engine Types

#### Memcached

* No-frills, robust workhorse
* Simple architecture
* Basic caching solution
* Non-persistent by design

#### Redis

* Feature-rich option
* Optional backup and persistence capabilities
* More complex architecture
* Advanced features and data structures
* Not meant to replace persistent databases (RDS, DynamoDB)

<figure><img src="../../../../.gitbook/assets/image (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Common Use Cases

1. Web Session Storage
   * Centralized session management for web server fleets
   * Alternative to multicast in VPC environments
   * Prevents session loss during server failures
2. Database Enhancement
   * Offload work from MySQL instances
   * Reduce database engine size requirements
   * Cost optimization potential
3. Real-time Applications
   * Gaming leaderboards
   * Mobile applications
   * Factory floor data streaming
   * Real-time dashboard support

### Key Considerations

* Primary purpose is caching, not persistent storage
* Extremely fast due to in-memory storage
* Trade-off between speed and persistence
* Should complement, not replace, persistent databases

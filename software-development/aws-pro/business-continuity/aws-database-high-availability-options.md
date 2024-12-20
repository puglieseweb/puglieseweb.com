# AWS Database High Availability Options

<figure><img src="../../../.gitbook/assets/image (33) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (49) (1).png" alt=""><figcaption></figcaption></figure>

###

### Operational Efficiency Spectrum



#### High Operational Efficiency (Less Control)

* AWS manages elasticity and HA
* Limited control over engine/architecture
* Automated management
* Simplified operations

#### Low Operational Efficiency (Maximum Control)

* Complete control over all aspects
* Manual OS updates and patches
* Custom configuration options
* Higher maintenance overhead

### Database Options Overview

#### DynamoDB

<figure><img src="../../../.gitbook/assets/image (34) (1).png" alt=""><figcaption></figcaption></figure>

**Characteristics**

* Highest operational efficiency
* NoSQL-only solution
* Built-in high availability
* Default regional distribution

**High Availability Features**

* Data distribution across partitions
* Three-AZ spread per region
* Global tables support
* Automatic failover handling

#### Amazon Aurora

<figure><img src="../../../.gitbook/assets/image (42) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (43) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (44) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (45) (1).png" alt=""><figcaption></figcaption></figure>









**Characteristics**

* Best HA option for relational data
* Minimal operational overhead
* Multi-AZ/Multi-region capable
* Read replica support

**High Availability Features**

* Synchronous data replication
* Cross-AZ distribution
* Global database option
* Automatic failover
* Up to five secondary regions

**Implementation Details**

* Main instance with multiple read replicas
* Cross-AZ data copies
* Automatic promotion of read replicas
* Storage-level replication for global databases

#### Amazon RDS

<figure><img src="../../../.gitbook/assets/image (36) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (35) (1).png" alt=""><figcaption></figcaption></figure>





**Characteristics**

* Good HA capabilities
* More complex multi-region setup
* Standby instances don't serve traffic
* Synchronous replication

**High Availability Options**

1. **Multi-AZ Deployment**
   * Synchronous replication
   * Automatic failover
   * Standby instances for failover only
2. **Regional Read Replicas**
   * Warm standby option
   * Manual promotion required
   * Cross-region capability

<figure><img src="../../../.gitbook/assets/image (38) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (39) (1).png" alt=""><figcaption></figcaption></figure>

1. **Backup and Recovery**
   * S3 snapshot storage
   * Higher RTO tolerance
   * Potential data loss depending on frequency

<figure><img src="../../../.gitbook/assets/image (40) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (41) (1).png" alt=""><figcaption></figcaption></figure>

#### Amazon Redshift

<figure><img src="../../../.gitbook/assets/image (46) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (47) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (48) (1).png" alt=""><figcaption></figcaption></figure>

**Current HA Options**

* Multi-node clusters (primary method)
* RA3 instances (preview) for multi-AZ
* S3 snapshot restoration for single-node

**RA3 Instance Features**

* Redshift Managed Storage (RMS)
* S3-based storage layer
* Cross-AZ compute provisioning
* Automatic failover capability

### Implementation Considerations

#### DynamoDB Implementation

* Enable global tables for multi-region
* Configure read/write capacity
* Monitor partition distribution
* Plan capacity requirements

#### Aurora Implementation

* Deploy across multiple AZs
* Configure read replicas
* Enable global databases if needed
* Set up automatic failover

#### RDS Implementation

* Choose appropriate HA strategy
* Configure backup schedules
* Plan for regional redundancy
* Set up monitoring and alerts

#### Redshift Implementation

* Select appropriate instance type
* Configure multi-node clusters
* Plan backup strategy
* Monitor cluster health

### Best Practices

#### Selection Criteria

1. **Data Type Requirements**
   * NoSQL: Consider DynamoDB
   * Relational: Aurora or RDS
   * Data Warehouse: Redshift
2. **Availability Requirements**
   * Regional vs Global
   * RTO/RPO requirements
   * Failover needs
3. **Operational Considerations**
   * Management overhead
   * Cost implications
   * Maintenance requirements

#### Monitoring and Maintenance

* Regular health checks
* Performance monitoring
* Backup verification
* Failover testing

### Exam Considerations

* DynamoDB is optimized for HA in NoSQL scenarios
* Aurora provides best relational database HA features
* RDS offers multiple HA options with different trade-offs
* Redshift achieves HA primarily through multi-node clusters
* Consider operational efficiency vs control requirements

# AWS Disaster Recovery Architectures

### Overview

AWS provides four main disaster recovery architectures, each offering different trade-offs between recovery speed and cost. These architectures range from simple backup solutions to fully active multi-site deployments.

### 1. Backup and Restore

<figure><img src="../../../.gitbook/assets/image (20).png" alt=""><figcaption></figcaption></figure>

#### Description

The most basic DR strategy, focusing on data backup to AWS.

#### Characteristics

* Minimal configuration required
* Low risk implementation
* Most cost-effective option
* Longest recovery time

#### Implementation Examples

* AWS Snowball for data transfer
* Virtual Tape Library
* AWS Storage Gateway
* S3 for backup storage

#### Limitations

* Limited flexibility
* Functions primarily as offsite backup
* Longest recovery time of all options
* Manual recovery process required

### 2. Pilot Light

<figure><img src="../../../.gitbook/assets/image (21).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (24).png" alt=""><figcaption></figcaption></figure>

#### Description

Maintains minimal AWS footprint in standby mode, similar to a pilot light on a gas heater.

#### Characteristics

* Cost-effective hot site alternative
* Core services maintained in ready state
* Requires some manual intervention
* Minutes to hours for full recovery

#### Implementation Components

* Small RDS instance for database replication
* Stopped EC2 instances for web/app servers
* Regular AMI updates required
* Basic infrastructure maintained

#### Recovery Process

1. Start stopped EC2 instances
2. Scale up RDS instance if needed
3. Redirect traffic to AWS environment
4. Validate application functionality

#### Considerations

* AMI maintenance crucial
* Regular testing required
* Database synchronization needed
* Cost-effective middle ground

### 3. Warm Standby

<figure><img src="../../../.gitbook/assets/image (23).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (22).png" alt=""><figcaption></figcaption></figure>

#### Description

Maintains a scaled-down but fully functional environment in AWS.

#### Characteristics

* More responsive than pilot light
* Services already running
* Can serve as staging environment
* Reduced recovery time

#### Implementation Components

* Active load balancer configuration
* Running web and application servers
* Replicated database infrastructure
* Route 53 for traffic management

#### Recovery Process

1. Scale up existing resources

* Increase EC2 instance sizes
* Add more instances as needed
* Upgrade database capacity

2. Update DNS routing

* Redirect traffic through Route 53
* Scale resources to match demand

#### Advantages

* Faster recovery than pilot light
* Environment already validated
* Can serve dual purpose (staging)
* Automated scaling possible

### 4. Multi-Site Active/Active

<figure><img src="../../../.gitbook/assets/image (25).png" alt=""><figcaption></figcaption></figure>

#### Description

Full production environment maintained in AWS, running alongside primary site.

#### Characteristics

* Fastest recovery time
* Minimal to no manual intervention
* Seconds or less to failover
* Most expensive option

#### Implementation Components

* Fully active load balancers
* Production-scaled EC2 instances
* Active database replication
* Route 53 health checks

#### Recovery Process

1. Automatic failover via Route 53
2. DNS propagation (based on TTL)
3. Traffic automatically redirected
4. No manual intervention required

#### Considerations

* Highest cost option
* Perceived resource waste
* DNS TTL impact on recovery
* Complex synchronization requirements

### Cost vs Recovery Time Trade-offs

#### Cost Spectrum (Low to High)

1. Backup and Restore
2. Pilot Light
3. Warm Standby
4. Multi-Site

#### Recovery Time Spectrum (Slow to Fast)

1. Backup and Restore (Hours/Days)
2. Pilot Light (Hours)
3. Warm Standby (Minutes)
4. Multi-Site (Seconds)

### Best Practices

#### Architecture Selection

* Align with business RTO/RPO
* Consider budget constraints
* Account for technical capabilities
* Plan for growth

#### Implementation

* Regular testing required
* Document procedures
* Automate where possible
* Monitor and maintain synchronization

#### Maintenance

* Keep AMIs current
* Test failover procedures
* Update documentation
* Train staff on procedures

# Services and Strategies

### Modern Migration Paradigm

<figure><img src="../../../../../.gitbook/assets/image (15).png" alt=""><figcaption></figcaption></figure>

#### Shift from Server to Workload Migration

* Focus on component-based migration
* Break applications into individual workloads
* Target appropriate AWS services for each component



<figure><img src="../../../../../.gitbook/assets/image (8).png" alt=""><figcaption></figcaption></figure>

#### Common Migration Patterns

1. **Containerized Applications**
   * Migration to AWS Batch
   * Implementation on AWS Fargate
2. **Tomcat Applications**
   * Migration to Elastic Beanstalk
   * Reduced operational overhead
   * Automatic scaling capabilities
3. **Microservices**
   * Migration to event-driven architecture
   * Serverless implementation
   * Message handling optimization

### Core Migration Services

#### Migration Hub

* Centralizes migration planning and tracking
* Integrates with Application Discovery Service
* Provides instance sizing recommendations
* Offers migration strategy advice
* Features intuitive dashboard for progress tracking

<figure><img src="../../../../../.gitbook/assets/image (9).png" alt=""><figcaption></figcaption></figure>

#### Application Discovery Service

<figure><img src="../../../../../.gitbook/assets/image (11).png" alt=""><figcaption></figcaption></figure>



**Key Features**

* Server-application mapping
* Traffic pattern analysis
* Encrypted data transmission
* Visual application mapping

**Implementation**

1. Agent installation on on-premises servers
2. Production traffic analysis
3. Server relationship mapping
4. Migration strategy development

#### Application Migration Service (MGN)

<figure><img src="../../../../../.gitbook/assets/image (10).png" alt=""><figcaption></figcaption></figure>

Use case 1:

<figure><img src="../../../../../.gitbook/assets/image (12).png" alt=""><figcaption></figcaption></figure>

Use case 2:

<figure><img src="../../../../../.gitbook/assets/image (13).png" alt=""><figcaption></figcaption></figure>



**Capabilities**

* Windows and Linux server migration
* Cross-cloud provider support
* Minimal interruption cutover
* Free service (pay only for EC2 instances)

**Implementation Process**

1. MGN agent installation
2. Continuous block storage replication
3. Test environment staging
4. Production cutover
5. Traffic redirection



### AWS to Data Center Options

<figure><img src="../../../../../.gitbook/assets/image (14).png" alt=""><figcaption></figcaption></figure>

#### EKS/ECS Anywhere

* On-premises container orchestration
* AWS-managed service benefits
* Bare metal implementation

#### AWS Outposts

* Physical AWS infrastructure
* Supports multiple AWS services:
  * EC2
  * S3
  * RDS

#### Snowball Edge

* Portable computing capability
* Supports EKS and EC2
* Offline data processing
* Data import/export functionality

### Migration Strategy Selection

#### Optimization Factors

1. **Operational Efficiency**
   * Modernization focus
   * Managed services utilization
   * Automatic scaling implementation
2. **Speed to Cloud**
   * Lift-and-shift approach
   * Migration Hub utilization
   * Rapid deployment focus
3. **Latency Requirements**
   * On-premises AWS services
   * Limited internet connectivity solutions
   * Hybrid architecture implementation

### Best Practices

#### Planning Phase

1. Define optimization priorities
2. Assess application components
3. Map dependencies
4. Choose appropriate migration tools

#### Implementation Phase

1. Install necessary agents
2. Configure monitoring
3. Test migrations
4. Plan cutover strategies

#### Post-Migration

1. Monitor performance
2. Optimize resources
3. Document configurations
4. Implement backup strategies

### Recommendations

1. Focus on workload-specific solutions
2. Utilize AWS managed services when possible
3. Consider hybrid solutions when appropriate
4. Plan for minimal disruption
5. Implement proper testing
6. Document migration processes

This document provides a foundation for understanding and implementing AWS migration strategies. Regular updates based on service enhancements and organizational needs are recommended.

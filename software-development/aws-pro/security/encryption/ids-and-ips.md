# IDS and IPS

### Intrusion Detection Systems (IDS)

#### Characteristics

* Passive monitoring system
* Focuses on network and system surveillance
* Reactive in nature
* Vulnerability scanning capabilities

#### Key Functions

* Monitors for suspicious activities
* Detects authentication failures
* Issues alerts for potential security incidents
* System vulnerability assessment

### Intrusion Prevention Systems (IPS)

#### Characteristics

* Active monitoring and response system
* Dynamic traffic analysis
* Signature-based threat detection
* Real-time intervention capabilities

#### Key Features

* Dynamic threat response
* Automated IP blacklisting
* Deep system monitoring through agents
* Proactive threat mitigation

### System Architecture Components

<figure><img src="../../../../.gitbook/assets/image (16) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Agent Deployment

* Local system installation
* Deep access monitoring
* System-level visibility
* Real-time data collection

#### Log Collection Mechanisms

* CloudWatch integration
* S3 storage capability
* Third-party SIEM integration (e.g., Splunk)
* Security Information and Event Management (SIEM) functionality

### AWS Logging Services Comparison

<figure><img src="../../../../.gitbook/assets/image (17) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### CloudWatch

* **Primary Purpose**: Service event logging
* **Scope**: Comprehensive AWS service monitoring
* **Focus**: Operational monitoring
* **Features**:
  * Multi-account logging
  * Indefinite log storage
  * 14-day alarm retention
  * High-level monitoring capabilities

#### CloudTrail

* **Primary Purpose**: API activity tracking
* **Scope**: AWS service API interactions
* **Focus**: Activity auditing
* **Features**:
  * Granular activity logging
  * Multi-account support
  * Long-term storage via S3
  * Integration with CloudWatch for alarming

### Implementation Best Practices

#### Log Aggregation

1. Establish dedicated logging account
2. Implement restricted access controls
3. Enable multi-account logging
4. Maintain log integrity

#### Alarm Configuration

1. Set up CloudWatch alarms for critical events
2. Configure CloudTrail integration with CloudWatch
3. Establish notification channels (SNS)
4. Implement response procedures

#### Security Architecture

1. Position IDS/IPS within appropriate subnets
2. Deploy monitoring agents across infrastructure
3. Configure automated response mechanisms
4. Establish notification workflows

### Integration Strategies

#### SIEM Integration

* Configure log forwarding
* Establish data retention policies
* Implement analysis rules
* Set up alerting mechanisms

#### Response Automation

1. Configure SNS notifications
2. Establish incident response workflows
3. Implement automated remediation
4. Document response procedures

### Monitoring Considerations

#### Operational Monitoring

* Service performance metrics
* Resource utilization
* System health indicators
* Operational alerts

#### Security Monitoring

* API activity tracking
* Authentication attempts
* Suspicious behavior patterns
* Security incidents

### Best Practices

1. Regular system updates
2. Continuous monitoring configuration review
3. Periodic alarm testing
4. Log retention policy maintenance
5. Access control review
6. Response procedure updates

This document provides a foundation for understanding intrusion detection and prevention systems along with AWS logging services. Regular updates based on emerging threats and AWS service enhancements are recommended.

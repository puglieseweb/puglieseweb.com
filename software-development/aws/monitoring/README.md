# Monitoring

* Amazon CloudWatch: This is the closest AWS-native equivalent to Datadog. It provides:
  * Metrics collection and visualization
  * Log aggregation and analysis
  * Alerting and notification
  * Basic application performance monitoring
* AWS X-Ray: Offers distributed tracing capabilities, similar to Datadog's APM (Application Performance Monitoring).
* Amazon Managed Grafana: While not a direct Datadog equivalent, it provides powerful visualization capabilities that can be used with various AWS data sources.
* AWS CloudTrail: For auditing API calls across your AWS infrastructure.



## Metrics, Logs, and Traces in AWS

The main pillars of observability  in AWS are:

1. Metrics: Primarily handled by CloudWatch Metrics
2. Logs: Managed through CloudWatch Logs and OpenSearch Service
3. Traces: Provided by AWS X-Ray

Other services enhance these pillars with additional capabilities like visualization (Managed Grafana), anomaly detection (DevOps Guru, Lookout for Metrics), and specialized monitoring for containers (Managed Service for Prometheus).

### Metrics

* **Definition**: Numeric values measured over time
* **AWS Service**: Amazon CloudWatch Metrics
* **Use Cases**:
  * Resource utilization (CPU, memory, disk)
  * Application performance (response times, error rates)
  * Business KPIs (transactions per second, active users)
* **Characteristics**:
  * Aggregated data
  * Regular intervals
  * Lightweight
  * Good for alerting and dashboards

### Logs

* **Definition**: Timestamped records of events
* **AWS Service**: Amazon CloudWatch Logs
* **Use Cases**:
  * Application logs
  * Security logs
  * Audit trails
  * Debugging
* **Characteristics**:
  * Detailed information
  * Unstructured or semi-structured
  * Event-driven
  * Good for in-depth analysis and troubleshooting

### Traces

* **Definition**: End-to-end journey of a request through a distributed system
* **AWS Service**: AWS X-Ray
* **Use Cases**:
  * Identifying performance bottlenecks
  * Understanding service dependencies
  * Debugging distributed transactions
* **Characteristics**:
  * Spans multiple services
  * Provides timing and error information
  * Shows request path and service interactions
  * Good for complex, distributed system analysis



## Summary

To summarise all Components of Observability in AWS are:

1. **Amazon CloudWatch**
   * Metrics collection and monitoring
   * Log aggregation and analysis
   * Alarms and notifications
   * Dashboards for visualization
2. **AWS X-Ray**
   * Distributed tracing
   * Service map visualization
   * Performance analysis and bottleneck identification
3. **Amazon OpenSearch Service** (formerly Amazon Elasticsearch Service)
   * Log analytics at scale
   * Full-text search capabilities
   * Visualization with OpenSearch Dashboards
4. **AWS CloudTrail**
   * API activity logging
   * Audit and compliance tracking
5. **Amazon EventBridge** (formerly CloudWatch Events)
   * Event routing and processing
   * Integration with other AWS services and external applications
6. **AWS Systems Manager**
   * Operational insights
   * Automated actions based on operational data
   * Parameter Store for configuration management
7. **Amazon Managed Grafana**
   * Advanced visualization and dashboarding
   * Integration with multiple data sources
8. **Amazon Managed Service for Prometheus**
   * Monitoring for container environments
   * High-cardinality data handling
9. **AWS Distro for OpenTelemetry**
   * Collection of metrics, traces, and logs using open standards
   * Integration with various AWS services
10. **Amazon DevOps Guru**
    * ML-powered operational insights
    * Proactive anomaly detection
11. **AWS App Mesh**
    * Service mesh providing observability for microservices
12. **Amazon Lookout for Metrics**
    * Automated anomaly detection in business and operational metrics
13. **AWS Compute Optimizer**
    * Resource optimization recommendations based on utilization metrics
14. **Amazon Detective**
    * Security-focused analysis of log data for threat detection
15. **AWS Health Dashboard**
    * Status of AWS services and impact on your resources

| Feature                | Amazon CloudWatch                                                                                                    | AWS CloudTrail                                                                                          |
| ---------------------- | -------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| Primary Purpose        | Monitoring and observability service                                                                                 | Auditing and governance service                                                                         |
| Main Function          | Collects and tracks metrics, logs, and events                                                                        | Records AWS account activity and API usage                                                              |
| Data Collected         | Performance metrics, application logs, custom metrics                                                                | API calls, user activity, account changes                                                               |
| Use Cases              | <p>- Performance monitoring<br>- Resource utilization tracking<br>- Application debugging<br>- Setting up alarms</p> | <p>- Security analysis<br>- Compliance auditing<br>- Operational troubleshooting<br>- Risk auditing</p> |
| Scope                  | Focused on AWS resources and applications                                                                            | Covers AWS account-wide activities                                                                      |
| Real-time Capabilities | Yes, for metrics and some logs                                                                                       | No, has a delay (typically 15 minutes)                                                                  |
| Historical Data        | Retention depends on configuration (14 days to indefinite)                                                           | 90 days by default, can be configured for longer                                                        |
| Alerting               | Yes, through CloudWatch Alarms                                                                                       | No direct alerting (can be integrated with CloudWatch)                                                  |
| Visualization          | Built-in dashboards and graphs                                                                                       | No built-in visualization (can use Athena or other tools)                                               |
| Data Processing        | Supports metric math, anomaly detection                                                                              | Primarily for raw data storage and access                                                               |
| Integration            | Tightly integrated with many AWS services for metrics                                                                | Logs API calls across all AWS services                                                                  |
| Cost Model             | Pay for what you use (ingestion, storage, retrieval)                                                                 | Free tier available, then pay for events recorded                                                       |
| Cross-account Support  | Limited (requires additional setup)                                                                                  | Built-in support for organization-wide trails                                                           |
| Custom Inputs          | Supports custom metrics and logs                                                                                     | Does not support custom inputs                                                                          |



## Resources

[https://cloudonaut.io/deep-dive-aws-cloudtrail/](https://cloudonaut.io/deep-dive-aws-cloudtrail/)

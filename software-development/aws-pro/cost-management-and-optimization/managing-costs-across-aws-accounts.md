# Managing Costs Across AWS Accounts

<figure><img src="../../../.gitbook/assets/image (34).png" alt=""><figcaption></figcaption></figure>

### Cost and Usage Reports

<figure><img src="../../../.gitbook/assets/image (27).png" alt=""><figcaption></figcaption></figure>

#### Overview

* Generated from management account's billing center
* CSV file format
* Filtering capabilities:
  * By account
  * By service
  * By custom tags
* Available with consolidated billing in organizations

#### Report Features

* Adjustable time granularity:
  * Hourly
  * Daily
  * Monthly
* Integration with analytics tools:
  * Amazon Athena
  * Amazon Redshift
  * Amazon QuickSight

### Centralized Budget Alerts

<figure><img src="../../../.gitbook/assets/image (35).png" alt=""><figcaption></figcaption></figure>

#### ![](<../../../.gitbook/assets/image (31).png>)Management Account Capabilities

* Access to AWS Budgets
* Creation of account-specific budgets
* Independent budget adjustment per account

#### Alert Architecture

1. Budget Alert Generation
   * Triggers SNS topic
   * Supports multiple subscribers
   * Enables event triggering
2. Cross-Account Implementation
   * SNS topic triggers Lambda function
   * Lambda sends event to EventBridge
   * Account-specific event buses receive events
   * Account-level Lambda functions process events
3. Response Actions
   * Automated resource shutdown
   * Administrator notifications
   * Custom mitigation actions

### Tagging Strategy

#### Tag Fundamentals

* Custom attributes for AWS resources
* Nearly universal resource compatibility
* Essential for multi-account management

#### Common Tagging Categories

* Environment
* Project
* Owner
* Version
* Team
* Customer

#### Multi-Account Applications

* Cost and usage report filtering
* Budget filtering and alerts
* Resource grouping
* Automation targeting

### Best Practices

#### Cost Monitoring

1. Report Generation
   * Regular cost analysis
   * Service-level monitoring
   * Account-specific tracking
2. Budget Implementation
   * Account-specific thresholds
   * Tag-based monitoring
   * Automated alert systems
3. Tag Management
   * Consistent naming conventions
   * Cross-account standardization
   * Regular tag audits

#### Automation and Control

1. Alert Response
   * Email notifications
   * Automated mitigation
   * Resource control actions
2. Resource Organization
   * Systematic tagging approach
   * Comprehensive AWS footprint visibility
   * Cross-account resource management

### Implementation Considerations

* Strategic tag planning essential
* Holistic environment view required
* Automated cost control mechanisms
* Cross-account communication protocols
* Regular monitoring and adjustment

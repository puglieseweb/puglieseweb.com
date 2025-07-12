# System Manager

## AWS Systems Manager Overview

AWS Systems Manager is a comprehensive management console that provides a suite of tools for managing large-scale AWS environments. The service is particularly relevant for managing environments with tens or hundreds of instances.

### Core Component: SSM Agent

The primary worker component is the SSM Agent, which:

* Is pre-installed on most modern AWS AMIs (since approximately 2016)
* Can be downloaded and installed on on-premises systems
* Enables Systems Manager functionality on managed instances

### Key Services and Features

#### 1. Inventory

* Collects information from managed instances about applications and their versions
* Gathers various metadata
* Use case: Identifying instances running specific software versions (e.g., finding servers running outdated Apache versions in a fleet of 200 web servers)

#### 2. State Manager

* Defines groups of machines based on configuration states
* Enables organization of servers into manageable groups
* Example: Categorizing servers into pre-upgrade and post-upgrade states based on Apache versions

#### 3. Logging

* AWS currently recommends using the CloudWatch Log agent
* Enables log streaming directly to CloudWatch
* Provides access to CloudWatch features like monitoring and notification
* Centralizes log management from instances

#### 4. Parameter Store

* Stores configuration data and connection strings
* Integrates with Secrets Manager
* Use case: Storing RDS credentials for config file updates during instance boot

#### 5. Insights Dashboard

* Provides account-level views of:
  * CloudTrail
  * Config
  * Trust Advisor screens

#### 6. Resource Groups

* Organizes resources through tagging
* Creates focused dashboards for specific asset groups
* Example: Dedicated dashboard for production ERP landscape assets

#### 7. Maintenance Windows

* Defines schedules for service operations
* Integrates with other services like Patch Manager
* Example: Setting patch application windows between midnight and 2 AM

#### 8. Automation

* Handles routine maintenance tasks and scripts
* Use case: Scheduling DEV and QA instance shutdowns during weekends

#### 9. Run Commands

* Executes scripts or commands across multiple instances
* Eliminates need for SSH/RDP connections
* Useful for bulk administrative tasks

#### 10. Patch Manager

* Automates patch application across fleets
* Uses baselines for patch approval management
* Features:
  * Predefined baselines for supported OSs
  * Custom baseline creation options
  * Default 7-day timer for patch application
  * Configurable patch criteria

Example of Patch Manager Baseline for Windows:

<figure><img src="../../../../../.gitbook/assets/image (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Systems Manager Documents (SSM Documents)

SSM Documents define Systems Manager actions using JSON or YAML formats. They support versioning and can be shared across accounts or publicly.

#### Document Types

<figure><img src="../../../../../.gitbook/assets/image (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. Command Documents
   * Used with Run Command and State Manager
   * Contains executable commands and configuration instructions
2. Policy Documents
   * Used with State Manager
   * Defines conditions for state assignment
   * Example: Determining instance state based on Apache version
3. Automation Documents
   * Used with the Automation service
   * Contains lists of tasks or commands for automation workflows

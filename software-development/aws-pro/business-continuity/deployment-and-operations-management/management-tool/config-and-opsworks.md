# Config and OpsWorks

### AWS Config

AWS Config is a service that enables auditing, assessment, and evaluation of AWS resource configurations. It's particularly valuable for organizations implementing ITIL, especially in addressing the configuration management component.

#### Key Features

1. Configuration Baseline Management
   * Creates baselines of configuration settings and files on systems
   * Tracks variations against these baselines over time
   * Detects and notifies about system changes (e.g., modifications to Linux fstab file)
2. Compliance Monitoring
   * Supports creation of Config rules to check resources for specific conditions
   * Flags non-compliant resources automatically
   * Common rule examples:
     * Verification of RDS backup enablement
     * CloudTrail activation status
     * EBS volume encryption status

### AWS OpsWorks

AWS OpsWorks is a managed instance service that provides automated platform management using Chef and Puppet. It offers comprehensive infrastructure management capabilities including upgrades, code deployment, backup configuration, and config file management.

#### Service Offerings

1. OpsWorks for Chef Automate
   * Fully managed hosted version of Chef
   * Ideal for organizations currently using Chef on-premise
2. OpsWorks for Puppet Enterprise
   * Fully managed hosted version of Puppet
   * Seamless transition for existing Puppet Enterprise users
3. OpsWorks Stacks
   * AWS-specific creation compatible with Chef recipes
   * Uses embedded Chef solo client on EC2 instances
   * Supports on-premise servers with agent installation

#### Stacks and Layers Architecture

1. Stacks
   * Collections of resources supporting services or applications
   * Can be cloned within the same region
   * Region-specific deployment and management
2. Layers
   * Represent components of delivery hierarchy
   * Examples include:
     * Data layer
     * Middleware layer
     * Web layer
   * Can incorporate various AWS services (EC2, RDS, etc.)

#### Important Limitations

* Stack cloning restricted to same region
* While OpsWorks is a global service, stacks are region-specific
* Cross-region resource management not supported (e.g., US-region stack cannot manage EU-region instances)

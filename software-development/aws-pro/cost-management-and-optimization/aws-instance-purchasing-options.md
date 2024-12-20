# AWS Instance Purchasing Options

### Reserved Instances (RI)

#### Overview

* Advance purchase of EC2 instance usage
* Significant discount compared to On-Demand
* Automatic discount application
* Available for EC2 and RDS
* Shareable across accounts with consolidated billing
* Can be sold on Reserved Instance Marketplace

#### Types of Reserved Instances

<figure><img src="../../../.gitbook/assets/image (36).png" alt=""><figcaption></figcaption></figure>

**Standard Reserved Instances**

* 40-60% discount
* Terms: 1 or 3 years
* Payment options:
  * All upfront
  * Partial upfront
  * No upfront
* Limited flexibility
* Can be sold on RI Marketplace

**Convertible Reserved Instances**

* 31-54% discount
* Same term options as Standard
* Greater flexibility
* Can change instance family
* Can benefit from price reductions
* Currently not sellable on RI Marketplace

#### Reserved Instance Attributes

1. Instance Type Configuration
   * Instance type (CPU, memory, networking)
   * Platform (OS)
   * Tenancy (shared/dedicated)
   * Optional AZ specification
2. Zonal vs Regional RIs
   * Zonal: Specific AZ with capacity guarantee
   * Regional: Any AZ in region, no capacity guarantee
   * Changeable via console/API
   * Instance size flexibility (Linux/default tenancy only)

### Spot Instances

#### Fundamentals

* Uses AWS excess capacity
* Market-based pricing
* Significant cost savings
* Risk of termination

#### Spot Request Types

1. One-Time (Fill and Kill)
   * Single fulfillment
   * Terminates when price exceeds bid
2. Maintain
   * Automatically reprovisions
   * Continues after termination
   * Price-dependent provisioning
3. Duration-Based
   * Fixed time period
   * Automatic termination
   * Specified duration

#### Market Behavior

* Price fluctuations by AZ
* Instance type impact
* Demand-based pricing
* Strategy considerations for different AZs

### Dedicated Options

#### Dedicated Instances

* Hardware dedicated to single account
* Available as:
  * On-Demand
  * Reserved
  * Spot
* Additional $2/hour per region cost
* Shares hardware with account's non-dedicated instances

#### Dedicated Hosts

* Physical server dedication
* Instance placement control
* Licensing advantages
* Single instance type per host
* Available as:
  * On-Demand
  * Reserved
* Capacity varies by instance type

### Best Practices

#### Cost Optimization

* Mix purchase options for optimal cost
* Consider workload patterns
* Monitor market prices
* Use automatic scaling with spot instances

#### Availability Strategy

* Use multiple AZs for spot workloads
* Consider capacity guarantees for critical workloads
* Plan for instance interruption
* Implement proper backup strategies

#### License Management

* Use dedicated hosts for license-bound software
* Track core/socket requirements
* Plan capacity based on license restrictions

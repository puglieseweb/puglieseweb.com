# Auto-Scaling

There are three types of auto scaling services:&#x20;

1. AWS Auto Scaling Service: Holistic scaling view + predictive scaling feature
2. EC2 Auto Scaling Service: Focused specifically on EC2 instance scaling
3. Application Auto Scaling Service: API for scaling non-EC2 AWS services

## AWS Auto Scaling Service

* Provides holistic view of all auto scaling activities
* Manages scaling across different application layers
* Accessed through dedicated console
* Offers business-oriented scaling strategies

<figure><img src="../../../../.gitbook/assets/image (8) (1) (1).png" alt=""><figcaption></figcaption></figure>

## Application Auto Scaling

API-based service for non-EC2 resources. Manages scaling for services like:

* DynamoDB
* ECS
* EMR

Application Auto Scaling Consolidates various service-specific scaling capabilities into a single API.

There are three Application Auto Scaling types:

| Scaling                  | What                                                                                       | When                                                                                                |
| ------------------------ | ------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------- |
| Target Tracking Policy   | Initiates scaling events to try to track as closely as possible a given **target metric.** | "I want my ECS hosts to stay at or below 70% CPU utilization."                                      |
| Step Scaling Policy      | Based on a metric, adjusts capacity given certain **defined thresholds.**                  | "I want to increase my EC2 Spot Fleet by 20% every time I add another 10,000 connections on my ELB" |
| Scheduled Scaling Policy | Initiates scaling events **based on a predefined time, day or date.**                      | "Every Monday at 0800, I want to increase the Read Capacity Units of my DynamoDB Table to 20,000"   |

## EC2 Auto Scaling Service

### Core Components

1. EC2 Auto Scaling Service
   * Primary service for EC2 resource scaling
   * Manages core scaling functionality
   * Handles policies, metrics, configurations
2. EC2 Auto Scaling Groups (ASG)
   * Logical grouping of EC2 instances
   * Implements scaling mechanisms
   * Manages instance monitoring
   * Controls min/max instances
   * Applies policies and health checks

### ASG Scaling Types

#### 1. Dynamic Scaling

Responds to real-time demand through three policy types:

| Scaling Policies               | What                                                                             | When                                                               |
| ------------------------------ | -------------------------------------------------------------------------------- | ------------------------------------------------------------------ |
| Target Tracking Scaling Policy | Scale based on a predefined or custom metric in relation to a target value       | "When CPU utilization gets to 70% on current instances, scale up." |
| Simple Scaling Policy          | Waits until health check and cool down period expires before evaluating new need | "Let's add new instances slow and steady."                         |
| Step Scaling Policy            | Responds to scaling needs with more sophistication and logic                     | "AGG! Add ALL the instances!"                                      |

#### 2. Predictive Scaling

* Leverages machine learning algorithms
* Analyzes historical data to forecast scaling needs
* Offers automatic scaling and advisory insights
* Optional data collection feature

<figure><img src="../../../../.gitbook/assets/image (9) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### 3. Scheduled Scaling

* Time-based pattern scaling
* Useful for known traffic patterns

### ASG Operating Modes

1. **Maintain Mode**
   * Keeps a fixed instance count
   * No scaling types involved
   * Simply maintains desired capacity
2. **Manual Mode**
   * User-controlled scaling
   * No automatic scaling types
   * Changes made through manual intervention
3. **Schedule-based Mode**
   * Uses Scheduled Scaling
   * Time-based pattern scaling
   * Useful for known traffic patterns
4. **Dynamic Mode**
   * Uses both Dynamic and Predictive Scaling
   * Dynamic Scaling Options:
     * Target Tracking Policy
     * Simple Scaling Policy
     * Step Scaling Policy
   * Predictive Scaling Features:
     * ML-based forecasting
     * Historical data analysis
     * Optional data collection

### Configuration Elements

<figure><img src="../../../../.gitbook/assets/image (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### **Launch Configuration**

* AMI specifications
* VPC settings
* Load balancer integration
* Network configurations

#### **Health Check Grace Period**

* Allows time for instance initialization
* Prevents premature health check failures

#### Cooldown Periods

* Default: 300 seconds
* Purpose: Stabilization time between scaling events
* Applies to:
  * Dynamic scaling (mandatory)
  * Manual scaling (optional)
  * Scheduled scaling (not supported)
* Can be customized for specific scenarios

<figure><img src="../../../../.gitbook/assets/image (3) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Scaling Based on Amazon SQS

<figure><img src="../../../../.gitbook/assets/image (5) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>




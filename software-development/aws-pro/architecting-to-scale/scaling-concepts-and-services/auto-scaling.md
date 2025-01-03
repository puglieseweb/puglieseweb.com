# Auto-Scaling

### Types of Auto Scaling Services

There are three types of auto scaling services:&#x20;

1. AWS Auto Scaling: Holistic scaling view + predictive scaling feature
2. EC2 Auto Scaling: Focused specifically on EC2 instance scaling
3. Application Auto Scaling: API for scaling non-EC2 AWS services

### Amazon EC2 Auto Scaling

* Longest-established auto scaling service
* Focused exclusively on EC2 instances
* Provides horizontal scaling (scaling out)
* Uses health checks and metrics to manage instance count

<figure><img src="../../../../.gitbook/assets/image (2) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Application Auto Scaling

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



#### 3. AWS Auto Scaling

* Provides holistic view of all auto scaling activities
* Manages scaling across different application layers
* Accessed through dedicated console
* Offers business-oriented scaling strategies

<figure><img src="../../../../.gitbook/assets/image (8) (1) (1).png" alt=""><figcaption></figcaption></figure>

AWS Predictive Scaling:

* Uses machine learning algorithms
* Analyzes historical data
* Predicts scaling needs
* Options for:
  * Automatic scaling
  * Advisory insights
* Optional data collection (can opt out)

<figure><img src="../../../../.gitbook/assets/image (9) (1) (1).png" alt=""><figcaption></figcaption></figure>

### EC2 Auto Scaling Groups in Detail

#### Scaling Options

1. **Maintain**
   * Keeps consistent number of instances running
2. **Manual**
   * User-specified instance counts
   * Configurable maximum and minimum limits
3. **Schedule-based**
   * Time-triggered scaling
   * Useful for predictable load patterns
4. **Dynamic**
   * Metric-based scaling
   * Responds to real-time demand

#### Configuration Components

<figure><img src="../../../../.gitbook/assets/image (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. **Launch Configuration**
   * AMI specifications
   * VPC settings
   * Load balancer integration
   * Network configurations
2. **Health Check Grace Period**
   * Allows time for instance initialization
   * Prevents premature health check failures

#### Scaling Policies

Within the Dynamic autoscaling group we have different Scaling policies:

1. **Target Tracking**
   * Maintains specific metric targets
   * Example: 70% CPU utilization
2. **Simple Scaling**
   * Basic instance addition/removal
   * Includes cooldown period
3. **Step Scaling**
   * Advanced scaling logic
   * More sophisticated control

#### Cooldown Periods

* Default: 300 seconds
* Purpose: Stabilization time between scaling events
* Applies to:
  * Dynamic scaling (mandatory)
  * Manual scaling (optional)
  * Scheduled scaling (not supported)
* Can be customized for specific scenarios

<figure><img src="../../../../.gitbook/assets/image (3) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Scaling Based on Amazon SQS

<figure><img src="../../../../.gitbook/assets/image (5) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>




# Auto-Scaling

### Types of Auto Scaling Services

<figure><img src="../../../../.gitbook/assets/image.png" alt=""><figcaption></figcaption></figure>

* WS Auto Scaling = Holistic scaling view + predictive scaling feature
* EC2 Auto Scaling = Focused specifically on EC2 instance scaling
* Application Auto Scaling = API for scaling other AWS services

#### 1. Amazon EC2 Auto Scaling

* Longest-established auto scaling service
* Focused exclusively on EC2 instances
* Provides horizontal scaling (scaling out)
* Uses health checks and metrics to manage instance count

<figure><img src="../../../../.gitbook/assets/image (2).png" alt=""><figcaption></figcaption></figure>

#### 2. Application Auto Scaling

* API-based service for non-EC2 resources
* Manages scaling for services like:
  * DynamoDB
  * ECS
  * EMR
* Consolidates various service-specific scaling capabilities into a single API

<figure><img src="../../../../.gitbook/assets/image (7).png" alt=""><figcaption></figcaption></figure>

#### 3. AWS Auto Scaling

* Provides holistic view of all auto scaling activities
* Manages scaling across different application layers
* Accessed through dedicated console
* Offers business-oriented scaling strategies

<figure><img src="../../../../.gitbook/assets/image (8).png" alt=""><figcaption></figcaption></figure>

AWS Predictive Scaling:

* Uses machine learning algorithms
* Analyzes historical data
* Predicts scaling needs
* Options for:
  * Automatic scaling
  * Advisory insights
* Optional data collection (can opt out)

<figure><img src="../../../../.gitbook/assets/image (9).png" alt=""><figcaption></figcaption></figure>

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

<figure><img src="../../../../.gitbook/assets/image (1).png" alt=""><figcaption></figcaption></figure>

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

<figure><img src="../../../../.gitbook/assets/image (3).png" alt=""><figcaption></figcaption></figure>

### Scaling Based on Amazon SQS

<figure><img src="../../../../.gitbook/assets/image (5).png" alt=""><figcaption></figcaption></figure>




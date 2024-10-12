# Comparison of Deployment Strategies in AWS

##

| Aspect                     | Rolling Deployment                                                  | A/B Testing                                                      | Canary Release                                              | Blue-Green Deployment                                                |
| -------------------------- | ------------------------------------------------------------------- | ---------------------------------------------------------------- | ----------------------------------------------------------- | -------------------------------------------------------------------- |
| **Definition**             | Gradually replace instances of the old version with the new version | Route a portion of traffic to a new version to compare behaviors | Release to a small subset of users before full deployment   | Create two identical environments and switch traffic from old to new |
| **Traffic Distribution**   | Gradual shift as instances are replaced                             | Split between two versions                                       | Small percentage to new version, then gradual increase      | Full switch from old to new                                          |
| **Risk Level**             | Medium                                                              | Low                                                              | Low                                                         | Low to Medium                                                        |
| **Rollback Ease**          | Moderate                                                            | Easy                                                             | Easy                                                        | Very Easy                                                            |
| **Cost**                   | Low                                                                 | Medium                                                           | Low to Medium                                               | High (maintaining two full environments)                             |
| **Downtime**               | Minimal to None                                                     | None                                                             | None                                                        | None (if done correctly)                                             |
| **User Impact**            | Minimal                                                             | Some users see different versions                                | Limited initial impact                                      | No impact if done correctly                                          |
| **Testing in Production**  | Limited                                                             | Yes                                                              | Yes                                                         | Limited                                                              |
| **Main AWS Services Used** | <p>• Auto Scaling Groups<br>• Elastic Load Balancer</p>             | <p>• Route 53<br>• CloudFront<br>• API Gateway</p>               | <p>• Auto Scaling Groups<br>• Application Load Balancer</p> | <p>• Route 53<br>• Elastic Load Balancer</p>                         |

### AWS Implementation Details

#### Rolling Deployment

* Use Auto Scaling groups with multiple instances
* Gradually update the launch configuration or template
* Elastic Load Balancer routes traffic to healthy instances

#### A/B Testing

* Use Route 53 weighted routing policies
* Alternatively, use CloudFront with Lambda@Edge for more complex routing
* API Gateway can also be used for testing different API implementations

#### Canary Release

* Use Application Load Balancer with target groups
* Gradually adjust traffic percentage to the new version
* AWS Lambda can be used for serverless canary deployments

#### Blue-Green Deployment

The Goal of blue/green deployments is to achieve immutable infrastructure, where you don't make changes to your application after it's deployed, but redeploy altogether:

* Create two identical environments (Blue and Green)
* Use Route 53 or Elastic Load Balancer to switch traffic
* AWS Elastic Beanstalk natively supports blue-green deployments

## Additional notes

Now, let me provide some additional context and exam tips related to Auto Scaling groups:

1. **Launch Templates vs. Launch Configurations**
   * Launch Templates are newer and more flexible
   * They support versioning and provide more features
2. **Scaling Policies**
   * **Target Tracking**: Maintains a specific metric at a target value
   * **Step Scaling:** Adjusts capacity based on specified CloudWatch alarms
   * **Simple Scaling**: Basic scaling based on a single scaling adjustment

<figure><img src="../../.gitbook/assets/image (23).png" alt=""><figcaption></figcaption></figure>

1. **Cooldown Periods**
   * Time after a scaling activity where further scaling activities are suspended
   * Helps prevent rapid scaling up and down (thrashing)
   * Default is 300 seconds (5 minutes)
2. **Health Checks**
   * EC2 Status Checks: Default, checks instance health
   * ELB Health Checks: More thorough, checks application health
   * Know when to use each type
3. **Instance Protection**
   * Prevents instances from being terminated during scale-in events
   * Useful for stateful applications or when running critical processes
4. **Integration with other AWS Services**
   * Elastic Load Balancing: Distributes traffic across instances
   * CloudWatch: Provides metrics for scaling decisions
   * SNS: Sends notifications about scaling events
5. **Lifecycle Hooks**
   * Allow you to perform custom actions when instances launch or terminate
   * Useful for installing software or retrieving data before termination
6. **Mixed Instances Policy**
   * Allows you to use multiple instance types in your Auto Scaling group
   * Helps optimize cost and availability

For the exam:

* Be prepared to design Auto Scaling solutions for various scenarios
* Understand how to troubleshoot common Auto Scaling issues
* Know how to calculate the appropriate minimum, maximum, and desired capacity based on application requirements
* Be familiar with best practices for Auto Scaling in production environments

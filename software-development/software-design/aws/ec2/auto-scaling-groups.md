# Auto scaling groups

An Auto Scaling group contains a collection of EC2 instances that are treated as a collective group for purpose of scaling and management.



<mark style="color:purple;">**Auto Scaling is vital to create high available application by spreading resources (e.g. EC2 instances) out over multiple AZs and utilizing ELB**</mark>&#x20;



1. Define Your Template. You pick from your available launch template or launch configurations.&#x20;
2. Networking and Purchasing. Pick your networking space and purchasing option. Using multiple AZs allows for high availability (HA).
3. ELB Configuration. EC2 instances can be registered behind a load balancer. The Auto Scaling group can be set to respect the load balancer health checks.&#x20;
4. Set Scaling Policies. Minimum, maximum, and desired capacity needs to be set to ensure you don't have too many or too few resources.
5. Notification. SNS can act as a notification tool, allowing you to let someone know when a scaling event occurs.

## Lifecycle Hooks

{% embed url="https://docs.aws.amazon.com/autoscaling/ec2/userguide/ec2-auto-scaling-lifecycle.html" %}

For example:

1. EC2 instance gets launched by EC2 Auto Scaling group&#x20;
2. Wait state is entered via the Lifecycle Hooks capacity&#x20;
3. While in the Wait state, the instance runs a custom script via EC2 user data to install a proprietary application
4. Script install and configure application&#x20;
5. Once the application is validate to be working correctly, the instance sends a complete-lifescycle-action command

## Autoscaling Policies&#x20;

* Step Scaling. Applies stepped adjustments to vary the scaling depending on the size of the alarm breach
* Simple Scaling. Relies on metrics for scaling needs. Example: Add 1 instance if the CPU Utilization metric > 80%
* Target Tracking. Uses a scaling metric and value that your ASG should maintain at all times. Example: Maintain ASG Average CPU Utilization = 50%
* Instance Warm-up and Cooldown.&#x20;
  * Warm-up stops instances from being placed behind the load balancer, failing the health check, and being terminated prematurely.
  * Cooldown. Pauses Auto Scaling for a set amout of time. Helps to avoid runaway scaling events.
  * Avoid Thrashing. You want to create instances quickly and spin them down slowly.&#x20;

## Scaling types

* Reactive scaling. Playig catchup. Once the load is there, you meansure it and then determine if you need to create more resources.
* Scheduled Scaling. If you have a predictable workload, create a scaling event to get your resources ready to go before they're actually needed.&#x20;
* Predictive Scaling. AWS uses its machine learning algorithms to determine when it need to scale. They are reevaluated every 24 hours to create a forecast for the next 48.

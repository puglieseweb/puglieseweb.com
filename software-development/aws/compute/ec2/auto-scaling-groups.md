# Auto Scaling Groups (ASG)

An Auto Scaling group contains a collection of EC2 instances that are treated as a collective group for purpose of scaling and management.



<mark style="color:purple;">**Auto Scaling is vital to create high available applications by spreading resources (e.g. EC2 instances) out over multiple AZs, utilising ELB.**</mark>



Steps:

1. Define Your Template. You pick from your available _launch template_ or _launch configurations._&#x20;
2. Networking and Purchasing. Pick your networking space and purchasing option. Using multiple AZs allows for high availability (HA).
3. ELB Configuration. EC2 instances can be registered behind a load balancer. The Auto Scaling group can be set to respect the load balancer health checks.&#x20;
4. Set Scaling Policies. **Minimum**, **maximum**, and d**esired capacity** needs to be set to ensure you don't have too many or too few resources.
5. Notification. SNS can act as a notification tool, allowing you to let someone know when a scaling event occurs.

## Lifecycle Hooks



We can add lifecycle Hooks to both _**pending**_ and _**terminating**_ stages on and EC2 Auto Scaling Group.

{% embed url="https://docs.aws.amazon.com/autoscaling/ec2/userguide/ec2-auto-scaling-lifecycle.html" %}

For example:

1. EC2 instance gets launched by EC2 Auto Scaling group&#x20;
2. Wait state is entered via the Lifecycle Hooks capacity&#x20;
3. While in the Wait state, the instance runs a custom script via EC2 user data to install a proprietary application
4. Script install and configure application&#x20;
5. Once the application is validate to be working correctly, the instance sends a complete-lifescycle-action command

## Autoscaling Policies&#x20;

### Target Tracking Scaling

* Think of it like cruise control in your car
* You set a target value (like CPU at 50%)
* ASG automatically adjusts capacity to maintain this target
* Example: If CPU goes to 60%, it adds instances. If CPU drops to 40%, it removes instances
* Best for stable, predictable workloads
* Simplest to set up and manag

### Step Scaling

* Like a staircase of responses based on how far metric deviates
* More aggressive response for larger breaches
* Example:
  * If CPU is 60-70%: Add 1 instance
  * If CPU is 70-80%: Add 2 instances
  * If CPU is >80%: Add 4 instances
* More precise control over scaling
* Better for handling varying degrees of load changes
* No cooldown period between each step adjustment

### Simple Scaling

* Like a basic if-then rule
* Single metric threshold triggers a single action
* Example: "If CPU > 80%, add 2 instances"
* Waits for cooldown period before making another adjustment
* Can lead to capacity ping-pong (adding/removing too frequently)
* Less granular control

### **Instance Warm-up** and **Cooldown**.&#x20;

* Warm-up stops instances from being placed behind the load balancer, failing the health check, and being terminated prematurely.
* Cooldown. Pauses Auto Scaling for a set amout of time. Helps to avoid runaway scaling events.
* Avoid Thrashing. **You want to create instances quickly and spin them down slowly.**&#x20;

## Scaling types

* **Reactive scaling**. Playing catch up. Once the load is there, you measure it and then determine if you need to create more resources.
* **Scheduled Scaling.** If you have a predictable workload, create a scaling event to get your resources ready to go before they're actually needed.&#x20;
* **Predictive Scaling**. AWS uses its machine learning algorithms to determine when it need to scale. **Predictions are reevaluated every 24** hours to create a forecast for the next 48.


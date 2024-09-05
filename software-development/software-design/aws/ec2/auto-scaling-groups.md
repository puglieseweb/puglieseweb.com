# Auto scaling groups

An Auto Scaling group contains a collection of EC2 instances that are treated as a collective group for purpose of scaling and management.



1. Define Your Template. You pick from your available launch template or launch configurations.&#x20;
2. Networking and Purchasing. Pick your networking space and purchasing option. Using multiple AZs allows for high availability (HA).
3. ELB Configuration. EC2 instances can be registered behind a load balancer. The Auto Scaling group can be set to respect the load balancer health checks.&#x20;
4. Set Scaling Policies. Minimum, maximum, and desired capacity needs to be set to ensure you don't have too many or too few resources.
5. Notification. SNS can act as a notification tool, allowing you to let someone know when a scaling event occurs.

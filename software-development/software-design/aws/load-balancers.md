# Load Balancers

### Layer 7 Application Load Balancer (ALB)

ALB are Layer 7 aware and support only two linsteners: HTTP adn HTTPS.

An Application Load Balancer funtions at the Application Layer - the seventh layer of the Open System Interconnection (OSI) model. After the load balancer receive a request, it evaluates the listener rules in priority order to determinate which rule to apply, and then select a target from the target group for the rule action.&#x20;



A listener checks for connection requests from clients, using the configured protocol and port.&#x20;

Rules determine how the load balancer routes requests to the registered targets. When a conditions for a rule are met then its actions are performed. At least a default rule for each listener must be defined.



Target group routes requests to one or more registered targets, such as EC2 instances, using the protocol and port number you specify.



<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/gTC9GbPsHvN3?a=4594&#x26;x=53&#x26;y=208&#x26;w=1414&#x26;h=687&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%2082abff9808cf22ac24de6b74e47f640b426c22f7f82256a91c4fc7738e8a5946-ts%3D1725388407" alt=""><figcaption></figcaption></figure>



Health check. if you're using an Application Load Balancer (ALB), which is a Layer 7 load balancer, and one of the EC2 instances in the target group fails its health check, the traffic will indeed be routed to the other healthy instance. Here's a more detailed explanation:

1. Health Check Process:
   * The ALB performs regular health checks on all registered targets (EC2 instances) in the target group.
   * These health checks are customizable and can be based on protocols like HTTP, HTTPS, or TCP.
2. Instance Failure Detection:
   * If an instance fails its health check, the ALB marks it as unhealthy.
   * The ALB requires a certain number of consecutive failed checks before marking an instance as unhealthy (this is configurable).
3. Traffic Routing:
   * Once an instance is marked as unhealthy, the ALB stops routing new requests to that instance.
   * All new incoming traffic is automatically routed to the remaining healthy instance(s) in the target group.
4. Dynamic Adjustment:
   * This process is dynamic and automatic. No manual intervention is required.
   * If the unhealthy instance recovers and passes its health checks, the ALB will start routing traffic to it again.
5. Load Balancing Algorithm:
   * The ALB uses advanced request routing algorithms to distribute traffic among healthy instances.
   * Even with one instance down, it continues to balance traffic effectively across remaining healthy instances.
6. Metrics and Monitoring:
   * AWS provides metrics and logs to monitor the health status of your instances and the traffic distribution.
7. Auto Scaling Integration:
   * If you're using Auto Scaling, it can be configured to replace unhealthy instances automatically.

This behavior ensures high availability and fault tolerance for your application. Even if one instance fails, your service remains available through the other healthy instance(s).

It's important to note that for optimal reliability, it's generally recommended to have more than two instances across multiple Availability Zones when possible. This provides better resilience against both instance failures and potential AZ outages.



## Path-Based Routing

traffing can be routed based on the resource uri path to different AZs

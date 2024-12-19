# Elastic Load Balancers

There are 4 ELB types:

1. Application Load Balancers (Layer 7)
2. Network Load Balancers (Layer 4)
3. Gateway Load Balancers (Layer 3)
4. Classic Load Balaners (Layer4/7)

<figure><img src="../../.gitbook/assets/image (30) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Comparison Chart

```
CopyFeature          | ALB           | NLB           | GWLB
-----------------|---------------|---------------|----------------
Max Throughput   | 100 Gbps      | 100 Gbps      | 100 Gbps
Latency         | ~400ms        | <100ms        | Low
Protocol Layer  | Layer 7       | Layer 4       | Layer 3/4
Use Case        | Web apps      | TCP/UDP apps  | Security apps
TLS Termination | Yes           | Yes           | No
HTTP/2          | Yes           | No            | No
WebSocket       | Yes           | Yes           | No
```

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

Traffic can be routed based on the resource uri path to different AZs.



## Network Load Balancer

NLB provides extreme performances (allows tens of thousands concurrent connections).

NLB is a Layer 4 Load balancing (works at Transport layer). After a connection request, the NLB select a target from the target group for the default rule.

It attempts to open a TCP connection to the selected target on the port specified in the listener configuration.&#x20;

The listener on a NLB then forwards the request to the target group. **There are no rules, unlike with ALB.**



* **Protocols supported: TCP, UDP, TLS, TCP\_UDP**&#x20;
* **Ports supported: 1-65535**

You can use a TLS listener to offload the work of encryption and decryption to your load balancer so your applications can focus on their business logic.&#x20;

If the protocol is TLS you must deploy exactly one SSL server certificate on the listener.&#x20;



Key Features of NLBs at Layer 4:

* NLBs can handle TCP, UDP, and TLS traffic.
* They're capable of handling millions of requests per second with low latency.
* NLBs provide static IP addresses for each Availability Zone, as well as one static IP for the load balancer.
* They support zonal isolation, where each load balancer node in the Availability Zone uses an independent IP address.

The correction mainly concerns the IP address allocation. Let me elaborate:

* Static IP per Availability Zone: Each NLB node in an Availability Zone gets a static IP address. This means if you have the NLB in three AZs, you'll get three static IP addresses, one for each AZ.
* Elastic IP (Optional): You can optionally assign one Elastic IP address per Availability Zone to the NLB, allowing you to keep the same IP address even if you need to recreate the NLB.
* Single DNS Name: The NLB is accessed via a single DNS name, which resolves to the node IPs in the enabled Availability Zones.

## Classing Load Balancer&#x20;

CLB is the same of ALB  (Layer 7 load balancer) but can also use X-Forward-ror and Sticky sessions. Application that relay on TCP protocol and also use Layer 4 load balancing.



X-Forwarded-For  request header contains the original IP address of the client.&#x20;

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/gTC9GbPsHvN3?a=4785&#x26;x=443&#x26;y=1365&#x26;w=795&#x26;h=382&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%2010e0235b5783992f6850e7b779ae2d7d33f9a6a677801aeff2d913ae4bc809b0-ts%3D1725388407" alt=""><figcaption></figcaption></figure>

504 error is Gateway timeout: if your application stops responding, the classing load balancer responds with a 504. This normally means that the application is having an issue at web server layer or database layer.



## Deregistration Delay in AWS Load Balancers

### Overview

Deregistration Delay (formerly known as Connection Draining in Classic Load Balancers) is a feature that helps gracefully remove instances from service while completing in-flight requests. This feature ensures a smooth transition when instances are being removed from the load balancer, preventing disruption to end users.

### How It Works

1. When an instance is being deregistered or becomes unhealthy:
   * The load balancer stops sending new requests to the instance
   * Existing in-flight requests are allowed to complete
   * The instance remains registered until either:
     * All in-flight requests are completed
     * The maximum deregistration delay timeout is reached

### Configuration Details

#### Supported Load Balancers

* Classic Load Balancer (CLB): Called "Connection Draining"
* Application Load Balancer (ALB): Called "Deregistration Delay"
* Network Load Balancer (NLB): Called "Deregistration Delay"

#### Timeout Settings

* Default timeout: 300 seconds (5 minutes)
* Configurable range: 1-3600 seconds (1 second to 1 hour)
* Setting to 0 seconds disables the feature

### Common Use Cases

1. Instance Maintenance
   * Performing updates or patches
   * Replacing instances
   * Scaling down operations
2. Blue-Green Deployments
   * Ensures zero downtime during deployments
   * Allows old instances to complete requests before termination
3. Auto Scaling Events
   * Graceful handling of scale-in events
   * Proper request completion during instance termination

### Best Practices

1. Set Appropriate Timeout Values
   * Consider your application's average request processing time
   * Add buffer time for long-running requests
   * Avoid setting unnecessarily long timeouts
2. Monitoring
   * Monitor connection draining status through CloudWatch metrics
   * Track the number of requests in flight during deregistration
   * Set up alerts for instances stuck in draining state
3. Testing
   * Test deregistration delay during non-peak hours
   * Verify behavior during deployment processes
   * Ensure proper timeout configuration

### Example AWS CLI Commands

```bash
# Set deregistration delay for ALB target group
aws elbv2 modify-target-group-attributes \
    --target-group-arn arn:aws:elasticloadbalancing:region:account-id:targetgroup/my-targets/73e2d6bc24d8a067 \
    --attributes Key=deregistration_delay.timeout_seconds,Value=60

# Enable connection draining for CLB
aws elb modify-load-balancer-attributes \
    --load-balancer-name my-load-balancer \
    --load-balancer-attributes "{\"ConnectionDraining\":{\"Enabled\":true,\"Timeout\":60}}"
```




# Elastic Load Balancing (ELB)

ELB automatically distributes incoming application traffic across multiple targets, such as Amazon EC2 instances. This can be done across multiple AZs.

There are 4 types of Load Balancers:

1. Application Load Balancer (Intelligent Load Balancer). Best suited for load balancing of HTTP and HTTPS Traffic. They operate at Layer 7 and are application aware.&#x20;
2. Network Load Balancer (Performance Load Balancer). Operating at the connection level (Layer 4) on the OSI Model, Network Load Balancers are capable of handling millions of requests per second, while maintaining ultra-low latencies.&#x20;
3. Gateway Load Balancer (Or inline appliance load balancing). Operating at the network level of the OSI Model (Layer 3).
4. Classic Load Balancer (Classic/Test/Dev Load Balancer). You can load balance HTTP/HTTPS applications and use Layer 7 specific features, such as X-Forwarded and sticky sessions.


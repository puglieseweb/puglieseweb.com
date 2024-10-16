# Elastic Load Balancing (ELB)

ELB automatically distributes incoming application traffic across multiple targets, such as Amazon EC2 instances. This can be done across multiple AZs.

There are 4 types of Load Balancers:

1. Application Load Balancer (Intelligent Load Balancer). Best suited for load balancing of HTTP and HTTPS Traffic. They operate at Layer 7 and are application aware.&#x20;
2. Network Load Balancer (Performance Load Balancer). Operating at the connection level (Layer 4) on the OSI Model, Network Load Balancers are capable of handling millions of requests per second, while maintaining ultra-low latencies.&#x20;
3. Gateway Load Balancer (Or inline appliance load balancing). Operating at the network level of the OSI Model (Layer 3).
4. Classic Load Balancer (Classic/Test/Dev Load Balancer). You can load balance HTTP/HTTPS applications and use Layer 7 specific features, such as X-Forwarded and sticky sessions.

| Feature                      | Application Load Balancer (ALB)                                      | Network Load Balancer (NLB)                                             | Classic Load Balancer (CLB)                                         |
| ---------------------------- | -------------------------------------------------------------------- | ----------------------------------------------------------------------- | ------------------------------------------------------------------- |
| OSI Layer                    | Layer 7 (Application)                                                | Layer 4 (Transport)                                                     | Layer 4 & 7                                                         |
| Protocols                    | HTTP, HTTPS, gRPC, WebSocket                                         | TCP, UDP, TLS                                                           | TCP, SSL/TLS, HTTP, HTTPS                                           |
| Performance                  | Good                                                                 | Best                                                                    | Good                                                                |
| Latency                      | \~400 ms                                                             | \~100 ms                                                                | \~400 ms                                                            |
| Static IP                    | ❌                                                                    | ✅                                                                       | ❌                                                                   |
| Elastic IP                   | ❌                                                                    | ✅                                                                       | ❌ (Only for CLB in EC2-Classic)                                     |
| Connection Draining          | ✅ (Deregistration Delay)                                             | ✅ (Deregistration Delay)                                                | ✅                                                                   |
| Health Checks                | HTTP, HTTPS, TCP                                                     | TCP, HTTP, HTTPS                                                        | TCP, HTTP, HTTPS, SSL                                               |
| Sticky Sessions              | ✅                                                                    | ✅                                                                       | ✅                                                                   |
| Path/Host-based Routing      | ✅                                                                    | ❌                                                                       | ❌                                                                   |
| URL Redirects                | ✅                                                                    | ❌                                                                       | ❌                                                                   |
| Fixed Response               | ✅                                                                    | ❌                                                                       | ❌                                                                   |
| Websocket Support            | ✅                                                                    | ✅                                                                       | Limited                                                             |
| HTTP/2 Support               | ✅                                                                    | ❌                                                                       | ❌                                                                   |
| gRPC Support                 | ✅                                                                    | ❌                                                                       | ❌                                                                   |
| SSL Offloading               | ✅                                                                    | ✅                                                                       | ✅                                                                   |
| Server Name Indication (SNI) | ✅                                                                    | ✅                                                                       | ❌                                                                   |
| User Authentication          | ✅                                                                    | ❌                                                                       | ❌                                                                   |
| AWS WAF Integration          | ✅                                                                    | ❌                                                                       | ❌                                                                   |
| Cross-zone Load Balancing    | Always on (Can't be disabled)                                        | Disabled by default                                                     | Disabled by default for EC2-Classic, Enabled by default for EC2-VPC |
| Target Types                 | IP addresses, EC2 instances, Lambda functions, Containers            | IP addresses, EC2 instances                                             | EC2 instances                                                       |
| Use Case                     | HTTP/HTTPS applications, Microservices, Container-based applications | TCP/UDP applications, Extreme performance needs, Static IP requirements | Legacy applications in EC2-Classic                                  |

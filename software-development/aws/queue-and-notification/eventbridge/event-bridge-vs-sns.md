# Event Bridge VS SNS

[https://lumigo.io/blog/5-reasons-why-you-should-use-eventbridge-instead-of-sns/](https://lumigo.io/blog/5-reasons-why-you-should-use-eventbridge-instead-of-sns/)



Key differentiators:

* EventBridge offers richer filtering and routing capabilities
* SNS provides simpler, straightforward pub/sub with lower latency
* EventBridge is better for AWS service integrations and scheduling
* SNS is better for high-throughput messaging with multiple subscribers

In many cases, they can be used together - EventBridge can publish to SNS topics, creating a powerful combination of routing and messaging capabilities.

Here's a detailed comparison between Amazon EventBridge and Amazon SNS:

| Feature                   | Amazon EventBridge                                                 | Amazon SNS                                                |
| ------------------------- | ------------------------------------------------------------------ | --------------------------------------------------------- |
| Primary Purpose           | Event routing and serverless event bus                             | Pub/sub messaging and fanout                              |
| Event/Message Filtering   | Rich JSON pattern matching and content-based filtering             | Basic message filtering with subscription filter policies |
| Maximum Message Size      | 256KB                                                              | 256KB                                                     |
| Message Persistence       | No built-in persistence (stateless)                                | No built-in persistence (unless using DLQ)                |
| Delivery Retry            | Yes, configurable retry policy                                     | Yes, configurable retry policy                            |
| Dead Letter Queue Support | Yes                                                                | Yes                                                       |
| Cross-Account Delivery    | Yes, through event bus policies                                    | Yes, through topic policies                               |
| Cross-Region Delivery     | Yes                                                                | Yes                                                       |
| Max Targets/Subscribers   | 5 targets per rule                                                 | 12.5 million subscriptions per topic                      |
| Schema Registry           | Yes, supports schema discovery and management                      | No                                                        |
| Default Event Sources     | AWS services, SaaS providers, custom applications                  | Custom applications, AWS services                         |
| Pricing Model             | Pay per event published                                            | Pay per API request and data transfer                     |
| Order Guarantee           | No guaranteed ordering                                             | No guaranteed ordering                                    |
| Target Types              | More target types (Lambda, SQS, Step Functions, API Gateway, etc.) | Fewer target types (HTTP/S, Email, SMS, SQS, Lambda)      |
| Latency                   | Typically higher (ms range)                                        | Lower (μs to ms range)                                    |
| Archive & Replay          | Yes, supports event archiving and replay                           | No native archive/replay                                  |



Use EventBridge when:

* You need event filtering based on content/patterns
* You want to schedule tasks (cron jobs)
* You need to handle events from AWS services automatically
* You want to build event-driven architectures with complex routing
* You need cross-account event delivery
* You want to integrate with SaaS applications
* You need to transform event content before delivery

Use SNS when:

* You need simple pub/sub messaging
* You want direct integrations with other AWS services
* You need fanout to multiple subscribers
* You need delivery retries and DLQ support
* You require message filtering based on attributes only
* You need message attributes and batch processing
* You want raw message delivery
* You need FIFO messaging with ordering guarantees

# Event Bridge VS SNS

[https://lumigo.io/blog/5-reasons-why-you-should-use-eventbridge-instead-of-sns/](https://lumigo.io/blog/5-reasons-why-you-should-use-eventbridge-instead-of-sns/)



Key differentiators:

* EventBridge offers richer filtering and routing capabilities
* SNS provides simpler, straightforward pub/sub with lower latency
* EventBridge is better for AWS service integrations and scheduling
* SNS is better for high-throughput messaging with multiple subscribers

In many cases, they can be used together - EventBridge can publish to SNS topics, creating a powerful combination of routing and messaging capabilities.



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

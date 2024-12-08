# EventBridge (Event Router)

EventBridge is an Event router that routes events based on rules.

AWS EventBridge is a **serverless event bus** service provided by Amazon Web Services (AWS). It's designed to facilitate event-driven communication between applications. Here's a comprehensive overview:

Key Features:

1. Event Routing
   * Routes events from AWS services, SaaS applications, and custom applications to target services
   * Supports up to 10,000 events/second per account
   * **Maximum event size of 256KB**
2. Serverless Architecture
   * Fully managed service with no infrastructure to maintain
   * Auto-scaling based on event volume
   * Pay-per-event pricing model
3. Event Pattern Matching
   * Content-based filtering using JSON-based event patterns
   * Support for exact value matching and content filtering
   * Prefix and suffix matching capabilities
   * Numeric and IP address range comparisons
4. Target Management
   * Multiple targets per rule (up to 5 targets per rule)
   * Supported targets include AWS services (Lambda, SNS, SQS, Step Functions)
   * API destinations for HTTP endpoints
   * Retry policies and dead-letter queues for failed deliveries
5. Event Buses
   * Default event bus for AWS services
   * Custom event buses for application-specific events
   * Partner event buses for SaaS integration
   * Resource-based policies for access control

<figure><img src="../../../../.gitbook/assets/image (1).png" alt=""><figcaption></figcaption></figure>

1. Schema Registry
   * Automatic schema inference from events
   * Schema versioning support
   * Code bindings generation for popular programming languages
   * OpenAPI schema support
2. Integration Capabilities
   * Native integration with 100+ AWS services
   * SaaS provider integration (e.g., Zendesk, DataDog)
   * Custom integration via API destinations
   * Support for CloudFormation and Infrastructure as Code
3. Cross-Account Features
   * Event sharing across AWS accounts
   * Organization-wide event management
   * Resource-based permissions for fine-grained access control

Common Use Cases:

* Microservices communication
* Application monitoring and alerting
* Workflow automation
* SaaS integration
* Audit and compliance tracking
* IoT data processing
* Real-time analytics triggers

This service is fundamental for building decoupled, event-driven architectures that need reliable event delivery with sophisticated routing and filtering capabilities.



### Delivery of events

1. Event Delivery:

* EventBridge attempts to deliver the event to the target
* The target service can reject/fail to process the event
* By default, EventBridge will retry failed deliveries for up to 24 hours

2. Error Handling Options:

```yaml
yamlCopy# Example EventBridge rule with DLQ configuration
EventRule:
  Type: AWS::Events::Rule
  Properties:
    DeadLetterConfig:
      Arn: !GetAtt DLQueue.Arn
    Targets:
      - Id: "MyLambdaTarget"
        Arn: !GetAtt MyLambda.Arn
        RetryPolicy:
          MaximumRetryAttempts: 3
          MaximumEventAgeInSeconds: 3600
```

3. You can configure:

* Dead Letter Queues (DLQ) for failed events
* Retry policies (attempts and duration)
* Maximum event age
* Custom error handling in target services

4. Common Target Responses:

* Lambda functions can throw errors
* SQS queues might be full
* API endpoints might return error codes
* State Machines might fail execution

Best Practices:

1. Always configure DLQs for important events
2. Implement idempotent processing in targets
3. Monitor failed deliveries and DLQ metrics
4. Set appropriate retry policies based on your use case



### Example

<figure><img src="../../../../.gitbook/assets/image (1) (1).png" alt=""><figcaption></figcaption></figure>

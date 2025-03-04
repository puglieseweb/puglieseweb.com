# Event-Driven Architecture

see [https://serverlessland.com/](https://serverlessland.com/).



Event-driven architecture (EDA) is an architectural pattern that enables the integration of serverless services through asynchronous event triggering across AWS environments. This approach promotes scalability, cost efficiency, and alignment with the Well-Architected Framework.

<figure><img src="../../../../.gitbook/assets/image (38) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Understanding Events

An event represents a significant occurrence or fact that has happened at a specific moment in time within a system. Specifically, it is:

* **A record of something that has occurred in the past** - it is immutable and represents a fact that cannot be changed.
* **A notification that captures what changed in the system,** including relevant details about what happened, when it happened, and any associated data.
* A message that interested parties (event consumers) can use to **react to the change, make decisions, update their own state, trigger workflows, or maintain data consistency across distributed systems.**

<figure><img src="../../../../.gitbook/assets/image (31) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Key Services in Event-Driven Architecture

<figure><img src="../../../../.gitbook/assets/image (32) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. AWS Lambda
   * Serverless compute platform
   * Executes custom logic
   * Core component for event processing
2. Amazon EventBridge
   * Central event bus
   * Choreographs asynchronous events
   * Implements event filtering and routing
3. AWS Step Functions
   * Orchestrates workflows
   * Manages service integration
   * Handles state management
4. Amazon SQS (Simple Queue Service)
   * Event buffering
   * Workflow decoupling
   * Message queuing
5. Amazon SNS (Simple Notification Service)
   * Event notification
   * Supports service and human subscribers
   * Push-based messaging
6. Amazon API Gateway
   * Handles external event triggers
   * Front-end integration
   * API management
7. Amazon DynamoDB
   * Serverless NoSQL database
   * Optimized for event-driven systems
   * Scalable data storage
8. Amazon S3
   * Object storage
   * Event emission capabilities
   * Trigger-based integrations

### Orchestration vs. Choreography

#### Orchestration

* Managed by **AWS Step Functions**
* Handles coupled workflows
* Supports decision trees and retries
* Maintains workflow state

<figure><img src="../../../../.gitbook/assets/image (35) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Choreography

* Managed by EventBridge
* Asynchronous coordination
* Multiple producers and consumers
* Loose coupling
* Can include orchestrated components

<figure><img src="../../../../.gitbook/assets/image (33) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Real-World Implementation Example

#### Order Processing Workflow

<figure><img src="../../../../.gitbook/assets/image (37) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. Front-end Application
   * Initiates new order
   * Connects via API Gateway
2. Event Processing
   * Lambda function processes initial request
   * EventBridge routes based on rules
   * Step Functions determine customer status
3. Order Fulfillment
   * Dedicated Step Function workflow
   * Processes order details
   * Triggers completion events
4. Front-end Updates
   * Lambda function handles updates
   * IoT Core manages WebSocket connections
   * Real-time user notification

### Exam Considerations

1. EventBridge Capabilities
   * Asynchronous event choreography
   * Rule-based event routing
   * Service integration
2. Step Functions Use Cases
   * State management
   * Idempotency requirements
   * Dependent event handling
3. Solution Design
   * Serverless for new workloads
   * Integration of main players
   * Well-Architected Framework alignment

### Best Practices

1. Service Selection
   * Choose appropriate serverless services
   * Consider scaling requirements
   * Evaluate cost implications
2. Architecture Design
   * Implement loose coupling
   * Plan for asynchronous operations
   * Design for scalability
3. Event Management
   * Define clear event patterns
   * Implement proper error handling
   * Monitor event flows

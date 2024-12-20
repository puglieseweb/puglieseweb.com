# Lambda

Example architecture useing Lambda

<figure><img src="../../../../.gitbook/assets/image (6) (1) (1).png" alt=""><figcaption></figcaption></figure>





SAM is sort of like a front end for CloudFormation:

<figure><img src="../../../../.gitbook/assets/image (3) (1) (1).png" alt=""><figcaption></figcaption></figure>

For Multicloud architecture use Serverless Framework rather then AWS SAM:

<figure><img src="../../../../.gitbook/assets/image (4) (1) (1).png" alt=""><figcaption></figcaption></figure>

Amazon EventBridge is designed to link variety of AWS and 3rd party apps to rules logic for launching other event-based actions.

<figure><img src="../../../../.gitbook/assets/image (5) (1) (1).png" alt=""><figcaption></figcaption></figure>

### AWS Lambda Overview

AWS Lambda is a cornerstone of AWS's serverless architecture, enabling code execution without managing infrastructure. This service represents a fundamental shift in cloud computing, focusing on functions rather than traditional application hosting.

#### Key Characteristics

* On-demand code execution
* No infrastructure management required
* Stateless execution model
* Automatic scaling capabilities
* Pay-per-use pricing model

#### Supported Languages

* Node.js
* Python
* Java
* Go
* C#
* Custom runtime support for additional languages

#### Integration Capabilities

* Amazon SNS
* Amazon SQS
* Amazon S3
* DynamoDB
* Various other AWS services

### Architectural Patterns

#### Fan-Out Architecture

A common Lambda implementation pattern that enables parallel processing:

1. Initial trigger receives data/instruction
2. Primary Lambda function initiates multiple parallel functions
3. Parallel functions execute independently
4. Enables efficient distributed processing

#### State Management

* Lambda functions are inherently stateless
* State must be managed through external services
* Common state storage options:
  * DynamoDB
  * S3
  * Other AWS persistence services

### AWS Serverless Application Model (SAM)

#### Overview

* Open-source framework for serverless development
* Simplifies serverless application deployment
* Acts as a front-end for CloudFormation
* Uses YAML for configuration

#### Key Features

1. **Local Testing**
   * Test applications before cloud deployment
   * Local debugging capabilities
   * Simplified development workflow
2. **CLI Tools**
   * Command-line interface for management
   * Deployment automation
   * Testing utilities
3. **CloudFormation Integration**
   * Generates CloudFormation templates
   * Manages AWS resource creation
   * Ensures consistent deployments

#### Example SAM Template Structure

```yaml
Runtime: nodejs14.x
Handler: index.handler
Function: ImageProcessor
Properties:
  Memory: 128
  Timeout: 30
```

#### Development Benefits

* Streamlined deployment process
* Local testing capabilities
* Built-in best practices
* AWS service integration

### Amazon EventBridge

#### Purpose

* Event-driven architecture enablement
* Third-party service integration
* AWS service coordination

#### Key Features

1. **Integration Capabilities**
   * Pre-integrated third-party services
   * AWS service connections
   * Custom application support
2. **Event Processing**
   * Rule-based event routing
   * Event pattern matching
   * Target action execution
3. **Use Cases**
   * Service integration
   * Automated workflows
   * Event-driven applications

#### Common Integrations

* Zendesk ticket creation
* Lambda function triggers
* SNS notifications
* Custom application events

### Serverless Framework Comparison

#### AWS SAM

* AWS-specific focus
* Native AWS integration
* Simplified AWS deployment
* CloudFormation-based

#### Serverless Framework

* Multi-cloud support
* Broader provider options
* Platform-agnostic approach
* Extended functionality

### Best Practices

#### Architecture Design

* Embrace stateless design
* Plan for parallel execution
* Consider integration points
* Implement proper error handling

#### Development Workflow

* Utilize local testing
* Implement proper monitoring
* Consider cold start impacts
* Manage function dependencies

#### Cost Optimization

* Monitor execution duration
* Optimize memory allocation
* Consider concurrent execution
* Implement proper timeout values

### Exam Considerations

#### Key Points

* Understanding of Lambda concepts
* Knowledge of serverless architecture
* Familiarity with integration options
* Awareness of scaling capabilities

#### Focus Areas

* Service integration possibilities
* Architectural patterns
* State management approaches
* Development tool options

### Additional Resources

* AWS SAM documentation
* Pre-made serverless applications
* AWS Lambda documentation
* EventBridge integration guides

Note: While detailed Lambda implementation knowledge isn't required for the exam, understanding its role in serverless architectures and integration capabilities is essential.

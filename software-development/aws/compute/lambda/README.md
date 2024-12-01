# Lambda

Write your code, build your function, and that's it! Lambda functions are isolated pieces of code that run very specific tasks.

## Networking

Lambda can run inside or outside a VPC (default). If you need to access a private resource (e.g., RDS), you must configure a VPC.

## Pricing

Free tier includes 1,000,000 requests and 400,000 GB-seconds of compute per month. After that, pay per request.

## Key Features

### Integrations

Integrates with numerous AWS services, including S3, DynamoDB, EventBridge, SQS/SNS, and Kinesis.

### Monitoring

Logging and monitoring through Amazon CloudWatch.

### Resource Configuration

Memory configurable from 128 MB to 10,240 MB in 1 MB increments. CPU scales proportionally with memory allocation, up to 6 vCPUs at maximum memory.

### Execution Time

Time limit of 900 seconds (**15 minutes)**. For longer workloads, use ECS, Batch, or EC2.

### Runtime Support

Supports industry-standard languages including Python, Go, Java, Node.js, and others.

### Triggers

Lambda functions can be triggered by:

* Scheduled events using EventBridge
* HTTP requests
* Changes in S3 buckets
* Messages in SNS/SQS queues
* And more

## Use Cases

* Simple data processing
* Quick file manipulation
* Real-time data analysis
* Automated operations tasks (e.g., shutting down EC2 instances)

## Storage Options

### Temporary Storage (/tmp)

* Default size: 512 MB
* Location: /tmp directory
* Lifecycle: Available only during function execution
* Use cases: Temporary file operations, caching, intermediate results
* Performance: Faster than network storage, slower than memory
* Alternative: Use S3 or EFS for larger or persistent storage needs

## Quotas and Limits

### Compute and Storage

* 1,000 concurrent executions (default, increasable via quota request)
* 512 MB - **10 GB ephemeral disk storage (/tmp)**
* EFS integration available with VPC configuration
* 4 KB total size limit for environment variables
* 128 MB - **10,240 MB memory allocation**
* **15-minute maximum execution** time for synchronous invocations

### Deployment

* Compressed package (zip) ≤ 50 MB
* Uncompressed package ≤ 250 MB (use S3 for larger packages)
* Request/response payload ≤ 6 MB
* Streamed responses ≤ 20 MB

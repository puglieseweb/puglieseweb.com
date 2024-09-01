# Lambda

Write your code, build your function, and that's it!



Lambda function are isolated pieces of code that run a very specific tasks.

Lambda can run inside or outside a VPC (default). Note that if you need to access a private resource (e.g. RDS) we must configure a VPC.

### Prising

Free tier of 1,000,000 requests and 400,000 GBs of compute per month. After that, pay per request.



### Integrations

Integrates with numerous AWS servcies, including S3, DynameDB, EventBridge, SQS/SNS/ and Kinesis.

### Build-in Monitoring

Logging and monitoring can be easily accomplished using Amazon ColudWatch

### Easy Configuration

Easily set memory requirements as needed. Currently able to use up to 10,240 MB! **CPU scales with memory.**



### **Execution Length**

**Used for short-term execution. Time limit of 900 seconds (15 minutes). For anything longer use, ECS, Batch, or EC2!"**

### **Runtime Support**

Leverage industry-dominant languages including Python, Goland, Java, Node.js, and others!

### Triggers

Lambda function needs to be triggered:

* by Scheduled event using event bridge,&#x20;
* HTTP requests
* change in an S3 Bucket,&#x20;
* message in SNS queue
* etc.&#x20;

### Use Cases

* Simple Data processing
* Quick file manipulation
* Some realtime data analysis&#x20;
* automating operations tasks (e.g. shout down EC2 instances at the end of the day)

### Quotas&#x20;

* Compute and Storage Quota:&#x20;
  * 1,000 concurrent executions;&#x20;
  * 512MB - 10 GB disk storage (/tmp)
  * Integration with EFS if needed (note that this requires VPC configuration)
  * 4KB for all environment variables
  * 128MB - 10GB memory allocation&#x20;
  * can run up to 900 seconds
* Deployments and Configuration:
  * Compressed deployment package (.zip) size must be <= 50 MB
  * Uncompressed deployment package (unzipped) must be <= 250 MB. If you have a big deployment package we should upload it to S3 bucket ant reference it from the lum
  * Request and response payload sizes up to 6 MB
  * Streamed responses up to 20 MB

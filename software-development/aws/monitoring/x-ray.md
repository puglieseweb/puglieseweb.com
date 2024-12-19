# X-Ray

<figure><img src="../../../.gitbook/assets/image (29) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

* App Insights: Collects application data for viewing, filtering, and gaining **insights about requests and responses.**
* Downstream. View calls to downstream AWS resources and other microservicers/APIs or databases
* Traces. Receives traces from applications&#x20;
* Multiple Options. Can add tracing headers, send trace data, or run the X-Ray deamon

Key concepts:

* Segments: Data containing resource names, request details, and other information
* Subsegments: Segments providing more granular timing information and details&#x20;
* Service graph: Graphical representation of interacting services in requests
* Traces: Trace ID tracks paths of requests and traces collect all segments in a request.&#x20;
* Tracing headers: Extra HTTP headers containing sampling decisions and trace ID
* Tracing header containing added information is named **X-Amzn-Trace-Id**

### **AWS X-Ray Deamon**

AWS software application that listens on **UDP port 2000**. It collects raw segment data and sends it to the AWS X-Ray API.&#x20;

When the deamon is running, it works along with the AWS X-Ray SDKs&#x20;

The Deamon can be run on:

* EC2 instances
* Amazon ECS
* AWS Lambda
* Elastic Beanstalk
* API Gateway
* SNS and SQS&#x20;


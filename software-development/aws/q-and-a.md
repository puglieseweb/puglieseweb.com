# Q\&A

## What are the availability SLAs for the different AWS Application SLAs?&#x20;

| Service           | Monthly Region-Level Availability SLA | Monthly Instance-Level (or equivalent) Availability SLA |
| ----------------- | ------------------------------------- | ------------------------------------------------------- |
| EC2               | 99.99%                                | 99.5% for individual EC2 instances                      |
| ECS               | 99.99%                                | No specific instance-level SLA                          |
| EKS               | 99.95%                                | No specific instance-level SLA                          |
| Elastic Beanstalk | No specific SLA published             | No specific instance-level SLA                          |
| App Runner        | No specific SLA published             | No specific instance-level SLA                          |
| Lambda            | 99.95%                                | No specific instance-level SLA (serverless)             |

## What are the difference between Beanstalk and App Runner?

Both AWS Elastic Beanstalk and AWS App Runner are managed services designed to simplify application deployment, but they have some key differences:

1. Level of abstraction:
   * Elastic Beanstalk: Provides a platform-as-a-service (PaaS) environment with more control over the underlying infrastructure.
   * App Runner: Offers a higher level of abstraction, focusing on a source-to-service experience with less infrastructure management
2. Infrastructure control:
   * Elastic Beanstalk: Allows more fine-grained control over EC2 instances, load balancers, and other AWS resources.
   * App Runner: Abstracts away most infrastructure details, managing them automatically.
3. Scaling:
   * Elastic Beanstalk: Offers both time-based and metric-based auto-scaling options.
   * App Runner: Provides automatic scaling based on incoming traffic, with less configuration required.
4. Deployment options:
   * Elastic Beanstalk: Supports various deployment strategies (rolling, immutable, blue/green) and can deploy from multiple sources (ZIP, Docker, Git).
   * App Runner: Primarily designed for deploying from source code repositories or container registries.
5. Language/framework support:
   * Elastic Beanstalk: Supports a wide range of programming languages and frameworks through preconfigured platforms.
   * App Runner: Initially focused on containerized applications, but now also supports source code deployments for specific runtimes.
6. Customization:
   * Elastic Beanstalk: Offers extensive customization options through configuration files and extension hooks.
   * App Runner: Provides fewer customization options, focusing on simplicity and rapid deployment.
7. Use cases:
   * Elastic Beanstalk: Better suited for applications that require more control over the infrastructure or have complex deployment requirements.
   * App Runner: Ideal for simpler web applications, microservices, or APIs that benefit from a streamlined deployment process.
8. Pricing model:
   * Elastic Beanstalk: You pay for the AWS resources used (e.g., EC2 instances, load balancers).
   * App Runner: Charges based on the compute and memory resources consumed by your application, with a simpler pricing structure.

In summary, Elastic Beanstalk offers more control and flexibility but requires more configuration, while App Runner provides a simpler, more automated experience with less customization. The choice between them often depends on your application's complexity, your team's expertise, and your specific requirements for infrastructure control.

## What are the difference between Beanstalk and ECS?

Amazon Elastic Beanstalk:

1. Abstraction level: Higher-level, Platform as a Service (PaaS)
2. Ease of use: Simpler, more automated
3. Application types: Supports various programming languages and web servers
4. Container support: Can deploy containerized applications, but not its primary focus
5. Scaling: Automatic scaling based on predefined rules
6. Infrastructure management: Handles most infrastructure details automatically

Amazon Elastic Container Service (ECS):

1. Abstraction level: Lower-level, container orchestration service
2. Ease of use: More complex, offers greater control
3. Application types: Specifically designed for containerized applications
4. Container support: Native support for Docker containers
5. Scaling: Manual or automatic scaling with more granular control
6. Infrastructure management: Requires more hands-on management

Key differences:

1. Purpose: Beanstalk is for easy deployment of web applications, while ECS is specifically for container orchestration.
2. Flexibility: ECS offers more flexibility and control over your container infrastructure, while Beanstalk abstracts away many details for simplicity.
3. Learning curve: Beanstalk has a gentler learning curve, while ECS requires more in-depth knowledge of containerization and orchestration.
4. Integration: ECS integrates more deeply with other AWS services for container management, like ECR (Elastic Container Registry).
5. Use cases: Beanstalk is ideal for simple web applications and developers who want to focus on code. ECS is better for complex, containerized microservices architectures.

## What are the Application Server Options for AWS?

1. **AWS Lambda (Serverless)**
   * Language support: Node.js, Python, Java, Go, .NET, Ruby
   * Pros:
     * Serverless, no infrastructure management
     * Auto-scaling
     * Pay only for compute time used
   * Cons:
     * Cold starts can increase latency
     * Limited execution time (15 minutes max)
   * Best for: Event-driven, sporadic workloads, microservices
2. **Amazon EC2 (Traditional)**
   * EC2, which offers a 99.99% availability SLA
   * Language support: Any (you have full control over the instance)
   * Pros:
     * Full control over the environment
     * Wide range of instance types for different needs
     * Can run any web server (e.g., Nginx, Apache) or application server (e.g., Tomcat, Jetty)
   * Cons:
     * Requires more management and configuration
     * You're responsible for scaling and maintenance
   * Best for: Applications requiring specific configurations, high-performance computing
3. **Amazon ECS (Container-based)**
   * Language support: Any (containerized applications)
   * Pros:
     * Easy container orchestration
     * Works well with Docker
     * Integrates with other AWS services
   * Cons:
     * Steeper learning curve if you're new to containers
     * More complex than serverless options
   * Best for: Microservices architectures, applications already containerized
4. **AWS Elastic Beanstalk**
   * Language support: Java, .NET, PHP, Node.js, Python, Ruby, Go, Docker
   * Pros:
     * Easy to use, handles deployment details
     * Auto-scaling and load balancing built-in
     * Supports multiple platforms
   * Cons:
     * Less flexible than managing your own EC2 instances
     * May not be suitable for very complex applications
   * Best for: Web applications, developers who want to focus on code rather than infrastructure
5. **AWS App Runner**
   * Language support: Any (via containers)
   * Pros:
     * Fully managed
     * Automatic scaling and load balancing
     * Simple to use, quick to deploy
   * Cons:
     * Less customizable than ECS or EC2
     * Newer service, so fewer features than more established options
   * Best for: Web applications, APIs, microservices, especially for teams wanting simplicity

## Can I store images and movies in DynamoDB?

While DynamoDB can technically store binary data like movies or pictures, it's generally not recommended for several reasons:

1. Size limitations:
   * As we discussed earlier, DynamoDB has a maximum item size of 400 KB.
   * Most movies and high-quality images easily exceed this limit.
2. Cost and performance:
   * DynamoDB charges based on the amount of data stored and read/write capacity units used.
   * Storing large binary files would be expensive and inefficient.
   * Querying and retrieving large binary objects would be slow and consume a lot of read capacity.
3. Lack of streaming support:
   * DynamoDB doesn't support efficient streaming of binary data, which is crucial for video playback.

Instead, here's the recommended approach for storing movies or pictures in conjunction with DynamoDB:

1. Use Amazon S3 (Simple Storage Service):
   * S3 is designed for storing and retrieving large objects like movies and images.
   * It's much more cost-effective for this purpose.
   * S3 supports efficient streaming for video content.
2. Store metadata in DynamoDB:
   * Use DynamoDB to store metadata about your movies or pictures.
   * This could include filenames, upload dates, user IDs, tags, etc.
   * Also store the S3 object key (essentially the file path in S3) in DynamoDB.

Here's a simple example of how you might structure this:

```json
{
  "TableName": "UserMedia",
  "KeySchema": [
    { "AttributeName": "UserId", "KeyType": "HASH" },
    { "AttributeName": "MediaId", "KeyType": "RANGE" }
  ],
  "AttributeDefinitions": [
    { "AttributeName": "UserId", "AttributeType": "S" },
    { "AttributeName": "MediaId", "AttributeType": "S" }
  ],
  "Items": [
    {
      "UserId": "user123",
      "MediaId": "photo1",
      "Type": "image",
      "S3Key": "users/user123/photos/photo1.jpg",
      "UploadDate": "2024-10-12T14:30:00Z",
      "FileSize": 2500000,
      "ContentType": "image/jpeg",
      "Tags": ["vacation", "beach"]
    },
    {
      "UserId": "user123",
      "MediaId": "video1",
      "Type": "video",
      "S3Key": "users/user123/videos/video1.mp4",
      "UploadDate": "2024-10-12T15:45:00Z",
      "FileSize": 150000000,
      "ContentType": "video/mp4",
      "Duration": 180,
      "Tags": ["family", "birthday"]
    }
  ]
}

```

In this structure:

1. The `UserMedia` table uses a composite key of `UserId` and `MediaId`.
2. Each item contains metadata about the media file, including its location in S3 (`S3Key`).
3. The actual media files (images, videos) are stored in S3, not in DynamoDB.

This approach offers several benefits:

* Efficient querying: You can quickly find all media for a user or specific pieces of media.
* Scalability: S3 handles the large files, while DynamoDB manages the lightweight metadata.
* Cost-effective: You're not paying DynamoDB prices for storing large binary data.
* Performance: Retrieving metadata is fast, and S3 is optimized for serving large files.

To use this setup in an application:

1. To upload: Save the file to S3, then create a metadata entry in DynamoDB.
2. To retrieve: Query DynamoDB for the metadata, then use the S3 key to fetch the file from S3.

This pattern is commonly used in applications dealing with user-generated content, media libraries, and similar scenarios where you need to manage large files along with searchable metadata.





## What are the differences between EMR and ML (SageMaker)?



Amazon SageMaker and Amazon EMR (Elastic MapReduce) are both AWS services designed for data processing and machine learning, but they serve different primary purposes and use cases. Let's break down the key differences:

1. Primary Purpose:
   * SageMaker: Primarily designed for end-to-end machine learning workflows.
   * EMR: Primarily designed for big data processing and analytics using open-source tools.
2. Core Functionality:
   * SageMaker: Focuses on building, training, and deploying machine learning models.
   * EMR: Focuses on processing vast amounts of data using distributed computing frameworks.
3. Tools and Frameworks:
   * SageMaker: Integrates various ML frameworks (TensorFlow, PyTorch, etc.) and provides its own optimized algorithms.
   * EMR: Supports Apache Hadoop ecosystem tools (Spark, Hive, HBase, etc.).
4. Ease of Use:
   * SageMaker: Provides a more managed experience for ML workflows, with built-in algorithms and model optimization.
   * EMR: Requires more hands-on configuration and management of the cluster and applications.
5. Scalability:
   * SageMaker: Automatically manages infrastructure for training and deployment, scaling based on workload.
   * EMR: Allows manual configuration of cluster size and instance types, with some automatic scaling options.
6. Use Cases:
   * SageMaker: Best for developing, training, and deploying ML models, especially for teams focused on data science.
   * EMR: Ideal for big data processing, ETL jobs, log analysis, and running large-scale analytics.
7. Data Processing:
   * SageMaker: Focused on processing data specifically for machine learning tasks.
   * EMR: Can handle a wider variety of big data processing tasks beyond just ML.
8. Deployment:
   * SageMaker: Provides built-in options for deploying models as endpoints for real-time or batch inference.
   * EMR: Typically used for batch processing, though can be used to serve models with additional setup.
9. Pricing Model:
   * SageMaker: Charges based on the resources used for notebook instances, training, and model hosting.
   * EMR: Charges based on the EC2 instances used in the cluster, plus a per-second charge for EMR features.
10. Integration with Other AWS Services:
    * SageMaker: Tightly integrated with other AWS ML services like Comprehend, Rekognition, and Forecast.
    * EMR: Well-integrated with data storage services like S3 and analytics services like Redshift.

Here's a quick comparison table:



| Feature               | Amazon SageMaker                          | Amazon EMR                                     |
| --------------------- | ----------------------------------------- | ---------------------------------------------- |
| Primary Purpose       | Machine Learning workflows                | Big Data processing and analytics              |
| Core Functionality    | Building, training, deploying ML models   | Distributed data processing                    |
| Key Tools             | Built-in ML algorithms, Jupyter notebooks | Hadoop, Spark, Hive, HBase                     |
| Ease of Use           | More managed, ML-focused                  | More configurable, requires more expertise     |
| Scalability           | Automatic for ML workloads                | Manual configuration with some auto-scaling    |
| Typical Use Cases     | Developing and deploying ML models        | ETL, log analysis, large-scale data processing |
| Data Processing Focus | ML-specific data preparation              | Wide variety of big data tasks                 |
| Model Deployment      | Built-in deployment options               | Requires additional setup for model serving    |
| Pricing Model         | Based on resources used for ML tasks      | Based on EC2 instances + EMR features          |
| AWS Integration       | ML services (Comprehend, Rekognition)     | Data services (S3, Redshift)                   |

SageMaker vs EMR ComparisonClick to open document

In summary, while there's some overlap in capabilities, SageMaker is generally the better choice if your primary focus is on machine learning workflows, especially if you want a more managed experience. EMR is more suitable for general big data processing tasks, especially if you're already familiar with and want to use Hadoop ecosystem tools.

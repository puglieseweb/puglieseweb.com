# Sagemaker (part of the exam)

## Core Concepts

Amazon SageMaker is a fully managed machine learning platform that enables developers and data scientists to build, train, and deploy machine learning models quickly.

Key Components:

1. Ground Truth
2. Notebook Instances
3. Training
4. Deployment

## Detailed Components

### 1. SageMaker Ground Truth

* Purpose: Create high-quality training datasets
* Features:
  * Manage labeling jobs
  * Use active learning to reduce labeling costs
  * Support for human labeling and automated labeling

### 2. SageMaker Notebook Instances

* Managed Jupyter notebook environment
* Pre-configured with machine learning libraries and frameworks
* Easily integrate with other AWS services

### 3. SageMaker Training

* Managed training of machine learning models
* Support for built-in algorithms and custom algorithms
* Distributed training capabilities
* Hyperparameter tuning jobs

### 4. SageMaker Deployment

Two main deployment types:

### a. Online/Real-time Deployment (SageMaker Hosting Services)

* Use case: Synchronous, real-time predictions
* Method: Create and deploy endpoints
* Input/Output: Varies by algorithm, typically JSON for output
* Steps:
  1. Create Model
  2. Create Endpoint Configuration
  3. Create Endpoint
* Invoke using `InvokeEndpoint()` API

### b. Offline/Batch Deployment (SageMaker Batch Transform)

* Use case: Asynchronous predictions on entire datasets
* Method: Batch transform jobs
* Input/Output: Varies by algorithm

### Key Features

1. **Built-in Algorithms**: Pre-built, optimized algorithms for common ML tasks
2. **Hyperparameter Tuning**: Automatic model tuning
3. **Distributed Training**: Train large models across multiple instances
4. **Inference Pipeline**: Chain multiple models for complex workflows
5. **Model Monitoring**: Detect concept drift and data quality issues
6. **SageMaker Studio**: Integrated development environment for ML

### Integration with AWS Services

* S3: Store datasets and model artifacts
* ECR (Elastic Container Registry): Store custom Docker images for training and inference
* IAM: Manage permissions and roles
* CloudWatch: Monitor metrics and logs
* Step Functions: Orchestrate ML workflows



## Deployment Type Summary

&#x20;

<table><thead><tr><th width="204"></th><th>Offline Usage deployment</th><th>Online Usage deployment</th></tr></thead><tbody><tr><td>Usage</td><td>Asynchronous or batch</td><td>Synchronous or real time</td></tr><tr><td>When</td><td>Generate predictions for an entire dataset all at once</td><td>SageMaker hosting service</td></tr><tr><td>Method</td><td>SageMaker batch transform</td><td>SageMaker hosting service</td></tr><tr><td>Input format</td><td>Varies depending on the algorithm</td><td>Varies depending on algorithm</td></tr><tr><td>Output format</td><td>Varies depending on algorithm</td><td>JSON String</td></tr></tbody></table>



### Exam Tips

1. Understand the difference between real-time and batch inference
2. Know the steps to deploy a model for real-time inference
3. Be familiar with SageMaker's built-in algorithms and when to use them
4. Understand how SageMaker integrates with other AWS services
5. Know how to optimize costs in SageMaker (e.g., using Spot Instances for training)

Remember, the SAA-C03 exam may present scenarios where you need to choose the appropriate SageMaker features based on specific machine learning requirements and constraints.

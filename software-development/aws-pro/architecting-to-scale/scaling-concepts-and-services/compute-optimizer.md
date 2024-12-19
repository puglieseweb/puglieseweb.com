# Compute Optimizer

### Overview

<figure><img src="../../../../.gitbook/assets/image (12) (1).png" alt=""><figcaption></figcaption></figure>

Compute Optimizer is a machine learning service designed to optimize AWS compute resources. It serves two primary purposes:

* Improve cost efficiency by identifying over-provisioned resources
* Enhance performance by rightsizing under-provisioned resources
* Can be implemented at single account or organization-wide level

<figure><img src="../../../../.gitbook/assets/image (10) (1).png" alt=""><figcaption></figcaption></figure>

### How It Works

#### Data Collection Process

1. Service requires access to:
   * Compute resources
   * CloudWatch metrics
2. Tracks performance and utilization patterns over time
3. Analyzes resource usage patterns
4. Produces configuration recommendations

### Compatible Resources

<figure><img src="../../../../.gitbook/assets/image (11) (1).png" alt=""><figcaption></figcaption></figure>

1. **EC2 Instances**
   * Rightsizing recommendations
   * Instance type optimization
2. **Auto Scaling Groups**
   * Configuration adjustments
   * Scaling parameter optimization
3. **EBS Volumes**
   * Volume type recommendations
   * Size optimization
4. **ECS on Fargate**
   * Task size optimization
   * Container size recommendations
5. **Lambda Functions**
   * CPU allocation optimization
   * Memory allocation recommendations

### Key Exam Points

1. **Core Functionality**
   * Uses machine learning algorithms
   * Analyzes CloudWatch metrics
   * Provides configuration recommendations
2. **Service Scope**
   * Not limited to EC2
   * Covers multiple compute services
   * Provides comprehensive resource optimization

This service demonstrates AWS's commitment to helping customers optimize their resource usage through automated, intelligent analysis.

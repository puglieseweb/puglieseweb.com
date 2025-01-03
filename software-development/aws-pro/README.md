# AWS Pro

### Defining Serverless

A service is considered serverless when it meets these key criteria:

1. **No Server Management**
   * Provider handles patches and OS updates
   * Example: AWS Lambda
2. **Flexible Scaling**
   * Automatic scaling based on traffic
   * Scaling within defined boundaries
3. **Built-in High Availability**
   * Default fault tolerance
   * Inherent availability features
4. **Scale to Zero** Capability
   * No idle capacity
   * No costs when inactive

#### Note on "Serverless" Services

Some AWS services labeled as "serverless" (e.g., Aurora Serverless, Neptune Serverless) may not fully meet all criteria, particularly zero-scaling. These services represent the best-fit options for serverless applications despite limitations.

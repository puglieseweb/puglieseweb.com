# Fargate

AWS Fargate is a serverless compute engine for docker containers. AWS owns and manages the infrastructure.

* Requires use of ECS or EKS.
* Can use both Linux and Windos containers.&#x20;

Difference between EC2 and Fargate are:

* EC2:
  * You are responsible for underlying operating system
  * EC2 pricing model&#x20;
  * Long-running containers
  * Multiple containers can share the same host
  * Capable of mounting EFS file systems for persistent, shared, storage.
* Fargate:
  * No operating system access
  * Pay based on resources allocated and time ran&#x20;
  * Short-running tasks
  * Isolated environments per containers
  * Capable of mounting EFS file systems for persistent, shared, storage.

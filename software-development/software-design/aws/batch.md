# Batch

1. Batched Workloads: allows to run batch computing workloads within AWS (run on EC2 or ECS/Fargate)&#x20;
2. Remove any heavy lifting for configuration and management of infrastructure
3. Automatically provision and scale
4. No install required



Important Concepts

* Jobs: Units of work that are submitted to AWS Batch (e.g. shell scripts, executables, and Docker images)
* Job Definitions: Specify how jobs are run (blueprint for the resources in the job)
* Job Queues: jobs reside in a queue until they are scheduled for execution in a compute environment.
* Compute Environment: set of managed or unmanaged compute resources used to run jobs

<figure><img src="../../../.gitbook/assets/AWS - Batches.svg" alt=""><figcaption></figcaption></figure>

| Fargate                                 | EC2                                       |
| --------------------------------------- | ----------------------------------------- |
| Recommended approach for MOST workloads | Need more control over instance selection |
| Requires fast start times (<30 seconds) | Elastic Fabric Adapter                    |
| 16 vCPU or less                         | custom AMIs                               |
| no GPUs                                 | GPUs                                      |
| max 120 GiB of memory                   | High levels of cocurrency                 |
|                                         | access to Linux Parameters                |



| AWS Batch                                 | AWS Lambda                                                                                            |
| ----------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| no time limit                             | 15 min execution time                                                                                 |
|                                           | limited native disk space, and EFS requires functions live within a VPC. It addes overhead and costs. |
| Batch uses Docker so can have any runtime | fully serverless with limited runtimes                                                                |



| Batch Managed                                                  | Batch Unmanage                                              |
| -------------------------------------------------------------- | ----------------------------------------------------------- |
| AWS manages capacity and instance types                        | managed your own resouces                                   |
| Compute resource specs are defined when environment is created | AmI must meet ECS AMI specs                                 |
| ECS instances are launched into VPC  subnets                   | you manage everything                                       |
| Default is the most recetn and approved Amazon ECS AMI         | Good choice for extremely complex or specific requirements  |
| You can use own AMI                                            |                                                             |
|                                                                |                                                             |
|                                                                |                                                             |

# Step Functions

[AWS Step Functions](https://aws.amazon.com/step-functions/) are a serverless orchestration service designed for event-driven task execution, enabling the combination of various AWS services for business applications.

### Core Components

#### State Machines

* Represents a workflow with different event-driven steps
* Uses Amazon States Language
* Includes a graphical console and workflow viewer
* Each workflow can have multiple executions (instances of running workflows)

#### States (Steps)

* Represent individual units of work within a workflow
* Each state is referenced by a unique name
* Can make decisions based on input, perform actions, or pass output

### Workflow Types





| **Feature**         | **Standard Workflows**                         | **Express Workflows (Async)**                          | **Express Workflows (Sync)**                             |
| ------------------- | ---------------------------------------------- | ------------------------------------------------------ | -------------------------------------------------------- |
| Execution Guarantee | Exactly-once execution                         | At-least-once execution                                | At-least-once execution                                  |
| Duration Limit      | Up to 1 year                                   | Up to 5 minutes                                        | Up to 30 seconds                                         |
| Best Used For       | Long-running workflows requiring audit history | High-volume event processing with eventual consistency | High-volume API orchestration needing immediate response |
| Performance         | Up to 2,000 executions/second                  | Up to 100,000 executions/second                        | Up to 100,000 executions/second                          |
| Use Case Example    | Complex business processes                     | IoT data streaming and ingestion                       | Real-time transaction processing                         |
| Pricing Model       | Per state transition                           | Based on executions, duration, and memory              | Based on executions, duration, and memory                |
| Response Type       | Asynchronous                                   | Asynchronous                                           | Synchronous (waits for workflow completion)              |

The key difference between Async and Sync Express workflows is that synchronous executions wait for the workflow to complete and return the result, while asynchronous executions start the workflow but don't wait for completion. Sync Express workflows also have a shorter duration limit of 30 seconds compared to 5 minutes for async Express workflows.

### State Types

1. **Pass State**
   * Passes input directly to output
2. **Task State**
   * Performs a single unit of work
   * Can integrate with services like Lambda, Batch, and SNS
3. **Choice State**
   * Adds branching logic to the state machine
4. **Wait State**
   * Creates specific time delays
5. **Succeed State**
   * Stops execution successfully
6. **Fail State**
   * Stops execution and marks as failed
7. **Parallel State**
   * Executes multiple branches simultaneously
8. **Map State**
   * Runs steps based on elements of an input array

#### Real-World Example

An online pickup order workflow where each step in the process represents a different state in the state machine.

# Step Functions

* Serveless orchestration service meant for event-driven task execution.&#x20;
* Combining different AWS services for business applications.
* Main components are **state machines** and **tasks.**
* Steps. A step is a state withing a workflow (state machine)
* State Machine. A particular workflow with different event-driven steps.
* Comes with a graphical console and a workflow view flows
* has Tasks. Specific states within a workflow (state machine) representing a single unit of work.

## Workflows types

Each workflow has executions. Execution are instances where you run your workflows in order to execute tasks.&#x20;



| Standard Workflow                                                        | Express Workflow                                                     |
| ------------------------------------------------------------------------ | -------------------------------------------------------------------- |
| Exactly-once execution                                                   | At-least-one workflow execution                                      |
| Can run for up 1 year                                                    | up to 5 minutes                                                      |
| Useful for long-running workflows that need to have an auditable history | Useful for high-event-rate workloads                                 |
| rates up to 2,000 executions per second                                  | Exampel use is IoT data streaming and ingestion                      |
| pricing based per state transition                                       | Pricing based on number of executions, duration, and memory consumed |
|                                                                          |                                                                      |
|                                                                          |                                                                      |

### States and State Machines

* Flexible. States are used to make decisions based on input, perform certain actions, or pass output.
* Use Amazon States Language.&#x20;
* States are elements within the state machine referenced by name

Example can be an online pickup order: Each step in this workflow is considered a state.

### States

Pass state: passes any input directly to its output&#x20;

Task state: Single unit of work performed (e.g. Lambda, Batch, and SNS)

Chose state: Brancing logic to the state machine

Wait state: create a specific time delay within the state machine

Succeed state: Stops executions successfully&#x20;

Fail state: Stop executions and marks them as failures

Parallel state: Runs parallel branches of executions within state machine

Map state: runs a set of steps based on elements of an input array

###

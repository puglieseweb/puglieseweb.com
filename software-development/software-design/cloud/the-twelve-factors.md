---
description: 'Immutable (infrastructure), Ephemeral, Declarative, Automated Applications'
---

# The Twelve Factors

The Twelve Factors provides guidelines on how to implement cloud aware applications. 

Deployment/build process factors:

| Factor  | Description |
| :--- | :--- |
| Codebase | One repository for each application |
| Dependencies | Explicitly declare all dependencies. Do not rely on implicit System-wide packages |
| Config | Things that changes between environments should be separated from the code |
| Backing services |  Attachable resources via a single URL |
| Build, release, run |  Run with one line \(e.g. using a script\) |

Application Architecture related factors:

| Factor  | Description |
| :--- | :--- |
| Processes |  Processes should be stateless. Do not use stick sessions |
| Port Binding |  Should be done by the applications: binding should not relay on external infrastructure |
| Concurrency | Scaling up and scaling out using different processes  |
| Disposable | Quick startup, Resilience to failures, graceful shutdown |
| Dev/Prod Parity | dev = stag = prod  |
| Logs |  Logs should be thread as events not like files. Log everything on the same channel |
| Admin Processes | Run as isolated processes. This is similar to scaling a new container which is isolated from the prod processes |

### Extra link

* [https://12factor.net/](https://12factor.net/)
* [https://www.youtube.com/watch?v=94PxlbuizCU](https://www.youtube.com/watch?v=94PxlbuizCU)


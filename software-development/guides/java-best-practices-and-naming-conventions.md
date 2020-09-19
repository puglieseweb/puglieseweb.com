# Event-base Microservices Implementation Guideline

These recommended are introduced to help to keep  microservices' source code standard, human readable, easy to test and maintain.

the recommendation are meant to fit the technology stack being used: 

* Spring Cloud
* Confluent Kafka
* HashiCorp Vault
* Docker-compose

The main architectural patterns revolves around Event Driven Architecture with: 

* Kafka to decouple microservices and implement asynchronous communications
* Schema Registry to allow contract between microservices to evolve and yet be retro-compatible and support different client versions
* Stream Processing to create, transform, and persist Aggregates
* Hexagonal Architecture to structure microservices around communication channels, backed system, and business functionally
* REST AP to: 
  * load stale state
  * Allow communication with external Systems

## Java Package Naming Conventions

This s an example of commonly use naming convention for Java packages:

```java
package [reversed.domain.name].[department_name].[functional_area].[application_name];
```

where:

* **\[reversed.domain.name\]** is the company reversed Internet domain name, so to avoid conflict with worldwide writing classes.
* **\[department\_name\]** is used to avoid collisions between departments in the same company
* **\[functional\_area\]** is used to avoid collisions between functional area in the same department 
* \[**application\_name\]** is used to avoid collisions between Git Repositories/application



\[department\_name\], \[functional\_area\] and \[application\_name\] follow the below conventions: 

* **all lower case** to avoid conflict with the names of classes or interfaces.
* **only alphanumeric** and **underscores \(\_\)** characters are allowed.
* packages **cannot begin with a number**.

For common/shared libraries it is possible to follow the following convention:

```java
package [reversed.domain.name].[department_name].shared.[library_name];
```

or 

```java
package [reversed.domain.name].shared.[library_name];
```




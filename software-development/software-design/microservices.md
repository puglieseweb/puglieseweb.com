# Microservices

It is very common to develop microservices using JSON RItMicroservices are stateless and maintain state outside the application in databases or data grids. Thanks to this, a microservice can scale easily and you can deploy it independently of other services.EST interface for each andandandandandandandandandandandandandandandand every service in your application. Although it may not be the wrong choice we want to make sure we "use the right tool for the job" so that the applications can be simplified, more efficient, more secure and easier to monitor and maintain.

Designing a successful microservice architecture requires forgetting some of the principles architects have followed for a long time and applying new ones. Microservice architecture patterns relate to the size, lifecycle, and behavior of microservices and their relations.

**Domain-driven Design and Bounded Contexts**

Microservice-based applications can be designed by using Domain-Driven Design, an approach to software development that requires a close understanding of the business domain. Domains can have multiple bounded contexts, which encapsulate the details of a single business domain and defines integration points with other bounded contexts.

For example, in an e-commerce application: order, delivery, and billing are all examples of different bounded contexts. Each bounded context maintains a data model derived from the bounded domain model. Each bounded context translates into one or more microservices.

Advantages of the Bounded Domain Model

* Changes in the domain model only affect a limited number of services.
* Services are autonomous.

Disadvantages of the Bounded Domain Model

* Excellent domain knowledge is required to define a bounded context.
* Complexity increases to keep the system consistent between contexts.

&#x20;Microservices are stateless and maintain state outside the application in databases or data grids. Thanks to this, a microservice can scale easily and you can deploy it independently of other services.

**Design for Failure**

Distributed applications can fail due to code errors, or hardware or network failures. Developers must design applications that anticipate and can recover from such failures.

Developers can incorporate various design patterns to make microservices applications fault-tolerant.

* Provide High Availability microservices for failure management.
* Use the Circuit Breaker design pattern to prevent service or network failure from cascading to other services.
* Use the Fallback design pattern to gracefully handle service failure.

Microservices can communicate asynchronously by using message-based communication protocols such as AMQP or MQTT; or by implementing message-based patterns such as point-to-point, publish-and-subscribe, request-and-reply, or request-and-notification. Depending on the protocol or pattern used for the communication, intermediary services such as brokers might be responsible for delivering the messages to the correct destination.\




### Extra links

* [https://www.redhat.com/en/topics/microservices](https://www.redhat.com/en/topics/microservices)
* [https://blog.codeship.com/microservices-messaging-rest-isnt-always-best-choice/](https://blog.codeship.com/microservices-messaging-rest-isnt-always-best-choice/)

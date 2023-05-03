# Microservices

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

### Extra links

* [https://www.redhat.com/en/topics/microservices](https://www.redhat.com/en/topics/microservices)
* [https://blog.codeship.com/microservices-messaging-rest-isnt-always-best-choice/](https://blog.codeship.com/microservices-messaging-rest-isnt-always-best-choice/)

---
description: >-
  Create an application to work without either a UI or a database so you can run
  automated regression-tests
---

# Hexagonal Architecture with Java Spring

Resources:

* [https://alistair.cockburn.us/hexagonal-architecture/](https://alistair.cockburn.us/hexagonal-architecture/)
* [https://reflectoring.io/spring-hexagonal/](https://reflectoring.io/spring-hexagonal/)
* [https://allegro.tech/2020/05/hexagonal-architecture-by-example.html](https://allegro.tech/2020/05/hexagonal-architecture-by-example.html)

System's **interfaces should be designed "by purpose"** rather than technology. 

Technology are substitute by **adaptors.** 





![](../../.gitbook/assets/image%20%286%29.png)

Hexagonal Architecture reflects the use “primary actors” and “secondary actors”. A ‘’primary actor’’ is an actor that drives the application \(e.g user interaction or an automated regression testing suite\). A ‘’secondary actor’’ is one that the application drives, either to get answers from or to merely notify \(e.g. database response\).


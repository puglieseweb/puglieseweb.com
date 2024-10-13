# Observability

Resources:

* Micromiter: [https://micrometer.io/docs/tracing](https://micrometer.io/docs/tracing)
* Micromiter Tutorial: [https://www.youtube.com/watch?v=JAdxO1XboJY](https://www.youtube.com/watch?v=JAdxO1XboJY)
* [https://www.w3.org/TR/trace-context/](https://www.w3.org/TR/trace-context/)

## Introduction

[Observability with Spring Boot 3](https://spring.io/blog/2022/10/12/observability-with-spring-boot-3) explain how to integrates Micrometer with Spring Boot 3 so that it is possible to instrument the code once using a single API and have multiple benefits out of it (e.g. metrics, tracing, logging).

**Metrics** are used to capture aggregates of exchange and understand how part of an application are performing (e.g. find out the average latency of a call to an endpoint, find the medium, 95%, etc.)

**Distributed Tracing**  is used  to look to a particular exchange at a give moment in time.

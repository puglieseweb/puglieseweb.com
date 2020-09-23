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



![](../../.gitbook/assets/image%20%287%29.png)



* **The domain** is the core of the hexagon, containing primary business logic, free of any infrastructure and framework boilerplate.
* **Ports** are plain Java interfaces that allow adapters to  plugging into the core domain \(e.g. repositories interfaces\)
* **Adapters** are either external APIs of your application or clients to other systems. They translate the interfaces of external systems to interfaces exposed by the domain through ports.

Now we will try to map the Hexagonal Architecture concepts to a more developer friendly Spring naming covetous 



```text
adapters
   inboud
   outboud
domain
   service (containing all the ports)
   model
   config
   
```



service \(rappresenting all the port\)

### Example implementation



```text
class ArticleResponse {
    private final String id;
    private final String title;
    private final String content;
    private final String authorName;

    static ArticleResponse of(Article article) {
        return new ArticleResponse(article.id().value(),
                article.title().value(),
                article.content().value(),
                article.author().name());
    }
    //boilerplate code omitted
}
```

Each adapter works on its data model, which can translate itself “from” or “to” the domain. The domain model on the other hand is adapter-model-agnostic. That’s why you should always favour

```text
ArticleResponse.of(domainArticle) 
```

over

```text
domainArticle.toResponse()
```


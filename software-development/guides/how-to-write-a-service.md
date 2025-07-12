# How to write a service

1. Generate the source code using [https://start.spring.io/#!type=gradle-project\&language=java\&platformVersion=2.3.4.RELEASE\&packaging=jar\&jvmVersion=1.8\&groupId=com.puglieseweb.core.services\&artifactId=demo\&name=demo\&description=Core%20service\&packageName=com.puglieseweb.core.services.demo\&dependencies=web,kafka,cloud-stream,kafka-streams,lombok,h2,data-jpa](https://start.spring.io/#!type=gradle-project\&language=java\&platformVersion=2.3.4.RELEASE\&packaging=jar\&jvmVersion=1.8\&groupId=com.puglieseweb.core.services\&artifactId=demo\&name=demo\&description=Core%20service\&packageName=com.puglieseweb.core.services.demo\&dependencies=web,kafka,cloud-stream,kafka-streams,lombok,h2,data-jpa)

2 Structure the code as follows:

![](<../../.gitbook/assets/image (3) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1).png>)

![](<../../.gitbook/assets/image (4) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1).png>)

![](<../../.gitbook/assets/image (6) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1).png>)

Benefits:

* Adapters and Business logic can be tested separately
* Changes are easier to change
* Communication can use multiple adapter

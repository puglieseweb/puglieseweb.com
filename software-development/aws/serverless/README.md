# Serverless

Serverless is about leaving that management behind.

Key characteristic:

* Ease of Use: There is not much for us to do besides bringing our code. AWS handles almost everything for us.
* Event based: Serverless compute resources can be brought online in response to an event happening.
* Billing Model: "Pay as you go" in its purest form. You only pay for your provisioned resources and the length of runtime.

Fargate vs Lambda

* Fargate: Select Fargate when you have more consistent workloads. Allows Docker use across the organisation and a greater level of control by developers.&#x20;
* Lambda: Great for unpredictable or inconsistent workloads. Perfect for applications that can be expressed as a single function. Lambda automatically scales on demand.&#x20;

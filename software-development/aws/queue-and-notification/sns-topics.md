# SNS (Topics)

A push-based messaging service: any messages sent by a producer arrives at the SNS topic and it is sent to all consumers

Use cases:&#x20;

* Alert systems or person

Supported Subscribers:

* Kinesis Data Firehose, SQS, Lamda, email, HTTP(s),SMS, and platform application endpoint.
* Message size up to 256 KB of text in any format, but you can also leverage the **SNS Extended Library** allowing messages up to 2GB. in this case the payload is stored in S3 and the published message is a reference to the object in S3
* DLQ Support&#x20;
* support SQS FIFO
* Encrypted in transit by default, and can be encrytped at rest.
* has Access policies do define who can published and subscribe

SNS Fanout:

* Message are replicated to multiple endpoint subscriptions.
* Allows for fully decoupled parallel asynchronous processing



You can add also Filtering policies.



SNS only support custom retry polices for HTTP(s) endpoints





<figure><img src="../../../../.gitbook/assets/AWS - SNS (1).svg" alt=""><figcaption></figcaption></figure>

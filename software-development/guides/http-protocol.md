# HTTP Protocol

HTTP protocol has 2 versions:

### HTTP/1.1 

Disadvantages:

* Create a new connection for  every request is expensive
* The number of outgoing connections a client can maintain is limited by: 
  * Number of ports
  * The Operation System 

Advantages:

* In one HTTP connection breaks the other connections stay unaffected 
* header are passed in plain text that can be easily inspected by tools like Wireshark

### HTTP/2

Multiple request and responses share the same connection. The connection is therefore logically broken down in multiple internal streams.

header are compressed for efficiency and cannot be inspected by tools like Wireshark.

### HTTP Headers

Custom Headers

* X-Debug : Pass/log more debug information while handling the request
* X-Experiment: Turn on experimental features for A/B testing
* X-Test: Operate on test data instead of customers's data
* Timestamp: cluster-wide transaction instrumentation and check how much time a request take to go through each node and Network latency 


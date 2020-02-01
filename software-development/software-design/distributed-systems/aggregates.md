---
description: Aggregates in context of Distributed Systems
---

# Aggregates

**Aggregates** make the concept of transactional boundaries explicit. An aggregate is a collection of data elements model as Objects which map to end user’s mental model \(or Domain Model\) and the users interact with. Aggregates put one more important constraint on user actions on objects. The actions also need to be transactional \(following the ACID properties\).  
  
****This helps finding ‘seams’ to break the system into parts. It is possible to identify aggregates using well known analysis techniques like use cases. A ‘service api’, can be thought of as a set of operations, each being a command to an aggregate.

An aggregate is a cluster of domain objects which are considered as one unit with regard to data changes. A transaction within an aggregate must remain atomic.

Meanwhile, an aggregate enforces its own data consistency/integrity using invariants. An invariant is simply a rule which must remain true regardless of the changes


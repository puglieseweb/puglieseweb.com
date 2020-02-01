# Bounded Context

A _Bounded Context_ is a logical boundary whiting a system where ubiquitous language is applied. It encapsulates a set of services which share the same deployment cycle or domain model

_Unambiguous language_ is implemented by specific canonical models representing a same domain object in different contexts. Different canonical models are required as a total unified domain model for a large system is often not feasible or cost-effective. Unambiguous language helps developers, businesses, and users to stay on the same page when they are talking about a context.

### Examples

Examples of **Bounded Contexts** are:

* Order context
* Delivery context
* Invoice context

An example of **Domain Event** for the Order context is Order accepted. The Invoice and the Delivery contexts are interested in the occurrence of this  event as it causes some internal processes to be started.


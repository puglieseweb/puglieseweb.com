# Relations database to event messages

Normalisation of a database is also done to optimise it for specif use case scenario implemented by a specific service.

When events are sent to a broker, it often makes sense to flatten (de-normalise) any database relationship (e.g. one-to-many):

![](<../../../.gitbook/assets/image (8) (1) (1) (1) (1) (1) (1).png>)

Flattening a message make it easy for different consumer types to use the message according to their needs:

![](<../../../.gitbook/assets/image (3) (1) (1) (1) (1) (1) (1) (1).png>)

Often we want to send a events every time there is a DB change (AKA Change Data Capture (CDC), for example to:

* notify other systems.
* data analytics

This means that Database operation and messaging publishing needs to be part of the same transaction.

Since Spring for Apache Kafka 2.5 is is now possible to set a transaction at contain lever (see [Apache Kafka](../../technologies/kafka.md))

events are immutable. This means that we should design the system able to reconstruct the state or replay the events is required.

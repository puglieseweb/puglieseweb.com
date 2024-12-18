# Scaling a distributed system

Scaling a distributed system involves:

1. Scaling **User Interfaces** or **External APIs** that connect to a **User Facing Servers. Load Balancing** and **Replication** are to common techniques.
2. **Scaling the Internal System Coordination system (e.g. Zookeeper), Load Balancer, Service Register, Message brokers.**
3.  **Scaling the Storage Layer:**

    1. **Store into the File System.** This is:
       1. Low level, general purpose approach to store data of any format, structure or size
       2. Normally used for not structure data. Examples are:
          1. Video Files
          2. Audio Files
          3. Text Files
          4. Memory logs
    2. **Database. A higher level abstraction that provides:**
       1. Query Language, caching and performance optimisations based on the know structure to the data
       2. Provide constraints to be applied to the data
       3. Guarantees ACID Transactions:
          1. Atomic
          2. Consistent
          3. Isolated
          4. Durable
       4. Databases are easy to build and replace

    There are two types of Databases:

    1. relational
    2. non-relational (NoSQL) - Less Structured data:
       1. Key/Value pairs
       2. key/Document pairs
       3. Graph Database

NoSQL databases are normally easyer to scale than SQL databases because each record is independent for each other

Cons:

* limit the ability to analyse the data across different databases.

Databases needs to be:

* Available
* Scalable
* Fault Tolerant

Centralized Database Issues

* Single Point of Failure
* Performance Bottleneck
  * Parallelism is limited to the number of cores in a machine
  * Limited connections the OS and network card can support
  * Minimum latency depends on the geographical location of the database instance and the user
  * Limited to the memory that a single machine can have

### Database Sharding

Data Sharding is splitting the data and placing each chunk on a different machine.

One common techniques used for data sharding is **Consistent Hashing. Consistent Hashing map**s both the keys of our data and the database nodes that store that data to the same hash space.

The benefits of Consisting Hasting are:

1\) We can Dynamically add and remove database nodes from our database cluster, without reallocation all the keys to the new set of nodes.

2\) WE can allocate more keys to some database nodes and fewer keys to other nodes (using the virtual nodes technique)

3\) We can spread the keys of our data more evenly across the database nodes (by using multiple hash functions)

Database Replication

Replication is creating identical copies of all the data, and placing each copy on a different machine. This allows to create **redundancy and High Availability and Fault Tolerance, scalability and performances (for data intensive reads we can increase the throughput )**

\*\*\*\*

## Eventually Consistency

In Master-Master configuration eventually consistency it is the model, if no further updates are made, eventually all readers will have access to the newest data. However temporarily some readers may see stale data (for example while writing to a node and immediately reading from another one). This is a good choice for systems that do not need to have the most up to data data across the board (for example when posting to social media profiles)

Advantages are:

* Provide lower latency and higher availability

In a Distributed database we have Eventual Consistency when

R + W < N

where:

* R is the minimal number of nodes to read from
* W is the minimal number of nodes to write to
* The total number of nodes

## Strict Consistency

In and Symmetric Master - Master Architecture, during writing we need to wait for all the nodes to be updated before acknowledging the write.

## Quorum Consensus - Record Version

In a Distributed database to guarantee Strict Consistency we need

R + W > N

where

* R is the minimal number of nodes to read from
* W is the minimal number of nodes to write to
* The total number of nodes

**By changing the value of R and W we can optimise our application for reads or writes.**

![](<../../../.gitbook/assets/image (15) (1) (1) (1) (1).png>)

![](<../../../.gitbook/assets/image (17) (1) (1) (1).png>)

Distributed databases uses both sharding and replication to guarantee:

* High Availability
* Consistency
* fault tolerance

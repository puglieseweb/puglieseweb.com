# Zookeeper

Algorithms in Distributed Systems needs to work equally efficiently with any number of nodes.

A Distributed System must be:

* Fault Tolerant: any number of nodes can fail, and the cluster stay functional if at least a node is alive
* Horizontallly Scalable: we can add node Dynamically.
* Algorithm in place needs to avoid performance bottlenecks such as **Herd Effect**.

Fault Tolerance and Horizontal Scalability are very important properties for a Distributed System:

* Fault Tolerance: your business can run 24x7 with no interruption 
* Horizontal Scalability: we can dynamically grow our business on demand



Node: A process running a dedicated machine

* Cluster: A collection of computer/nodes connected to each other. The node in the same cluster are working on the same task, and typically are running the same code
* Master Node: distributed the task among other nodes and collects the results.
* [Zookeeper](https://blog.twitter.com/engineering/en_us/topics/infrastructure/2018/zookeeper-at-twitter.html): A high performance coordination service designed specifically for distributed systems. Provides a simple abstraction similar to a tree/file  system.
  * two types of Znodes:
    * Persistent - persists between sessions
    * Ephemeral - is deleted when the session ends
* Leader election algorithm: Requires a service registry mechanism and and election algorithm:
  1. Every node connects to zookeeper volunteers to become a leader: each node creates a znode under `/election` **``**znode parent. Zookeper name names the zoned according to the order of their addition. 
  2. The first znone being added \(The smallest node\) is the leader. Each added node queries the `/election` parent znode to get to know all the nodes crated prior its creating.
  3. If the current node created is the smallest number it knows that it is now the leader. If the node knows that is not the smallest number then it knows that needs to wait for instruction/Tasks coming from the leader

## Watcher and Triggers

A watcher allows us to get a notification when a change happens.

We can register a watcher when we call the methods:

```text
getData(znodePath, watcher) 
```

Get notified if a znode's data gets modified

```text
getChildren(..., watcher)
```

Get notified when the list of a znodes's children changes.

```text
exists(...)
```

exists\(znodePath, watcher\) : get notified if a znode gets deleted or created


# Distributed System Terminology

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
* 

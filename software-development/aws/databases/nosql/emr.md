---
description: Elastic MapReduce.
---

# EMR

EMR helps with ETL (Extract, Transform, Load) processing of large datasets, web indexing, machine learning training, and large-scale genomics.

EMR Uses open-sources tools such as Spark, Hive, HBase, Flink, Hudi, and Presto.

Clusters are groups of EC2 instances within Amazon EMR. Each instance is a node.

### Components of an EMR Cluster

1. **Master Node**: Manages the cluster, coordinating the distribution of data and tasks.
2. **Core Nodes**: Run tasks and store data in the Hadoop Distributed File System (HDFS).
3. **Task Nodes** (Optional): Only run tasks, do not store data. Typically uses spot instances.&#x20;

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/Bl-.4I.LO_ht?a=5723&#x26;x=428&#x26;y=178&#x26;w=1123&#x26;h=694&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%20d03cf71114f66befc2bc604a3d7e86caeff2a5af9b1a17deff5d25219609419e-ts%3D1726396829" alt=""><figcaption></figcaption></figure>

### Purchasing Options and Cluster Types&#x20;

* On-Demand. Most Reliable as will not be terminated. Most expensive choice.
* Reserved. Minimun of 1 year. Offer great cost savings. Typically used for Primary and Core nodes.&#x20;
* Spot. Cheapest option. Can be terminated with little warning. Typically used for Task nodes.

### EMR Storages

3 different storages options:

1. Hadoop Distributed File System (HDFS). Stored data across instances (nodes) and it is used for caching results during processing
2. EMR File System (EMRFS) extends HDFS to add the ability to directly access S3. S3 is used to store input and output data, not intermediate data.
3. Local File System. Disks created with each EC2 instance. These are ephemeral disks.&#x20;


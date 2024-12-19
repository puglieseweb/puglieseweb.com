# AWS Snow Family

### Historical Context: Great Barrier Island's Communication Challenge

In the late 1800s, Great Barrier Island, located 90 kilometers northeast of Auckland, New Zealand, faced significant isolation with only weekly steamer ship service for communication. The isolation's serious implications became evident in October 1894 when the SS Wairarapa crashed near Miner's Head, resulting in 121 deaths among 235 passengers. Due to the isolation, news of the tragedy couldn't reach the mainland until the next weekly steamer arrived.

#### The Pigeon Post Solution

In response to this isolation, about 18 months after the shipwreck, the world's first pigeon post service was established. The service proved so successful that two competing companies operated simultaneously. This innovative solution continued until 1908, when the first telegraph cable connected the island to the mainland.

### Modern Data Transfer: The AWS Snow Family

Today's massive data transfer challenges are addressed by the AWS Snow Family, an evolution of the AWS import/export process. This service offers several options for moving large amounts of data to and from AWS:

1. **Traditional Import/Export**
   * Ships external hard drives to AWS
   * AWS staff manually transfers data to S3
   * Basic but effective solution
2. **AWS Snowball**
   * Ruggedized NAS device
   * 80 TB capacity
   * Ships to customer location
   * Encrypted data transfer to S3
3. **Snowball Edge**
   * Enhanced version of Snowball
   * Includes onboard computing power
   * Supports Lambda functions
   * Clustering capabilities
   * Ideal for remote locations requiring data processing
   * Functions as a portable AWS solution
4. **AWS Snowmobile**
   * Shipping container-scale storage solution
   * Capacity up to 100 PB
   * Physical transportation to AWS data center
   * Used for massive data migrations
   * Large enough to be visible in satellite imagery (as demonstrated by DigitalGlobe's migration)

<figure><img src="../../../../.gitbook/assets/image (41).png" alt=""><figcaption></figcaption></figure>

#### Security and Transfer Process

* Devices are shipped via common carriers (UPS, DHL, FedEx)
* Data is encrypted during loading and transit
* Transfer speed depends on shipping method chosen

The AWS Snow Family demonstrates that sometimes, just as with the historical pigeon post, physical transport can be the most efficient solution for data transfer challenges.

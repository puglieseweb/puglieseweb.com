# EC2 Hibernation

EC2 Hibernation preserves the in-memory RAM on persistent storage (EBS). This feature offers several benefits and has specific limitations:

### Benefits

* Much faster to boot up because it does not need to reload the operating system.

### Requirements and Limitations

* Instance RAM must be less than 150 GB.
* Instance Families include:
  * C3, C4, C5
  * M3, M5
  * R3, R4, R5
* Available for:
  * Windows
  * Amazon Linux 2 AMI
  * Ubuntu
* **Instance cannot be hibernated for more than 60 days**.

### Availability

* Available for On-Demand and Reserved Instances.

### How It Works

When an EC2 instance hibernates, it saves the contents of the instance memory (RAM) to the EBS root volume. When the instance is started again, the RAM contents are reloaded, allowing the instance to resume where it left off.

This feature is particularly useful for:

* Long-running processes
* Services that take time to initialize
* Saving the RAM state for later use

By using hibernation, you can maintain the instance's state without keeping it running, potentially saving on compute costs while still preserving the instance's memory state.


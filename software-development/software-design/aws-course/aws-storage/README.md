# AWS Storage

* Volumes exist on EBS, whereas Snapshots exist on S3.

## How to encrypt unencrypted volumes

1. Create a snapshot of the unencrypted root device volume.
2. Create a copy of the snapshot and select the encrypt option.
3. Create an AMI from the ecrypted shapshot.
4. Use the AMI to launch new encrypted instances


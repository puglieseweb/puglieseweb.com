# EBS

<figure><img src="../../../../.gitbook/assets/image (12) (1).png" alt=""><figcaption></figcaption></figure>

## AWS Elastic Block Storage (EBS) Guide

### Introduction

EBS (Elastic Block Storage) can be thought of as virtual hard drives for AWS EC2 instances. This guide explains the key concepts and features of EBS using an analogy to the board game Othello/Reversei to help visualize how it works.

### Key Characteristics of EBS

* Only usable with EC2 instances
* Tied to a single Availability Zone (AZ)
* Does not inherently provide multi-AZ redundancy
* Offers various optimization choices for IOPS, throughput, and cost
* Can be attached and detached from EC2 instances

### EBS vs Instance Stores

#### Instance Stores

* Temporary storage
* Only available when EC2 instance is running
* Data is lost when instance is stopped or terminated
* Locked to specific EC2 instance
* Better performance due to direct attachment

#### EBS Volumes

* Persistent storage
* Can be attached/detached from different EC2 instances
* Supports snapshots
* Slightly lower performance due to network access
* More flexible and feature-rich

### EBS Snapshots

#### Benefits of Snapshots

1. Cost-effective backup strategy
2. Easy data sharing with other AWS accounts
3. Migration tool for moving between AZs or regions
4. Convert unencrypted volumes to encrypted ones

#### How Snapshots Work

Using the Othello board game analogy (64-block field):

1. Initial Snapshot
   * Contains complete volume data
   * Occupies full storage space
2. Subsequent Snapshots
   * Only store incremental changes
   * Much smaller storage footprint
   * Track both additions and deletions

#### Snapshot Management

* Deleting intermediate snapshots doesn't affect ability to restore from later ones
* Deleting the first snapshot doesn't impact restoration capabilities
* AWS automatically manages data pointers in S3
* Snapshots can be restored to create new volumes

### Amazon Data Lifecycle Manager

* Automates snapshot scheduling
* Allows scheduling for volumes or instances
* Provides retention rules for old snapshots
* Helps optimize storage costs
* Manages snapshot lifecycle automatically

### Best Practices

1. Pay attention to AZ when creating volumes
2. Use CloudWatch jobs for periodic snapshots
3. Implement appropriate retention policies
4. Consider encryption requirements
5. Monitor and manage costs through lifecycle policies

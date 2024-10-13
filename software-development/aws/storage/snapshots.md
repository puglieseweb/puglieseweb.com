# Snapshots

* Snapshots exist on S3
* are point-in-time photographs of volumes and are incremental in nature.
* the first snapshot will take some time to create. For consistent snapshots, stop the instance and detach the volume.
* You can share Snapshots between AWS accounts as well as between regions, but first you need to copy the snapshot to the target region.


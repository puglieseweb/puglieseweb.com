# Tape Gateway

**Tape Gateway (VTL - Virtual Tape Library)**

* Cloud-based replacement for physical tape backup systems
* Preserves existing tape-based backup workflows and software
* Uses TLS encryption for secure data transfer to AWS

Key Features:

* Virtual Tapes are stored in:
  * S3 for active virtual tapes (Virtual Tape Library)
  * S3 Glacier for archived virtual tapes (Virtual Tape Shelf)
* Compatible with major backup software vendors
* Cost-effective long-term data retention
* No physical tape infrastructure needed

Architecture:

```
CopyBackup Software → Tape Gateway → AWS Storage
(iSCSI VTL)    (TLS Transfer)  ↳ S3 (Active tapes)
                               ↳ S3 Glacier (Archived tapes)
```

Benefits:

* Maintains existing backup processes
* Eliminates physical tape management
* Reduces operational costs
* Secure and durable storage
* Easy retrieval when needed

<figure><img src="../../../../.gitbook/assets/image (2) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

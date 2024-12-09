# Sharing RDS DB instance with an external auditor

Make an encrypted snapshot of the database, share the snapshot, and allow access to the AWS KMS encryption key.

This is optimal because:

* Maintains data security through encryption
* Provides a complete, point-in-time copy of the database
* Allows controlled access through KMS key sharing
* Enables the auditor to create their own RDS instance from the snapshot

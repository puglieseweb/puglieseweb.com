# MFA Delete  VS Object Lock

MFA Delete:

* Requires both the user's credentials AND a valid MFA token to delete objects or disable versioning
* Must be enabled on versioning-enabled buckets
* Primarily protects against accidental or malicious deletions by adding an extra authentication step
* Good for general protection but can be disabled by admin users
* Lower administrative overhead

Object Lock:

* Enforces a "write-once-read-many" (WORM) model
* Two modes:
  1. **Governance mode:** Users with special permissions can override
  2. **Compliance mode:** NO ONE can delete objects until retention period expires, not even the root account
* Cannot be disabled once enabled
* More complex to manage but provides stronger compliance guarantees
* Often used for regulatory requirements like SEC Rule 17a-4

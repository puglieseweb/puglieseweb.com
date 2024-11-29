# Separation of Duties (SoD)

"Separation of Duties" (SoD) or "Segregation of Duties", in IT security ensures that no single person has excessive control by dividing critical functions and privileges among multiple users or roles ("nobody should have all the kingdom keys"). Here's what it means practically:

1. Access Control Principles:

* No single user/identity should have full admin access across all resources
* Break down permissions based on roles and responsibilities
* Use principle of least privilege (PoLP)

2. Data Partitioning Best Practices:

* Segment data across different security boundaries
* Use separate encryption keys for different data categories
* Implement different access levels for prod/dev/test environments
* Store sensitive data in isolated segments with stricter controls

This approach reduces risk by:

* Limiting blast radius of compromised credentials
* Enabling better audit trails
* Enforcing separation of duties
* Making lateral movement harder for attackers




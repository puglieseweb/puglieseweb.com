# AWS CloudFormation (Immutable AWS Architecture)

Allows to declare AWS infrastructure as code (AAC) using json or YAML template files.

We should aim to build a stateless immutable Architecture that we can re-provision anytime any ware in AWS.

We should never hardcode IDs (e.g. AMI or snapshot IDs) in the resource section of the templage , but rather store them in a mapping section of the template or use parameter store (part of the system manger) &#x20;



You deploy your infrastructure code as a Stack or Stack Set.&#x20;

Stacks are regional.

Stack Sets use admin account to create, update, or delete stacks across multiple account and Regions.

CloudFormation helps to:

* Cost Tracking
* Automate deployments&#x20;
* Change Set allows for a preview of resource changes when updating an existing  stack

Required field of  Template  (used to deploy resources) are:&#x20;

* Template Format Version&#x20;
* Resources

Other optiona fiels are:&#x20;

* parameters&#x20;
* mappings
* Outputs
* transform (macro to modify the template)

You can manually draft the template file or use the AWS Template Designer.

# Gateway Load Balancer (GWLB)

* Gateway Load Balancer (GWLB):
  * Specifically designed for deploying, scaling, and managing third-party virtual appliances like firewalls.
  * Allows you to deploy and manage a fleet of third-party virtual appliances that support GENEVE encapsulation.
  * Provides a transparent network gateway for routing traffic to virtual appliances for inspection.
* Gateway Load Balancer Endpoint:
  * Acts as an entry/exit point for traffic going to/from the virtual appliances.
  * Allows you to keep the appliances in a separate VPC (inspection VPC) while seamlessly integrating with the application VPC.
* Least Operational Overhead:
  * GWLB automatically scales and manages the distribution of traffic across multiple appliance instances.
  * Simplifies the architecture by eliminating the need for complex routing rules or additional networking components.
* Transparent to the Application:
  * The application doesn't need to be aware of the inspection process, making integration seamless.

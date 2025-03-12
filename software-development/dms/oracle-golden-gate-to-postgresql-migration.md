# Oracle Golden Gate to PostgreSQL Migration

## Architecture Decision Record: Oracle to PostgreSQL Migration Strategy

### Context

* Current Environment:
  * Two Oracle database instances in Datacenter A
  * Two Oracle database instances in Datacenter B
  * Oracle GoldenGate performing homogeneous replication between instances
  * Business application using a service name to connect to one Oracle instance at a time
  * TLS not currently enabled between application and Oracle databases
  * AWS Direct Connect already established between on-premises datacenters and AWS
  * Some data is encrypted using Oracle Transparent Data Encryption (TDE)
  * Oracle service provides transparent connection routing to active instances
* Target Environment:
  * AWS PostgreSQL as the destination database
  * Need for ongoing replication using CDC (Change Data Capture)
  * Desire to enable TLS specifically for the DMS migration path

### Decision Drivers

* Minimize disruption to existing business applications
* Ensure data consistency during and after migration
* Enable secure data transfer using TLS for the migration path
* Leverage existing Oracle GoldenGate infrastructure where beneficial
* Ensure compatibility between source Oracle systems and target PostgreSQL

### Options Analysis

#### Option 1: Direct AWS DMS with Oracle TLS Source Endpoint

* **Description**: Configure AWS DMS to connect directly to one Oracle instance per datacenter with TLS enabled specifically for these connections
* **Advantages**:
  * Simpler architecture with direct DMS-to-Oracle connection
  * Can enable TLS specifically for DMS connections
  * Leverages AWS DMS's built-in CDC capabilities
* **Disadvantages**:
  * Need to reconfigure if the active Oracle instance changes
  * Requires opening firewall rules from AWS to on-premises
  * Additional load on production databases

#### Option 2: AWS DMS + Oracle GoldenGate as CDC Source

* **Description**: Use existing GoldenGate infrastructure to consolidate changes to a dedicated Oracle instance, which becomes the source for AWS DMS
* **Advantages**:
  * Leverages existing GoldenGate replication
  * Isolates migration traffic from production databases
  * Single entry point for AWS DMS regardless of which instance is active
* **Disadvantages**:
  * Additional Oracle instance required
  * More complex architecture
  * Potential for slightly increased latency

#### Option 3: Oracle GoldenGate + AWS DMS with Initial Load Optimization

* **Description**: Use AWS DMS for initial load with Oracle GoldenGate handling ongoing CDC to PostgreSQL
* **Advantages**:
  * Utilizes GoldenGate's proven replication capabilities
  * Can optimize initial load through DMS parallel processing
  * Potentially better control over transformation logic
* **Disadvantages**:
  * Requires GoldenGate license for heterogeneous replication
  * More complex setup and maintenance
  * Requires expertise in both GoldenGate and DMS

#### Option 4: AWS SCT + DMS with GoldenGate-Isolated Source

* **Description**: Use AWS Schema Conversion Tool (SCT) for schema migration, DMS for data migration, with a GoldenGate-replicated Oracle instance as the source
* **Advantages**:
  * Comprehensive schema conversion capabilities
  * Isolates production databases from migration workload
  * Can implement TLS just on the dedicated migration path
* **Disadvantages**:
  * More components to manage
  * Additional Oracle instance required
  * Requires expertise across multiple tools

### Decision

**Recommended Approach: Option 2 - AWS DMS + Oracle GoldenGate as CDC Source**

This approach provides the best balance of leveraging existing infrastructure, minimizing impact on production systems, and providing a clean migration path with TLS security.

Key benefits for this specific environment:

* Leverages existing Direct Connect for secure connectivity between AWS and on-premises
* Enables TLS specifically for the migration path without disrupting existing application connections
* Isolates migration workload from production databases
* Creates a single, stable source endpoint for AWS DMS regardless of which production instance is active

### Network Architecture Diagrams

#### Current Application Access (Non-TLS)

```
┌─────────────────┐                                 ┌─────────────────────────┐
│                 │                                 │    Datacenter A         │
│    Business     │                                 │  ┌─────────────────┐    │
│    Application  │◄───Oracle Service Name──────────┼─►│  Oracle DB A1   │    │
│                 │  (load-balanced, non-TLS)       │  └─────────────────┘    │
└─────────────────┘                                 │                         │
                                                    │  ┌─────────────────┐    │
                                                    │  │  Oracle DB A2   │    │
                                                    │  └─────────────────┘    │
                                                    └─────────────────────────┘
                                                                │
                                                                │ GoldenGate
                                                                │ Replication
                                                                ▼
                                                    ┌─────────────────────────┐
                                                    │    Datacenter B         │
                                                    │  ┌─────────────────┐    │
                                                    │  │  Oracle DB B1   │    │
                                                    │  └─────────────────┘    │
                                                    │                         │
                                                    │  ┌─────────────────┐    │
                                                    │  │  Oracle DB B2   │    │
                                                    │  └─────────────────┘    │
                                                    └─────────────────────────┘
```

#### Oracle Service and Connection Proxy Architecture

```
┌─────────────────┐                                 ┌─────────────────────────┐
│                 │                                 │      Oracle Service     │
│    Business     │◄───────────────────────────────►│  ┌─────────────────┐   │
│    Application  │                                 │  │ Oracle Net      │   │
│                 │                                 │  │ Listener        │   │
└─────────────────┘                                 │  └───────┬─────────┘   │
                                                    │          │             │
                                                    │          ▼             │
                                                    │  ┌─────────────────┐   │
                                                    │  │ Connection      │   │
                                                    │  │ Manager Proxy   │   │
                                                    │  └───────┬─────────┘   │
                                                    └──────────┼─────────────┘
                                                               │
                         ┌────────────────────────────────────┐│┌─────────────────────────────────┐
                         │                                    ││                                  │
                         ▼                                    ▼▼                                  ▼
              ┌─────────────────────────┐        ┌─────────────────────────┐        ┌─────────────────────────┐
              │    Datacenter A         │        │    Datacenter A         │        │    Datacenter B         │
              │  ┌─────────────────┐    │        │  ┌─────────────────┐    │        │  ┌─────────────────┐    │
              │  │  Oracle DB A1   │    │        │  │  Oracle DB A2   │    │        │  │  Oracle DB B1   │    │
              │  └─────────────────┘    │        │  └─────────────────┘    │        │  └─────────────────┘    │
              └─────────────────────────┘        └─────────────────────────┘        └─────────────────────────┘
```

**Oracle Service Components**

1. **Oracle Net Listener**:
   * Listens for client connection requests
   * Receives initial connection through the service name (e.g., `PROD_SERVICE`)
   * Forwards connection to the Connection Manager based on the service configuration
2. **Connection Manager Proxy (CMAN)**:
   * Acts as a proxy between clients and database servers
   * Performs load balancing across available database instances
   * Provides session multiplexing and connection concentration
   * Can implement network access control rules
3.  **Service Configuration**:

    ```
    # Example tnsnames.ora configuration
    PROD_SERVICE =
      (DESCRIPTION =
        (ADDRESS = (PROTOCOL = TCP)(HOST = oracle-cman.example.com)(PORT = 1521))
        (CONNECT_DATA =
          (SERVICE_NAME = PROD_SERVICE)
        )
      )
    ```
4.  **Migration-Specific Service Configuration**:

    * Create a dedicated service for AWS DMS connections
    * Example:

    ```
    MIGRATION_SERVICE =
      (DESCRIPTION =
        (ADDRESS = (PROTOCOL = TCPS)(HOST = migration-oracle.example.com)(PORT = 2484))
        (CONNECT_DATA =
          (SERVICE_NAME = MIGRATION_SERVICE)
        )
        (SECURITY =
          (SSL_SERVER_CERT_DN = "CN=migration-oracle.example.com,O=Your Organization")
        )
      )
    ```
5. **Configuration for TLS-Only Migration Service**:
   * Configure a separate Oracle Net Listener for TLS connections
   * Create a dedicated Connection Manager profile for migration traffic
   * Ensure the migration service points only to the dedicated Oracle instance
   * Apply network access controls to allow only AWS DMS IP ranges

#### Proposed Migration Architecture with TLS for DMS

```
┌─────────────────┐                                 ┌─────────────────────────┐
│                 │                                 │     Oracle Service      │
│    Business     │◄───Oracle Service Name──────────┼─►┌─────────────────┐    │
│    Application  │  (load-balanced, non-TLS)       │  │ Connection      │    │
│                 │                                 │  │ Manager Proxy   │    │
└─────────────────┘                                 │  └──────┬──────────┘    │
                                                    └──────────┼───────────────┘
                                                               │
                                  ┌──────────────────────┐     │     ┌──────────────────────┐
                                  │                      │     │     │                      │
                                  ▼                      ▼     ▼     ▼                      ▼
                    ┌─────────────────────────┐   ┌─────────────────────────┐   ┌─────────────────────────┐
                    │    Datacenter A         │   │    Datacenter A         │   │    Datacenter B         │
                    │  ┌─────────────────┐    │   │  ┌─────────────────┐    │   │  ┌─────────────────┐    │
                    │  │  Oracle DB A1   │    │   │  │  Oracle DB A2   │    │   │  │  Oracle DB B1   │    │
                    │  │ (TDE enabled)   │    │   │  │ (TDE enabled)   │    │   │  │ (TDE enabled)   │    │
                    │  └─────────────────┘    │   │  └─────────────────┘    │   │  └─────────────────┘    │
                    └─────────────────────────┘   └─────────────────────────┘   └─────────────────────────┘
                                 │                           │                             │
                                 │                           │                             │
                                 │        GoldenGate         │                             │
                                 └───────────Replication─────┴─────────────────────────────┘
                                                   │
                                                   ▼
┌───────────────────────┐                 ┌─────────────────────────────────┐
│        AWS            │                 │         Dedicated Migration      │
│                       │ AWS Direct      │         Environment              │
│  ┌─────────────────┐  │ Connect         │  ┌───────────────────────────┐  │
│  │                 │  │                 │  │  Oracle TLS Listener      │  │
│  │  DMS            │  │                 │  │  (Port 2484)              │  │
│  │  Replication    │◄─┼─TLS Connection──┼─►│                           │  │
│  │  Instance       │  │ with TDE Wallet │  │  Migration Oracle DB      │  │
│  │                 │  │                 │  │  (TDE Keys Available)     │  │
│  └─────────────────┘  │                 │  └───────────────────────────┘  │
│         │             │                 └─────────────────────────────────┘
│         │             │
│         ▼             │
│  ┌─────────────────┐  │
│  │                 │  │
│  │  PostgreSQL     │  │
│  │  Target with    │  │
│  │  pgcrypto       │  │
│  └─────────────────┘  │
│                       │
└───────────────────────┘
```

### Implementation Plan

#### 1. Prepare the Environment

* Create a dedicated Oracle instance to serve as the GoldenGate target and DMS source
* Configure this instance with TLS enabled
* Ensure network connectivity between AWS and the dedicated Oracle instance
* Provision AWS DMS replication instance with appropriate specifications
* **TDE Considerations**: Set up the Oracle wallet with TDE keys from production databases

#### 2. Configure Oracle Connection Manager and Services

* Set up Oracle Connection Manager (CMAN) if not already in use
* Create a dedicated service for AWS DMS connections with TLS enforcement
* Configure network access control rules to restrict access to the migration service
* Test connection routing through both standard and TLS-enabled services

#### 3. Configure Oracle GoldenGate

* Configure GoldenGate to replicate from all Oracle instances to the dedicated instance
* Verify that all necessary tables and schemas are included in the replication
* **TDE Considerations**: Ensure GoldenGate has appropriate wallet access for TDE-encrypted columns
* Test GoldenGate replication performance and consistency

#### 3. Configure TLS for the Dedicated Oracle Instance

* Generate TLS certificates for the dedicated Oracle instance
* Configure Oracle listener for TLS connections
* Set up Oracle wallet with certificates
* Test TLS connectivity locally to ensure proper setup
* Document TLS configuration for future reference

#### 4. Setup AWS Schema Conversion Tool (SCT)

* Use AWS SCT to convert Oracle schemas to PostgreSQL format
* Review and adjust any incompatible objects or data types
* Create the target schema in PostgreSQL

#### 6. Configure AWS DMS

*   Create source endpoint connecting to the dedicated Oracle instance with TLS enabled:

    ```json
    {  "EndpointIdentifier": "oracle-tde-source",  "EndpointType": "source",  "EngineName": "oracle",  "Username": "dms_user",  "Password": "{{password}}",  "ServerName": "migration-oracle.example.com",  "Port": 2484,  "DatabaseName": "MIGDB",  "SslMode": "verify-full",  "CertificateArn": "arn:aws:dms:region:account:certificate:cert-id",  "ExtraConnectionAttributes": "useLogminerReader=N;useBfile=Y;accessAlternateDirectly=false;useAlternateFolderForOnline=true;oraclePathPrefix=/rdsdbdata/log/;usePathPrefix=/rdsdbdata/log/;replacePathPrefix=true"}
    ```
* Create target endpoint connecting to AWS PostgreSQL
* Create replication task with appropriate settings for full load + CDC
* Configure table mappings and transformation rules as needed
* **TDE-specific configuration**: Include Oracle wallet location in the DMS task settings

#### 7. PostgreSQL Encryption Setup

* Install and configure `pgcrypto` extension for column-level encryption
* Set up AWS KMS integration for key management
* Design encryption strategy for sensitive columns
* Create stored procedures for encryption/decryption operations

#### 8. Migration Execution

* Perform initial full load while application continues to use Oracle
* Validate data in PostgreSQL against Oracle source
* **TDE Data Validation**: Verify that previously encrypted data is correctly processed
* Enable ongoing CDC replication
* Monitor replication lag and data consistency

#### 9. Application Cutover

* Update application configuration to connect to PostgreSQL
* Monitor application performance and functionality
* Maintain Oracle GoldenGate + DMS replication for a rollback period
* Decommission Oracle infrastructure after successful validation

### Security Considerations

* TLS certificates must be properly configured on the dedicated Oracle instance
* AWS DMS replication instance should be in a secure VPC with appropriate security groups
* Leverage existing Direct Connect for secure connectivity between AWS and on-premises
* Database credentials should be managed through AWS Secrets Manager
* Audit logging should be enabled on both source and target databases
* Network ACLs and security groups should be configured to allow only necessary traffic

### Monitoring and Validation Strategy

* Implement checksum validation for critical tables
* Monitor DMS task metrics through CloudWatch
* Set up alerts for replication lag exceeding thresholds
* Regularly validate row counts and sample data between source and target
* Perform application-level validation tests to ensure business logic integrity

### Network and TLS Configuration Details

#### TLS Configuration for Oracle Migration Source

1. **Certificate Setup**:
   * Generate a certificate signing request (CSR) for the dedicated Oracle instance
   * Use an internal CA or AWS Certificate Manager Private CA to sign the certificate
   * Import the certificate and CA chain into an Oracle wallet
2.  **Oracle Listener Configuration**:

    ```
    LISTENER =
      (DESCRIPTION_LIST =
        (DESCRIPTION =
          (ADDRESS = (PROTOCOL = TCP)(HOST = migration-oracle.example.com)(PORT = 1521))
          (ADDRESS = (PROTOCOL = TCPS)(HOST = migration-oracle.example.com)(PORT = 2484))
        )
      )

    WALLET_LOCATION = (SOURCE = (METHOD = FILE)(METHOD_DATA = (DIRECTORY = /oracle/wallet)))
    SSL_CLIENT_AUTHENTICATION = FALSE
    ```
3. **AWS DMS Source Endpoint Configuration**:
   * Use TCPS protocol in the connection string
   * Upload the CA certificate to AWS DMS
   * Configure the endpoint with TLS mode enabled
4. **Direct Connect Configuration**:
   * Ensure security groups in AWS VPC allow inbound connections on port 2484 (TCPS)
   * Configure on-premises firewall to allow outbound connections from DMS IP range to Oracle on port 2484
   * Use AWS Direct Connect routing policies to prioritize database migration traffic

# Reporting System 2

I'll update the architecture to include the wallet services and differentiate between provisioning and payment token states. I'll create two diagrams: one for the overall architecture and another focused on the state management and reporting.



```mermaid
```

```mermaid
stateDiagram-v2
    [*] --> TokenRequested

    state "Provisioning States" as ProvStates {
        TokenRequested --> ProvisionInitiated
        ProvisionInitiated --> ProvisionPending
        ProvisionPending --> ProvisionSuccess
        ProvisionPending --> ProvisionFailed
        ProvisionSuccess --> WalletActive
        ProvisionFailed --> [*]
    }

    state "Payment Token States" as PayStates {
        WalletActive --> TokenActive
        TokenActive --> TokenSuspended
        TokenSuspended --> TokenActive
        TokenActive --> TokenExpired
        TokenSuspended --> TokenExpired
        TokenExpired --> [*]
    }

    note right of ProvStates
        Monitored by:
        - Datadog APM
        - Custom Provisioning Dashboards
        - Wallet-specific metrics
    end note

    note right of PayStates
        Tracked through:
        - Token Lifecycle Reports
        - Real-time State Monitoring
        - Compliance Auditing
    end note

```

Key updates to the reporting architecture:

1. Wallet Services Layer:
   * Separate monitoring for each wallet service
   * Wallet-specific performance metrics
   * Provision success rates by wallet
2. State Management:
   * Distinct tracking for provisioning states
   * Separate monitoring for payment token states
   * State transition analytics
3. Enhanced Reporting Categories:
   * Wallet-specific reports:
     * Provision success rates by wallet
     * Token usage patterns by wallet
     * Wallet-specific error rates
   * State-based reports:
     * Provisioning state transitions
     * Token state lifecycle
     * State duration metrics
   * Performance reports:
     * Wallet service latency
     * Token service performance
     * Provisioning service metrics
4. Real-time Monitoring Updates:
   * Wallet service health
   * Provision state transitions
   * Token state changes
   * Cross-wallet analytics
5. Business Intelligence:
   * Wallet usage patterns
   * Provision success trends
   * Token lifecycle analysis
   * Cross-wallet comparisons

Would you like me to:

1. Detail specific metrics for wallet service monitoring?
2. Explain the state transition reporting strategy?
3. Elaborate on specific report implementations for any component?

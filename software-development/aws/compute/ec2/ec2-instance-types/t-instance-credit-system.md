# T Instance Credit System

T Instance Credit System:

1. CPU Credits

* Each T instance earns CPU credits continuously at a fixed rate depending on the instance size
* These credits accumulate when CPU usage is below the baseline performance
* Credits can be spent when CPU usage exceeds the baseline
* Maximum credit balance varies by instance size
* Credits expire after 24 hours for T2, but never expire for T3 instances while running

2. Baseline Performance

* Each T instance size has a baseline CPU performance level
* For example:
  * t3.micro: 10% baseline CPU utilization
  * t3.small: 20% baseline CPU utilization
  * t3.medium: 20% baseline CPU utilization
* Operating at or below baseline allows credit accumulation
* Operating above baseline consumes credits

3. Credit Earning Rate Examples:

* t3.micro: 6 credits per hour
* t3.small: 12 credits per hour
* t3.medium: 24 credits per hour
* One CPU credit equals one vCPU running at 100% utilization for one minute

4. Burst Behavior

* When workload requires more CPU than baseline:
  * Instance spends accrued credits to burst
  * Can burst up to 100% CPU utilization if enough credits available
  * Performance gradually returns to baseline when credits are depleted
* No performance degradation during burst-to-baseline transition

5. Launch Credits

* New T instances receive launch credits
* Helps provide good initial performance for new instances
* Launch credits are used first, before earned credits
* Launch credits that aren't used expire after 24 hours

6. Unlimited Mode vs Standard Mode

* Standard Mode:
  * Instance can only burst while it has credits
  * Returns to baseline when credits are exhausted
  * No additional charges
* Unlimited Mode:
  * Can burst beyond earned credits
  * Incurs additional charges for sustained high CPU usage
  * Useful for workloads requiring consistent high performance
  * Can be enabled/disabled at any time

7. Monitoring Credits

* CloudWatch metrics available:
  * CPUCreditUsage
  * CPUCreditBalance
  * CPUSurplusCreditBalance (Unlimited mode)
  * CPUSurplusCreditsCharged (Unlimited mode)

8. Credit Behavior During Stop/Start

* Standard Mode:
  * Accumulated credits are lost when instance is stopped
  * New instance starts with zero earned credits
  * Receives new launch credits
* Unlimited Mode:
  * Surplus credits are billed when instance is stopped
  * New instance starts with zero earned credits
  * Receives new launch credits

Best Practices:

1. Workload Matching

* Use T instances for variable workloads with occasional spikes
* Monitor credit balance to ensure adequate performance
* Switch to non-burstable instances if consistently high CPU needed

2. Mode Selection

* Use Standard mode for:
  * Predictable workloads
  * Development/test environments
  * Non-critical applications
* Use Unlimited mode for:
  * Applications requiring consistent performance
  * Workloads with unexpected spikes
  * When occasional additional costs are acceptable

3. Cost Management

* Monitor credit usage patterns
* Set up CloudWatch alarms for credit balance
* Consider switching to M instances if consistently using all credits

This credit mechanism is crucial for the SAP-C02 exam, as you may need to:

* Determine appropriate instance sizes based on workload patterns
* Understand cost implications of different modes
* Troubleshoot performance issues related to credit exhaustion
* Design solutions using T instances effectively

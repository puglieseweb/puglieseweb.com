# API Security

### Create a usage plan with an API key that is shared with genuine users only



API Gateway usage plans with API keys provide several key benefits for controlling API access:

1. Keys act as authentication tokens that must be included in request headers
2. Allows throttling and quota limits per API key
3. Enables monitoring/tracking of usage by key
4. Can revoke compromised keys without affecting other users

The solution requires distributing API keys securely to legitimate users while keeping them private from potential attackers.

# Cognito

The two components of Cognito are:&#x20;

* User pools: are directories of users that provide sign-up and sign-in options for your application
* Identity pools: allow you to give your user access to other AWS services.&#x20;

User pools and Itentity pools can be used either separatly or together.&#x20;



```plant-uml
@startuml
actor User
participant "Client Application" as App
participant "AWS Cognito" as Cognito
participant "Protected API" as API

User -> App: 1. Open app
App -> Cognito: 2. Initialize
Cognito --> App: 3. Return configuration
User -> App: 4. Enter credentials
App -> Cognito: 5. Authenticate (username/password)
Cognito --> App: 6. Return tokens (ID, Access, Refresh)
App -> App: 7. Store tokens
User -> App: 8. Request protected resource
App -> API: 9. API request with Access token
API -> API: 10. Validate token
API --> App: 11. Return protected resource
App --> User: 12. Display protected resource
@enduml
```

# Cognito

Cognito provides authentication, authorization, and user management for your web and mobile apps in a single service without the need for custom code. Your users can sign in directly with a username and password they create on through a third party (e.g. Facebook, Amazon, Google or Apple)



Features:

1. Sign-up and sign-in options for your apps
2. Access for guest users
3. Acts as an identity broker between your application and web ID providers.



The two components of Cognito are:&#x20;

* User pools: are directories of users that provide sign-up and sign-in options for your application
* Identity pools: allow you to give your user access to other AWS services.&#x20;

User pools and Itentity pools can be used either separatly or together.&#x20;

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/8JJap_RE7xqG?a=6538&#x26;x=299&#x26;y=94&#x26;w=886&#x26;h=912&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%20adbbd2f69d28520c1f0b52217ba47509eab25768d39dfbc6ad3f30dfdc74c750-ts%3D1726855842" alt=""><figcaption></figcaption></figure>

```plant-uml
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
```

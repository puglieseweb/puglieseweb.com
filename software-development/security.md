# Security

* **CSRF (Cross-Site Request Forgery)** → someone uses your _already logged-in browser_ as a remote control. this is an attack where a malicious site causes a logged-in user’s browser to perform **unwanted actions** on another site by **abusing automatic cookie sending**.
* **XSS (Cross-Site Scripting)** → someone installs malware inside your site. **XSS**, instead, is an attack where **malicious JavaScript** is injected into a legitimate site and runs **with the user’s privileges**.
* **CORS (Cross-Origin Resource Sharing)**→ browser deciding who can read data present in response messages from a legit backend server. **CORS** is a browser security mechanism that prevents a malicious or cloned website from **reading responses from a backend API** unless the backend explicitly allows that origin.

| Attack | Needs JS?       | Uses cookies? | Goal                 |
| ------ | --------------- | ------------- | -------------------- |
| CSRF   | ❌ No            | ✅ Yes         | Perform actions      |
| XSS    | ✅ Yes           | ✅ Yes         | Steal data / control |
| CORS   | ❌ Not an attack | ❌             | Protect responses    |

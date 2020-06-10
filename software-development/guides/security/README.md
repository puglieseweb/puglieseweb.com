# Security

Complex systems are intrinsically insecure and security vulnerabilities and bugs will exist in hardware, operating systems, tools and application we develop and use.

So we have to accept and mitigate the risk of security vulnerabilities by:

* distributing trust.
* reduce attack surfaces.
* create isolation and compartmentalization.
* build layers of defenses.

It is important to mitigate risks by selection the right **security controls.**

Encrypting everything is the best way of mitigating the security risk that comes with a modern complex system. 

## Encryption

Encryption transform plain text in Cipher Text \(unreadable format\). In encryption we have to main component: 

* the Algorithm \(public\)
* the Key \(secret\)

AES is a Symmetric algorithm: it uses only one Key. The password is converted to a  key derivation function. Also [a good read](http://pic.dhe.ibm.com/infocenter/initiate/v9r5/index.jsp?topic=/com.ibm.einstall.doc/topics/t_einstall_GenerateAESkey.html) \(courtesy of IBM\).

So in the end you actually have 3 things:

* Your encrypted files
* Your encrypted key
* A hash of your password to verify it

You might ask why you need the key: If you would change your password \(and you had encrypted all your files with your password\), you would need to first decrypt all your files with your orignal password and then re-encrypt them with your new password. With this implementation all you need to do is decrypt and re-encrypt the key \(as this key serves as your password\).


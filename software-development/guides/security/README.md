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

### Symmetric encryption

Symmetric encryption uses only one key.

At the time of writing, AES 256 is that advised is Symmetric algorithm to use. If combined with a "strong password" it is uncrackable. AES 128 is also considered secure.

The password is converted to a **key derivation function**. Also [a good read](http://pic.dhe.ibm.com/infocenter/initiate/v9r5/index.jsp?topic=/com.ibm.einstall.doc/topics/t_einstall_GenerateAESkey.html) \(courtesy of IBM\).

So in the end you actually have 3 things:

* Your encrypted files
* Your encrypted key
* A hash of your password to verify it

You might ask why you need the key: If you would change your password \(and you had encrypted all your files with your password\), you would need to first decrypt all your files with your orignal password and then re-encrypt them with your new password. With this implementation all you need to do is decrypt and re-encrypt the key \(as this key serves as your password\).

But how do you exchange or agree keys? 

### Asymmetric encryption

Asymmetric algorithms help solve the problem of exchanging or agreeing Keys and also allow for something called digital signatures.

Asymmetric encryption uses two keys:

* a public key, and
* a private key

A very common asymmetric algorithm is RSA.

You always need the counterpart key to encrypt and decrypt and it is not possible to encrypt and decrypt using the same key.

#### Encrypting using the Public key

A sender can encrypt using a public key to achieve privacy or **confidentiality** so no one else can read the message but the receiver. The receiver though cannot confirm who has sent because anyone can use public key to encrypt \(the sender cannot be authenticated\).

#### Encrypting using the Private key

if you encrypt with your own private key then this means **authenticating** is what you're interested in.

#### Hash functions

Hashing can be used to verify **integrity**.

A plain text **input** is processed by a **cryptographic hash function** to produce a **digests** \(AKA message digests or hashes\). a digest cannot be used to recover an input.

Example of Hash function to be used are SHA 256 or above.

#### Digital Signature

A digital Signature is a signed message.

A singed message is produced bu by hashing the message and then encrypting it with the sender private key.

A digital signature provide integrity \(hashing\), notrepudiation and authentication \(private key encryption\)



Encryption + Digital Signature provides confidentiality, integrity, nonrepudiation and authentication. 

#### Crypto System

A Crypto System combine  various encryption technologies to provide a number of security services:

* **confidentiality** which is privacy 
* **authentication** which is knowing that Bob is the real Bob or you are the real you 
* **Nonrepudiation** which means you cannot later deny that you send or encrypted a message
* **integrity** that the message hasn't been altered in any way.

And example of Crypto System is TLS. Another example is PGP, and encryption technology for emails.

**Hybrid Crypto System**

Asymmetric encryption is very very slow compared do Symmetric encryption.

Which is why we have something called hybrid systems or hybrid crypto systems. Public and private keys are used to exchange an agreed keys and the  use symmetric algorithms like AES to actually encrypt the data.

HTTPS using TLS and is an example of Hybrid Crypto System offering confidentiality, authentication, Nonrepudiation and integrity.



The combination of algorithms used is know as **Cipher suite.**


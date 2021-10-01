In cryptography, PKCS #12 defines an archive file format for storing many cryptography objects as a single file. 
It is commonly used to bundle a *private key* with its *X.509 certificate* or to *bundle all the members of a chain of trust*.

A PKCS #12 file may be encrypted and signed. The internal storage containers, called "SafeBags", may also be encrypted and signed. 
A few SafeBags are predefined to store *certificates*, *private keys* and *CRLs*. Another SafeBag is provided to store any other data at individual implementer's choice.


It is advisible to:
* use a different logger for debug pupose
* log internal ids rather the user or emails informatins 
* Replace the Spring Whitelabel error page and replace with an custom error.html


# Debugging
Adding a service parameter to a server request is not a good protection against hacking. If an attacker fakes a request, he will be able to include the output of debugging information in logs and collect a lot of important information about the system.


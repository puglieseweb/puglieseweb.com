# Relations database to messages

Normalisation of  a database is also done to optimise it for specif use case scenario implemented by a specific service. 

When messages are sent to a broker, it often makes sense to flatten \(de-normalise\) any database relationship \(e.g. one-to-many\):  

![](../../../.gitbook/assets/image%20%287%29.png)

Flattening a message make it easy for different consumer types to use the message according to their needs:

![](../../../.gitbook/assets/image%20%288%29.png)


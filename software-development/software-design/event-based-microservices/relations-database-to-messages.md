# Relations database to messages

When we normalise a database we normal optimise it for a specif use case scenario required by a service. 

When sending message to a broker often make sense to flatten \(de-normalise\) any database relationship \(e.g. one-to-many\) into messages:  

![](../../../.gitbook/assets/image%20%287%29.png)

Flattening a message make easy for different consumer types to use the message suit  their needs

![](../../../.gitbook/assets/image%20%288%29.png)


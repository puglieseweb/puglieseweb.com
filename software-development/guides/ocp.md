# OCP

* Build Config references both Git hub repository (a property of the Build Config is the Build Hock) and the Image Stream (containing Builder Image Steam and to build a a specific time of an Application
* Build Config start a Build Pod.
* Build Pod checkout the Git Repo and produce an Application Image
* The Application Image is pushed to the Image Stream that now references the new image
* The Deployment see that there is a new image and the start a rolling deployment
* The deployment is responsible to sprecify the properties with witch the actuall application pod will be instantiated.
* The Service (similar to a load balancer) references one or more Pods
* The Route is the mapping of the fully qualified domain name to a Service





### Create a route

```
oc expose srv/[SERVICE-NAME]
oc get route
```

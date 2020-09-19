# Java Best Practices and naming conventions

## Java Package Naming Conventions

This s an example of commonly use naming convention for Java packages:

```java
package [reversed.domain.name].[department_name].[functional_area].[application_name];
```

where:

* **\[reversed.domain.name\]** is the company reversed Internet domain name, so to avoid conflict with worldwide writing classes.
* **\[department\_name\]** is used to avoid collisions between departments in the same company
* **\[functional\_area\]** is used to avoid collisions between functional area in the same department 
* \[**application\_name\]** is used to avoid collisions between Git Repositories/application



\[department\_name\], \[functional\_area\] and \[application\_name\] follow the below conventions: 

* **all lower case** to avoid conflict with the names of classes or interfaces.
* **only alphanumeric** and **underscores \(\_\)** characters are allowed.
* packages **cannot begin with a number**.

For common/shared libraries it is possible to follow the following convention:

```java
package [reversed.domain.name].[department_name].shared.[library_name];
```

or 

```java
package [reversed.domain.name].shared.[library_name];
```




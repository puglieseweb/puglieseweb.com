---
description: >-
  This article explores how to build an application with a Spring Boot backend
  and React frontend, using Gradle as the master build tool.
---

# ReactJs

### Node

Node Package Manager (npm) can be used to as a boilerplate generator for React applications. &#x20;

The _`create-react-app`_ command can be used to generate a directory structure that looks something like this:

```
my-app/
  README.md
  node_modules/
  package.json
  public/
    index.html
    favicon.ico
  src/
    App.css
    App.js
    App.test.js
    index.css
    index.js
    logo.svg
```

To create a React application:

```
mkdir my-react-project cd my-react-project 
gradle init 
npx create-react-app frontend 
```

### ReactJs

React uses its own language called JSX.

The .js files inside the src/ directory, such as App.js and index.js, are actually JSX files. In modern React development, .js is the standard file extension used for files that contain JSX, even though the files contain JSX syntax.&#x20;

In the src directory, App.js is the main component of the application, and that's where most of the JSX code resides. index.js is the entry point to the application that renders the App component into the root element in index.html. However, feel free to rename these .js files to .jsx if it makes the distinction clearer for you. The tooling set up by create-react-app will understand either file extension.

#### Webpack and environment configuration

The create-react-app command is designed to provide an abstraction over the configuration and dependencies of a React app, including Webpack. This is to help users who do not want to handle configuration themselves, and just want a simple and quick way to start building a React app. It uses Webpack and Babel behind the scenes, but hides their configuration files to ensure simplicity.

create-react-app does not create a visible webpack.config.js or similar files in the project directory because it wants to maintain a zero-configuration setup. All of this is handled internally by react-scripts, a package that comes with create-react-app.

If you need to customize the Webpack configuration, you have two main options:

* Eject
* Use a Tool like CRACO (Create React App Configuration Override)
* Use custom-react-scripts or react-app-rewired



### Gradle

We need to compile JSX files into  regular Javascript that a browser's runtime can interpret.&#x20;

We want to remove the need to use use Node directly, and let Gradle compile the regular Javascript files. This is made possible by the [gradle-node-plugin](https://github.com/node-gradle/gradle-node-plugin/blob/main/docs/usage.md).

Finally we want to move the compiled Javascript files into the Spring Boot's resources folder:

```
/src/main/resources/static/
```



### Security

We offload JWT Authentication to an NGINX instance that fronts all the application with a JWT-based provider.


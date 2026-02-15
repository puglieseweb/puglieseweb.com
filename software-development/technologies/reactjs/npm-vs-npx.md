---
description: >-
  Understanding the difference between npm and npx, two essential tools in the
  Node.js ecosystem used in ReactJS development.
---

# npm vs npx

## npm (Node Package Manager)

npm is the default package manager for Node.js. It handles installing, updating, and managing project dependencies defined in `package.json`.

### Key responsibilities

* **Install packages** locally or globally
* **Manage dependencies** and their versions via `package.json` and `package-lock.json`
* **Run scripts** defined in the `scripts` section of `package.json`

### Common commands

```bash
# Install all dependencies listed in package.json
npm install

# Add a package as a project dependency
npm install react

# Add a package as a dev dependency
npm install --save-dev jest

# Install a package globally (available system-wide)
npm install -g typescript

# Run a script defined in package.json
npm run build
```

When you run `npm install <package>`, the package is downloaded into the `node_modules/` directory and recorded in `package.json`.

## npx (Node Package Execute)

npx is a package runner tool that ships with npm (version 5.2+). Instead of installing a package and then running it, npx lets you **execute packages directly** without a permanent installation.

### Key responsibilities

* **Run packages without installing them globally**
* **Execute one-off commands** from packages not in your project
* **Ensure the latest version** of a tool is used each time

### Common commands

```bash
# Create a new React application without installing create-react-app globally
npx create-react-app my-app

# Run a specific version of a package
npx typescript@5.0 tsc --init

# Run a locally installed package binary
npx jest --watch
```

## Key differences

| Aspect              | npm                                        | npx                                          |
| -------------------- | ------------------------------------------ | --------------------------------------------- |
| **Purpose**          | Install and manage packages                | Execute packages directly                     |
| **Installation**     | Downloads packages to `node_modules/` or globally | Downloads temporarily, executes, then discards |
| **Disk usage**       | Packages persist on disk                   | No permanent storage for one-off executions   |
| **Version**          | Uses the installed version                 | Can fetch and run any specific version         |
| **Typical use case** | Adding libraries to a project              | Running CLI tools and project generators       |

## Practical example in React development

Before npx, creating a new React app required two steps:

```bash
# Step 1: Install the generator globally
npm install -g create-react-app

# Step 2: Use the generator
create-react-app my-app
```

The problem with this approach is that the globally installed `create-react-app` could become outdated, leading to projects scaffolded with old templates and dependencies.

With npx, this becomes a single step that always uses the latest version:

```bash
npx create-react-app my-app
```

## When to use each

* **Use npm** when you need a package as part of your project (a dependency your code imports) or when you need a tool available permanently on your system.
* **Use npx** when you want to run a CLI tool or generator once without polluting your global installations, or when you want to ensure you are always running the latest version.

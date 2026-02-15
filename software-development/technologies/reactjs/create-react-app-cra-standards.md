---
description: >-
  Overview of the conventions, project structure, and tooling standards enforced
  by Create React App (CRA).
---

# Create React App (CRA) standards

Create React App (CRA) is the official React scaffolding tool maintained by Facebook. It enforces a set of conventions and standards so that developers can focus on writing application code rather than configuring build tooling.

## Project structure standard

CRA generates a standardized directory layout:

```
my-app/
  node_modules/
  public/
    index.html        # Single HTML entry point
    favicon.ico
    manifest.json     # PWA metadata
  src/
    App.css
    App.js            # Root component
    App.test.js       # Co-located test file
    index.css
    index.js          # Application entry point
    reportWebVitals.js
    setupTests.js     # Test framework configuration
  package.json
  README.md
```

Key conventions:

* **`public/`** — Static assets served as-is. Only `index.html` is required. Files here are not processed by Webpack.
* **`src/`** — All application source code. Only files inside `src/` are processed by Webpack and included in the production bundle.
* **`src/index.js`** — The JavaScript entry point. CRA expects this file to exist.

## Build and tooling standards

CRA abstracts the build configuration through the `react-scripts` package, which bundles:

* **Webpack** — Module bundler with pre-configured loaders for JS, CSS, images, and fonts
* **Babel** — JavaScript compiler with presets for JSX, modern ES syntax, and browser polyfills
* **ESLint** — Linter with the `eslint-config-react-app` ruleset enabled by default
* **Jest** — Test runner configured with JSDOM for component testing
* **PostCSS** — Autoprefixer for cross-browser CSS compatibility

## Script standards

CRA provides four predefined npm scripts:

| Script            | Command              | Purpose                                                  |
| ----------------- | -------------------- | -------------------------------------------------------- |
| `npm start`       | `react-scripts start` | Starts the development server with hot reloading on port 3000 |
| `npm test`        | `react-scripts test`  | Runs Jest in interactive watch mode                       |
| `npm run build`   | `react-scripts build` | Creates an optimized production build in `build/`         |
| `npm run eject`   | `react-scripts eject` | Exposes all configuration files (irreversible)            |

## Environment variable standard

CRA supports environment variables with a strict naming convention:

* Variables **must** be prefixed with `REACT_APP_` to be embedded in the build (e.g., `REACT_APP_API_URL`)
* Variables are loaded from `.env` files in a defined priority order: `.env.local` > `.env.development` / `.env.production` > `.env`
* `NODE_ENV` is set automatically (`development`, `test`, or `production`) and cannot be overridden

## Testing standard

* Test files are co-located with source files using the naming pattern `*.test.js` or `*.spec.js`
* Tests can also be placed in a `__tests__/` directory
* Jest is configured with JSDOM, enabling DOM assertions without a browser
* `setupTests.js` is automatically executed before every test suite

## Import resolution standards

* **Absolute imports** are supported by setting `"baseUrl": "src"` in `jsconfig.json` or `tsconfig.json`, allowing `import Button from 'components/Button'` instead of relative paths
* CSS, images, SVGs, and fonts can be imported directly into JavaScript files as modules
* CSS Modules are supported via the `*.module.css` naming convention

## CRA limitations and alternatives

CRA is no longer actively maintained (the last major release was v5 in December 2021). The React team now recommends framework-based approaches for new projects. Common alternatives include:

| Tool         | Description                                           |
| ------------ | ----------------------------------------------------- |
| **Vite**     | Fast build tool with native ES modules and HMR        |
| **Next.js**  | Full-stack React framework with SSR and file-based routing |
| **Remix**    | Full-stack framework focused on web standards and nested routing |

## References

* [Create React App official documentation](https://create-react-app.dev/)
* [CRA GitHub repository](https://github.com/facebook/create-react-app)
* [Adding custom environment variables](https://create-react-app.dev/docs/adding-custom-environment-variables/)
* [Running tests](https://create-react-app.dev/docs/running-tests/)
* [Folder structure](https://create-react-app.dev/docs/folder-structure/)
* [Available scripts](https://create-react-app.dev/docs/available-scripts/)
* [Supported browsers and features](https://create-react-app.dev/docs/supported-browsers-features/)
* [React documentation — Start a New React Project](https://react.dev/learn/start-a-new-react-project)

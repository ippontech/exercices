# dummy

## Node.js and NPM

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js](https://nodejs.org/): We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

- [Approaches](#approaches)

- [Package types](documentation/package-types.md)

- [Assertions](documentation/assertions.md)

- Testing:

  - [Cucumber](documentation/cucumber.md)

  - [Cypress](documentation/cypress.md)

  - [Code coverage](documentation/coverage.md)

- [Local run](#local)

- [Proxy](documentation/proxy.md)

<!-- jhipster-needle-documentation -->

<!-- jhipster-needle-readme -->

## <a id="approaches"></a>Approaches

- TDD (Test-Driven Design)
- Hexagonal architecture
- Non-anemic domain (strongly linked to Type-Driven Development)
- Trunk-Based Development (with short-lived feature branches): https://trunkbaseddevelopment.com/

## <a id="local"></a>Local run

By default application is run as a complete standalone app using Spring profiles
(that you can control explicitly by overriding active profiles):
```
  -Dspring.profiles.active=h2,mock
```

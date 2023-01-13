# Code coverage
## Backend
This is the real easy part: `jacoco-maven-plugin` handles almost everything. If you are not familiar with JVM agents,
it is the instrumentation of compiled code during runtime. It means "you run your deployed app (JAR), and you attach
a kind of spy (an agent) that will watch everything happening on the executed  code". This is widely used by production
monitoring solutions to provide performance and usage metrics (New Relic, AppDynamics, Dynatrace, DataDog, Azure AppInsight ...).

So you may say "what's the connection with code coverage?" It works the exact same way: JaCoCo (standing for Java Code
Coverage) will be our spy watching all the instructions being actually called by our tests, and thus determining those
that have not been called. Maven JaCoCo plugin provides a `prepare-agent` goal that we call during test execution. See
the `Jenkinsfile` pipeline such as:
```
mvn jacoco:prepare-agent@pre-unit-tests surefire:test
mvn jacoco:prepare-agent-integration@pre-integration-tests failsafe:integration-test@integration-test failsafe:verify
mvn jacoco:prepare-agent-integration@pre-integration-tests failsafe:integration-test@component-test failsafe:verify
```
Those lines literally mean "Maven, run unit tests (using Surefire) but don't forget to attach JaCoCo agent to spy
what's going on", "Maven do the same thing (using Failsafe) for integration tests and component tests".

When all kinds of tests from the testing pyramid are run, we'll check the global code coverage by merging the 3 reports
into one using other JaCoCo plugin goals:
```
mvn jacoco:report@post-unit-test jacoco:report-integration@post-integration-tests jacoco:merge@merge jacoco:report@post-merge-report jacoco:check@check
```
Meaning "JaCoCo, use the logs you create when watching tests to merge into a global report into a humanly-readable file
and check that our 100% code coverage is still there".

## Frontend
### Unit tests
Remember what instrumenting is? For Jest (used for unit testing), magic occurs. This is native, we just have to tell
Jest to `jest.config.js` to collect coverage and report a raw JSON in our coverage folder.
```javascript
collectCoverage: true,
collectCoverageFrom: ['./src/main/webapp/**/*.ts'],
coverageDirectory: '<rootDir>/coverage/jest',
coverageReporters: ['json'],
```

### Component tests
This becomes really messy right now for component testing. Cypress does not provide instrumentation at all. So we need to
make it happening using Webpack, NYC (Istanbul) and hopefully some Cypress dependencies. Instead of "just running Cypress",
we need to run our frontend using Webpack, tell it to instrument our transpiled code using Istanbul, then ask Cypress libs
to fetch the `window.__coverage__` property written inside the Cypress web browser, in order to save in a file to be reported
later using NYC again.

1. `angular.json` uses the `@angular-builders/custom-webpack:browser` instead of `@angular-devkit/build-angular:browser`.
   It is quite the same as the first extends the latter, but allows configuration extensions.
   When running the `e2e` configuration, we specify our custom Webpack configuration:
```
"customWebpackConfig": {
  "path": "./src/test/javascript/integration/coverage.webpack.ts"
}
```
2. `coverage.webpack.ts` is Webpack used for transpiling Typescript and instrumenting it using Istanbul. Files instrumented
   are configured using the include minus exclude properties and are relative to the file location (this is the most important detail):
```
include: path.join(__dirname, '..', '..', '..', 'main', 'webapp'),
```
3. `support.ts` is a Cypress support file loaded for all Cypress tests, embedding official Cypress code coverage instrumentation
```typescript
import '@cypress/code-coverage/support';
```
4. `cypress-config.ts` configure a task before and after all Cypress tests to save the browser `window.__coverage__`
   code coverage results an `.nycrc_output` folder.
5. Reporting is done by NYC on the scope of included minus excluded files configured through `.nycrc` (pathes are relative
   to NYC configuration location):
```javascript
"include": [ "../../../../src/main/webapp/**" ],
"exclude": [ "**/*.spec.ts" ],
"reporter": ["json"],
"report-dir": "../../../../coverage/cypress"
```
6. Finally, time to combine the Jest and Cypress reports! We use NYC to combine reports and generate a beautiful HTML file
   that we can check. As NYC takes a folder of files, we previously need to copy Jest & Cypress coverages in a single folder:
```
"copy:reports": "cp coverage/cypress/coverage-final.json coverage/cypress-coverage.json && cp coverage/jest/coverage-final.json coverage/jest-coverage.json",
"coverage:report": "npm run copy:reports && nyc merge coverage coverage/combined/coverage-combined.json && nyc report -t coverage/combined --reporter=html",
```

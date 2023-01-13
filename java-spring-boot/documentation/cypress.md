## Cypress
Cypress defines 2 types of tests: component and e2e (end-to-end). However, do NOT be misled by this naming if you 
understand the testing pyramid. In testing pyramid, an component test is a blackbox test against our frontend or against 
our backend as they are two components. In Cypress, a component test refers to frontend components such as Angular 
components. Thus when we want to "component test" our frontend as per the definition of the testing pyramid, it is in 
fact a Cypress e2e test with mocked backend calls.

### Schematic
Cypress `@cypress/schematic` is an official Cypress dependency easing the wrapping of Cypress configuration. Running 
a Cypress test means in fact two things: that the frontend is running using for example `ng serve`, and that the test
can actually connect to this frontend using `cypress open` (for interactive visual mode, used for double loop BDD TDD)
or using `cypress run` (for CI background mode).

In `angular.json`, schematic allows to specify the run target to serve the frontend, location of the Cypress 
configuration file to detect the e2e tests, and headless (CI/background) or interactive (visual in browser) modes. 
```json
"options": {
  "configFile": "src/test/javascript/integration/cypress-config.ts",
  "devServerTarget": "angular-project:serve-coverage",
  "watch": true,
  "headless": false,
  "browser": "chrome"
}
```

### Basics
We use Cypress in pair with double loop BDD TDD development practice (also called Outside-in TDD (in opposite to Inside-out TDD)
or London school TDD (in opposite to Chicago school TDD)).
Cypress is very effective at making our frontend conception emerge by defining the most straightforward end-user behavior
in the form of a given/when/then.
Always stay very concise and high-level. Component testing has a slow feedback loop thus should be used to make the global
behavior and code design emerge. A simple test such as this one but allow to make the whole hexagonal architecture emerge:
```typescript
describe('Marketplace', () => {
  it('displays all solutions by default', () => {
    cy.intercept('GET', '/api/solutions', [{"id": "solution1"}, {"id": "solution2"}]);
    
    cy.visit('/solutions');
    
    cy.get('.solution').should('have.length', 2);
  });
});
```
In addition, the benefit of double loop BDD TDD is that "you can push your feature as soon as a component test is OK",
because it means you successfully implement value through a complete loop. Again, to maintain velocity, keep your test 
very simple and straightforward.

### Debugging Cypress configuration (not your test)
For debugging Cypress execution on Windows for example to understand proxy settings, you can transform the NPM script such as:

```
# in package.json:
"cy": "set DEBUG=cypress:* && cypress open --e2e --browser chrome --config-file src/test/javascript/integration/cypress-config.ts",
# then run
npm run cy
```

Or without NPM by inlining Cypress execution:
```
DEBUG=cypress:* cypress open --e2e --browser chrome --config-file src/test/javascript/integration/cypress-config.ts
```

# Troubleshooting proxy
An enterprise proxy is a lot of wasted time for developers. Hope you can find at least some tips here to not lose as much
time as we did. You must understand that proxy settings are different when using regular applications such as web browsers,
when developing in Maven/Gradle contexts, and when developing in NPM/Yarn contexts. It is also different when you're 
developing on your local computer, or when the project runs in CI on a dedicated network environment...

## Environments variables
Very your proxy is not set as an environment variable:
```shell
echo $HTTP_PROXY
echo $HTTPS_PROXY
```
If anything is defined here, make sure to check the system environment variables and user environment variable.
For Windows users: double-check that you control the user environment variables for your local account, and not for your
administrative account if you opened the settings using an administrative account.

## NPM proxy
NPM settings can be defined at several levels. In the project root directory, it is interesting to check which settings
are used, that can be defined at the "global", "user" and "project" levels:
```shell
 npm config ls -l | grep proxy
```
The output should look something like:
```shell
https-proxy = null 
metrics-registry = "https://<nexus-url>/repository/npm-proxy/" 
noproxy = [""]                                                               
proxy = null                                                                 
registry = "https://<nexus-url>/repository/npm-proxy/"
```
* Global level means: `npm config --global get https-proxy`
* User level means: `~/.npmrc` file
* Project level means: `./npmrc` file

## Cypress proxy
### Cypress install
`cypress install` may take forever and do absolutely nothing. If that's the case, try running in debug and check proxy:
```
DEBUG=cypress:* cypress install
```
A working behavior is that the proxy is unset next to the download URL, such as:
```
  cypress:cli Downloading package {
  url: 'https://cdn.cypress.io/desktop/12.1.0/win32-x64/cypress.zip',
  proxy: null,
```

### Cypress run
If you configure a proxy for NPM using `.npmrc` settings, Cypress will inherit this proxy when ran inside an NPM script.
Cypress must absolutely not be run using a proxy because our component testing is made on localhost. Or make sure the `noproxy`
setting is well defined on localhost.
Configuring a proxy for Cypress will result on Cypress not being able to detect the application served on `http://localhost:9000`  
If you have this kind of issue, you can confirm it is a proxy configuration problem by first serving the application `npm start`
then running Cypress outside of NPM: `cypress open [...]` - this should work without proxy.

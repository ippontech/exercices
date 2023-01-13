# WebApp

Angular web app is served by backend server.

## Building process

During the build, the angular app is built and the output (a set of images and html/js/css files) is copied to `/static`
folder.
Those files will be served by the backend server.
They will be identical for all users.

## Expected behaviour

When the user opens the wep app in the browser :

- the request `https://root/foo/bar` will be sent to the server
- the server will return the content of the file `static/index.html`
- all files used in `index.html` will issue a new request
- the request `https://root/dummy.js` will be sent to the server
- the server will return the content of the file `static/dummy.js`

Once all the files are downloaded, the correct page (depending on the route) will be displayed to the user.

## SpringBoot default mechanism

SpringBoot default mechanism handles all file requests (`https://root/dummy.js`) :

- the request will be handled by `ResourceHttpRequestHandler`
- the handler will use `PathResourceResolver` to fetch the requested file in known classpath resource folders (`/static`
  is one of them)

The special case comes from the 'main' request `https://root/foo/bar` : no such file exists in the folder.

## Solution

A custom `ResourceResolver` has been added to fetch `index.html` if no specific file has been requested.

This allows us to keep the default mechanism :

- the request is still handled by `ResourceHttpRequestHandler`
- the handler will use `WebAppRessouceResolver` to fetch the file
  - if no specific file has been requested then `index.html` is requested
  - the actual file fetching is delegated to `PathResourceResolver`

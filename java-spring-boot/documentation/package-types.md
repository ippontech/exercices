# Package types

This application comes with 2 package level annotations:  

* `SharedKernel` used to mark packages containing classes shared between multiple contexts;
* `BoundedContext` used to mark packages containing classes to answer a specific bounded context. Classes in this package can't be used in other bounded context.

To mark a package you have to add a `package-info.java` file at the package root with:  

```java
@{{ basePackage }}.SharedKernel
package com.ippon;
```

You can also check the `HexagonalArchTest` describing the way of architecting our code.
Please note architecture is a living thing adapting to business needs, environments context, team methodologies [...],
thus you are encouraged to discuss architecture subjects with all the developers, decide together of the things that
will help you keeping an easy-to-maintain-and-evolve application, then enforcing your way of doing in arch unit tests.


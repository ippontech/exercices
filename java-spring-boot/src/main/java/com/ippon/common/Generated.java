package com.ippon.common;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used to exclude classes or methods from jacoco coverage
 */

@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Generated {
  String reason() default "";
}

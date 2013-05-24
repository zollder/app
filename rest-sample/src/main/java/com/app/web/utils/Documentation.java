package com.app.web.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//--------------------------------------------------------------------------------------------------------------------------------
/**
 * Documentation annotation. To be used until JAXB provides a mechanism to document entities, properties and/or methods.
 */
// --------------------------------------------------------------------------------------------------------------------------------
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Documentation
{
	String caption();
	String comment();
}
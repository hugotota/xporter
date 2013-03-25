package ht.xporter.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(value = { METHOD, FIELD })
public @interface ExcelColumn {
	String label();
	int order() default 0;
	String nested() default "";
	boolean header() default true;
}
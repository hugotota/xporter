package ht.xporter.excel.annotation;

import static ht.xporter.excel.mapping.ExcelStyleWrapper.BorderFill.NONE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import ht.xporter.excel.mapping.ExcelStyleWrapper.BorderFill;
import ht.xporter.excel.style.CellType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(value = FIELD)
public @interface ExcelStyle {
	CellType cellType();
	String pattern() default "";
	BorderFill border() default NONE;	
}
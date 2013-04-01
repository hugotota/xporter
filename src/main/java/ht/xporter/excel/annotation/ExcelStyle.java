package ht.xporter.excel.annotation;

import static ht.xporter.excel.mapping.ExcelStyleWrapper.BorderFill.NONE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import ht.xporter.excel.mapping.ExcelStyleWrapper.BorderFill;
import ht.xporter.excel.style.CellType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation for configuration of excel styles
 * 
 * @author hugo.tota@gmail.com
 * @version 1.0
 */
@Retention(RUNTIME)
@Target(value = FIELD)
public @interface ExcelStyle {
	/**
	 * 
	 * 
	 * @return  
	 */
	CellType cellType();
	String pattern() default "";
	BorderFill border() default NONE;	
}
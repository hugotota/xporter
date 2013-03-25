package ht.xporter.excel.style;

import java.math.BigDecimal;
import java.math.BigInteger;

public enum CellType {
	DATE(java.util.Date.class, Date.class), 
	LONG(Long.class, Number.class),
	STRING(String.class, Text.class),
	DOUBLE(Double.class, Decimal.class),
	INTEGER(Integer.class, Number.class),
	BIGINTEGER(BigInteger.class, Number.class), 
	BIGDECIMAL(BigDecimal.class, Decimal.class); 

	private CellType(Class<?> clazz, Class<? extends Style> style) {
		this.style = style;
		this.clazz = clazz;
	}

	private Class<?> clazz;
	private Class<? extends Style> style;

	public Class<? extends Style> getStyle() {
		return style;
	}

	public Class<?> getClazz() {
		return clazz;
	}
}
package ht.xporter.excel.assembly;

import ht.xporter.excel.config.WorkbookConfig;

public interface Assembly {
	void clear();

	Assembly build();

	Assembly addClasses(Class<?> clazz);

	WorkbookConfig getClassConfig(Class<?> clazz);
}
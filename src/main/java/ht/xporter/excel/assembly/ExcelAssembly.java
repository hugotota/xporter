package ht.xporter.excel.assembly;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import ht.xporter.excel.annotation.ExcelColumn;
import ht.xporter.excel.annotation.ExcelReport;
import ht.xporter.excel.annotation.ExcelStyle;
import ht.xporter.excel.config.WorkbookConfig;
import ht.xporter.excel.i18n.I18n;
import ht.xporter.excel.mapping.ExcelColumnWrapper;
import ht.xporter.excel.mapping.ExcelStyleWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Constraints;

public class ExcelAssembly implements Assembly {
	private I18n i18n;
	private List<Class<?>> classes;
	private Map<Class<?>, WorkbookConfig> mapping;

	public ExcelAssembly() {
		i18n = new I18n();
		mapping = new HashMap<Class<?>, WorkbookConfig>();
		classes = Constraints.constrainedList(new ArrayList<Class<?>>(), Constraints.notNull());
	}

	protected void process() {
		if (!classes.isEmpty()) {
			for (Class<?> clazz : classes) {
				WorkbookConfig config = new WorkbookConfig();

				ExcelReport annotation = (ExcelReport) clazz.getAnnotation(ExcelReport.class);

				if (annotation == null) {
					throw new IllegalArgumentException(i18n.getMessage("xporter.invalid.class", clazz.getName()));
				}

				config.setWorkbookName(annotation.name());

				for (Method method : clazz.getDeclaredMethods()) {
					ExcelColumn column = method.getAnnotation(ExcelColumn.class);

					if (column != null) {
						config.add(method.getName(), new ExcelColumnWrapper
								.Builder()
								.property(method.getName())
								.label(column.label())
								.nested("")
								.path(method.getName())
								.order(column.order())
								.type(METHOD)
								.build());
					}
				}

				StringBuilder path = new StringBuilder();

				for (Field field : clazz.getDeclaredFields()) {
					ExcelColumn column = field.getAnnotation(ExcelColumn.class);
					ExcelStyle style =  field.getAnnotation(ExcelStyle.class);

					if (column != null) {
						String nested = column.nested();
						path.append(field.getName());

						if (!"".equals(nested)) {
							path.append(".").append(nested);
						}

						config.add(field.getName(), new ExcelColumnWrapper
								.Builder()
								.label(column.label())
								.nested(column.nested())
								.path(path.toString())
								.order(column.order())
								.header(column.header())
								.type(FIELD)
								.build());

						if (style != null) {
							config.add(field.getName(), new ExcelStyleWrapper
									.Builder()
									.column(column.label())
									.pattern(style.pattern())
									.border(style.border())
									.cellType(style.cellType())
									.build());
						}
					}

					path.setLength(0);
				}

				mapping.put(clazz, config);
			}
		}
	}

	public void clear() {
		if (classes != null && !classes.isEmpty()) {
			classes.clear();
		}

		if (mapping != null && !mapping.isEmpty()) {
			mapping.clear();
		}
	}

	public Assembly build() {
		process();
		return this;
	}

	public WorkbookConfig getClassConfig(Class<?> clazz) {
		return mapping.get(clazz);
	}

	public Assembly addClasses(Class<?> clazz) {
		classes.add(clazz);
		return this;
	}
}
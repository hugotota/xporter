package ht.xporter.excel.config;

import ht.xporter.excel.mapping.ExcelColumnWrapper;
import ht.xporter.excel.mapping.ExcelStyleWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkbookConfig {
	private String workbookName;
	private Map<String, ExcelColumnWrapper> columns;
	private Map<String, ExcelStyleWrapper>  styles;

	public WorkbookConfig() {
		styles = new HashMap<String, ExcelStyleWrapper>();
		columns = new HashMap<String, ExcelColumnWrapper>();
	}

	public String getWorkbookName() {
		return workbookName;
	}

	public void setWorkbookName(String workbookName) {
		this.workbookName = workbookName;
	}

	public void add(String property, ExcelColumnWrapper column) {
		columns.put(property, column);
	}

	public ExcelColumnWrapper getColumn(String property) {
		return columns.get(property);
	}

	public void add(String property, ExcelStyleWrapper style) {
		styles.put(property, style);
	}

	public ExcelStyleWrapper getStyle(String property) {
		return styles.get(property);
	}

	public List<ExcelStyleWrapper> getStyles() {
		return new ArrayList<ExcelStyleWrapper>(styles.values());
	}

	public List<ExcelColumnWrapper> getColumns() {
		List<ExcelColumnWrapper> list = new ArrayList<ExcelColumnWrapper>(columns.values());
		Collections.<ExcelColumnWrapper>sort(list);
		return list;
	}

	public boolean hasStyles() {
		return styles.size() > 0;
	}

	public boolean hasColumns() {
		return columns.size() > 0;
	}
}
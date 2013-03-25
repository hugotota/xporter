package ht.xporter.excel.style;

import ht.xporter.excel.mapping.ExcelStyleWrapper;
import ht.xporter.excel.mapping.ExcelStyleWrapper.BorderFill;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class PoiCase {
	private SXSSFWorkbook workbook;
	private ExcelStyleWrapper style;

	public PoiCase(SXSSFWorkbook workbook, ExcelStyleWrapper style) {
		this.style = style;
		this.workbook = workbook;
	}

	public CellStyle cellStyle() {
		return workbook.createCellStyle();
	}

	public CreationHelper creationHelper() {
		return workbook.getCreationHelper();
	}

	public String column() {
		return style.getColumn();
	}

	public String pattern() {
		return style.getPattern();
	}

	public BorderFill border() {
		return style.getBorder();
	}

	public CellType type() {
		return style.getCellType();
	}
}
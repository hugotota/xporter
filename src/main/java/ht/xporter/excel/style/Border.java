package ht.xporter.excel.style;

import ht.xporter.excel.mapping.ExcelStyleWrapper;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

class Border {
	public static void fill(CellStyle style, ExcelStyleWrapper.BorderFill border) {
		if (ExcelStyleWrapper.BorderFill.NONE.equals(border)) { return; }

		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	}

	public static void fill(CellStyle style) {
		fill(style, ExcelStyleWrapper.BorderFill.ALL);
	}
}
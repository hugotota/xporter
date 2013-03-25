package ht.xporter.excel.builder;

import ht.xporter.excel.assembly.Assembly;
import ht.xporter.excel.config.WorkbookConfig;
import ht.xporter.excel.i18n.I18n;
import ht.xporter.excel.mapping.ExcelColumnWrapper;
import ht.xporter.excel.mapping.ExcelStyleWrapper;
import ht.xporter.excel.style.Cache;
import ht.xporter.excel.style.Creator;
import ht.xporter.excel.style.Header;
import ht.xporter.excel.style.PoiCase;
import ht.xporter.excel.style.Style;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class ExcelWorkbookFactory {
	private static final int EXCEL_ROW_LIMIT = 1048576;
	private static final int DEFAULT_FLUSH_BLOCK_SIZE = 500;
	private int flushBlockSize;

	private I18n i18n;
	private Assembly assembly;
	private SXSSFWorkbook workbook;

	public ExcelWorkbookFactory(Assembly assembly) {
		i18n = new I18n();
		workbook = new SXSSFWorkbook();
		this.assembly = assembly;
	}

	public ExcelWorkbookFactory(Assembly assembly, SXSSFWorkbook workbook) {
		i18n = new I18n();
		this.workbook = workbook;
		this.assembly = assembly;
	}

	public <T> Workbook create(List<T> data) throws Exception {
		if (data == null || data.isEmpty()) {
			throw new IllegalArgumentException(i18n.getMessage("xporter.invalid.collection"));
		}

		if (data.size() > EXCEL_ROW_LIMIT) {
			throw new IllegalArgumentException(i18n.getMessage("xporter.invalid.number.of.lines"));
		}

		if (this.flushBlockSize < 1) {
			this.flushBlockSize = DEFAULT_FLUSH_BLOCK_SIZE;
		}

		final Class<?> clazz = data.get(0).getClass();

		WorkbookConfig config = assembly.getClassConfig(clazz);

		List<ExcelColumnWrapper> columns = config.getColumns();

		if (workbook == null) {
			workbook = new SXSSFWorkbook(-1);
		}

		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();

		int rowCount = 0;
		int columnCount = 0;
		int flushBlockCount = 1;

		SXSSFRow row = (SXSSFRow) sheet.createRow(rowCount++);

		List<ExcelStyleWrapper> styles = config.getStyles();
		for (ExcelStyleWrapper style : styles) {
			PoiCase poiCase = new PoiCase(workbook, style);
			Creator creator = new Creator(poiCase);
			Cache.instance().addToCache(creator.column(), creator.style());
		}

		for (ExcelColumnWrapper column : columns) {
			String header = column.getLabel();
			SXSSFCell cell = (SXSSFCell) row.createCell(columnCount++);
			cell.setCellValue(header);
			if (column.getHeader()) {
				cell.setCellStyle(new Header(workbook).get());
			}
		}

		for (T entity : data) {
			row = (SXSSFRow) sheet.createRow(rowCount++);
			flushBlockCount++;
			columnCount = 0;

			for (ExcelColumnWrapper column : columns) {
				try {
					SXSSFCell cell = (SXSSFCell) row.createCell(columnCount++);
					Object value = Ognl.getValue(column.getPath(), entity);
					addCell(value, cell, column.getLabel());
				} catch (OgnlException e) {
					throw new RuntimeException(i18n.getMessage("xporter.invalid.path", column.getPath()), e);
				}
			}

			if (flushBlockCount == flushBlockSize) {
				sheet.flushRows();
				flushBlockCount = 0;
			}
		}

		sheet.flushRows();

		return new Workbook(new XLSXExtension(config.getWorkbookName()), workbook);
	}

	private void addCell(Object value, SXSSFCell cel, String column) {
		Style style = Cache.instance().getFromCache(column);

		if (value != null) {
			if (value instanceof String) {
				cel.setCellValue(new XSSFRichTextString((String) value));
			} else if (value instanceof Long) {
				cel.setCellValue((Long) value);
			} else if (value instanceof Integer) {
				cel.setCellValue((Integer) value);
			} else if (value instanceof Double) {
				cel.setCellValue((Double) value);
			} else if (value instanceof BigDecimal) {
				cel.setCellValue(((BigDecimal) value).doubleValue());
			} else if (value instanceof BigInteger) {
				cel.setCellValue(((BigInteger) value).intValue());
			} else if (value instanceof Date) {
				cel.setCellValue((Date) value);
			}

			if (style != null) { 
				cel.setCellStyle(style.get()); 
			}
		}
	}
}
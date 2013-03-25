package ht.xporter.excel.builder;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class Workbook {
	private XLSXExtension name;
	private SXSSFWorkbook workbook;

	public Workbook(XLSXExtension name, SXSSFWorkbook workbook) {
		this.name = name;
		this.workbook = workbook;
	}

	public XLSXExtension xlsx() {
		return name;
	}

	public void write(OutputStream out) throws IOException {
		workbook.write(out);
	}
}
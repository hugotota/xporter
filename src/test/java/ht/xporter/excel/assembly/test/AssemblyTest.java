package ht.xporter.excel.assembly.test;

import ht.xporter.excel.annotation.ExcelColumn;
import ht.xporter.excel.annotation.ExcelReport;
import ht.xporter.excel.assembly.Assembly;
import ht.xporter.excel.assembly.ExcelAssembly;
import ht.xporter.excel.config.WorkbookConfig;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssemblyTest {
	@Test
	public void assemblyBuildTest() {
		Assembly assembly = new ExcelAssembly().addClasses(ExcelWhitoutReportName.class).build();
		WorkbookConfig config = assembly.getClassConfig(ExcelWhitoutReportName.class);
		Assert.assertEquals(config.getColumns().size(), 1);
	}

	@Test
	public void assemblyColumnNameTest() {
		Assembly assembly = new ExcelAssembly().addClasses(ExcelWhitoutReportName.class).build();
		WorkbookConfig config = assembly.getClassConfig(ExcelWhitoutReportName.class);
		Assert.assertEquals(config.getColumns().get(0).getLabel(), "Column");
	}

	@Test
	public void assemblyReportNameTest() {
		Assembly assembly = new ExcelAssembly().addClasses(ExcelWhitoutReportName.class).build();
		WorkbookConfig config = assembly.getClassConfig(ExcelWhitoutReportName.class);
		Assert.assertEquals(config.getWorkbookName(), "excel-report");
	}

	@Test
	public void assemblyWithNameBuildTest() {
		Assembly assembly = new ExcelAssembly().addClasses(ExcelWithReportName.class).build();
		WorkbookConfig config = assembly.getClassConfig(ExcelWithReportName.class);
		Assert.assertEquals(config.getColumns().size(), 2);
	}

	@Test
	public void assemblyTwoColumnNameTest() {
		Assembly assembly = new ExcelAssembly().addClasses(ExcelWithReportName.class).build();
		WorkbookConfig config = assembly.getClassConfig(ExcelWithReportName.class);
		Assert.assertEquals(config.getColumns().get(0).getLabel(), "Column 1");
		Assert.assertEquals(config.getColumns().get(1).getLabel(), "Column 2");
	}

	@Test
	public void assemblyCustomReportNameTest() {
		Assembly assembly = new ExcelAssembly().addClasses(ExcelWithReportName.class).build();
		WorkbookConfig config = assembly.getClassConfig(ExcelWithReportName.class);
		Assert.assertEquals(config.getWorkbookName(), "ExcelReport");
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void notExcelReport() {
		new ExcelAssembly().addClasses(NoReport.class).build();
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void addNullToClassesTest() {
		new ExcelAssembly().addClasses(null).build();
	}

	public static class NoReport {} 

	@ExcelReport 
	public static class ExcelWhitoutReportName { @ExcelColumn(label = "Column") private String column; }

	@ExcelReport(name = "ExcelReport")
	public static class ExcelWithReportName {
		@ExcelColumn(label = "Column 1") private String column1;
		@ExcelColumn(label = "Column 2") private String column2;
	}
}
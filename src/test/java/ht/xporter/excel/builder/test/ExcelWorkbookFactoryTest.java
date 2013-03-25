package ht.xporter.excel.builder.test;

import static ht.xporter.excel.style.CellType.BIGDECIMAL;
import static ht.xporter.excel.style.CellType.DATE;
import static ht.xporter.excel.style.CellType.INTEGER;
import static ht.xporter.excel.style.CellType.STRING;
import ht.xporter.excel.annotation.ExcelColumn;
import ht.xporter.excel.annotation.ExcelReport;
import ht.xporter.excel.annotation.ExcelStyle;
import ht.xporter.excel.assembly.Assembly;
import ht.xporter.excel.assembly.ExcelAssembly;
import ht.xporter.excel.builder.ExcelWorkbookFactory;
import ht.xporter.excel.builder.Workbook;
import ht.xporter.excel.mapping.ExcelStyleWrapper.BorderFill;
import ht.xporter.excel.style.CellType;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExcelWorkbookFactoryTest {
	private List<Excel> data;

	@BeforeClass
	public void init() {
		data = new LinkedList<Excel>();
		data.addAll(Arrays.asList(
				new Excel(new Date(), "some long string value 1", new Integer(9), new BigDecimal(1), new Nest("nest 1")),
				new Excel(new Date(), "some long string value 2", new Integer(99), new BigDecimal(11), new Nest("nest 2")), 
				new Excel(new Date(), "some long string value 3", new Integer(999), new BigDecimal(111), new Nest("nest 3")))
		);

		StringBuilder filePath = new StringBuilder()
			.append(new File("").getAbsolutePath()).append("/excel.xlsx");
		if (new File(filePath.toString()).exists()) {
			new File(filePath.toString()).delete();
		}
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Data collection should not be null.*")
	public void nullDataTest() throws Exception {
		ExcelWorkbookFactory factory = new ExcelWorkbookFactory(Mockito.mock(Assembly.class));
		factory.create(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp= "Data collection has more rows than Excel suports")
	public void excelLimitTest() throws Exception {
		List<?> list = Mockito.mock(List.class);
		Assembly assembly = Mockito.mock(Assembly.class);
		Mockito.when(list.size()).thenReturn(1048577);
		ExcelWorkbookFactory factory = new ExcelWorkbookFactory(assembly);
		factory.create(list);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Data collection should not be null.*")
	public void emptyDataTest() throws Exception {
		Assembly assembly = Mockito.mock(Assembly.class);
		ExcelWorkbookFactory factory = new ExcelWorkbookFactory(assembly);
		factory.create(new ArrayList<String>());
	}

	@Test
	public void exportTest() throws Exception {
		Assembly assembly = new ExcelAssembly()
			.addClasses(Excel.class)
			.build();

		ExcelWorkbookFactory factory = new ExcelWorkbookFactory(assembly);
		Workbook workbook = factory.create(data);

		StringBuilder filePath = new StringBuilder()
			.append(new File("").getAbsolutePath())
			.append("/")
			.append(workbook.xlsx().filename());

		workbook.write(new FileOutputStream(filePath.toString()));

		Assert.assertTrue(new File(filePath.toString()).exists());
	}

	@ExcelReport(name = "excel")
	public static class Excel {
		@ExcelColumn(label = "Data") 
		@ExcelStyle(border = BorderFill.ALL, pattern = "mmm/dd/yyyy", cellType = DATE)
		private Date date;
		@ExcelColumn(label = "String") 
		@ExcelStyle(border = BorderFill.ALL, cellType = STRING)
		private String string;
		@ExcelColumn(label = "Integer") 
		@ExcelStyle(border = BorderFill.ALL, cellType = INTEGER)
		private Integer integer;
		@ExcelColumn(label = "BigDecimal") 
		@ExcelStyle(border = BorderFill.ALL, pattern = "0.00", cellType = BIGDECIMAL)
		private BigDecimal bigDecimal;
		@ExcelColumn(label = "Nested", nested = "nest", order = 90, header = false)
		@ExcelStyle(cellType = CellType.STRING)
		private Nest nest;

		public Excel(Date date, String string, Integer integer, BigDecimal bigDecimal, Nest nest) {
			this.date = date;
			this.string = string;
			this.integer = integer;
			this.bigDecimal = bigDecimal;
			this.nest = nest;
		}

		public Date getDate() { return date; }
		public void setDate(Date date) { this.date = date; }
		public String getString() { return string; }
		public void setString(String string) { this.string = string; }
		public Integer getInteger() { return integer; }
		public void setInteger(Integer integer) { this.integer = integer; }
		public BigDecimal getBigDecimal() { return bigDecimal; }
		public void setBigDecimal(BigDecimal bigDecimal) { this.bigDecimal = bigDecimal; }
		public Nest getNest() { return nest; }
		public void setNest(Nest nest) { this.nest = nest; }
	}

	public static class Nest {
		private String nest;
		
		public Nest(String nest) { this.nest = nest; }
		public String getNest() { return nest; }
		public void setNest(String nest) { this.nest = nest; }
	}
}
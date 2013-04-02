An easy way to export excel reports from your application, just use the annotations and done.

Example

 - Annotate your bean like this

```java
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

		//getters and setters removed
	}
```
 - Write the excel file like this

```java
	public void export(List<Excel> data) throws Exception {
		// add your annotated classes here
		Assembly assembly = new ExcelAssembly()
			.addClasses(Excel.class)
			.build();

		// create a factory
		ExcelWorkbookFactory factory = new ExcelWorkbookFactory(assembly);
		Workbook workbook = factory.create(data);

		// write your excel file
		workbook.write(new FileOutputStream(workbook.xlsx().filename()));
  	}
```

 - And the framework will create something like this pic

![ScreenShot](https://raw.github.com/hugotota/xporter/master/excel.jpeg)

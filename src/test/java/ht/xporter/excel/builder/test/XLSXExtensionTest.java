package ht.xporter.excel.builder.test;

import ht.xporter.excel.builder.XLSXExtension;

import org.testng.Assert;
import org.testng.annotations.Test;

public class XLSXExtensionTest {
	@Test(expectedExceptions = NullPointerException.class)
	public void nullXlsxTest() {
		new XLSXExtension(null);
	}

	@Test
	public void withExtension() {
		Assert.assertEquals(new XLSXExtension("bla").filename(), "bla.xlsx");
		Assert.assertEquals(new XLSXExtension("bla.l").filename(), "bla.xlsx");
		Assert.assertEquals(new XLSXExtension("bla.xlsx").filename(), "bla.xlsx");
		Assert.assertEquals(new XLSXExtension("bla.l.looo.lala.ihfd").filename(), "bla.l.looo.lala.xlsx");
	}
}
package ht.xporter.excel.mapping.test;

import ht.xporter.excel.mapping.ExcelColumnWrapper;

import java.lang.annotation.ElementType;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExcelColumnWrapperTest {
	@Test
	public void allFieldsNullTest() {
		ExcelColumnWrapper wrapper = new ExcelColumnWrapper();
		Assert.assertEquals(wrapper.getLabel(), null);
		Assert.assertEquals(wrapper.getNested(), null);
		Assert.assertEquals(wrapper.getPath(), null);
		Assert.assertEquals(wrapper.getOrder(), null);
		Assert.assertEquals(wrapper.getType(), null);
	}

	@Test
	public void allFieldsNullFromBuilderTest() {
		ExcelColumnWrapper wrapper = new ExcelColumnWrapper.Builder().build();
		Assert.assertEquals(wrapper.getLabel(), null);
		Assert.assertEquals(wrapper.getNested(), null);
		Assert.assertEquals(wrapper.getPath(), null);
		Assert.assertEquals(wrapper.getOrder(), null);
		Assert.assertEquals(wrapper.getType(), null);
	}

	@Test
	public void allFieldsFilledTest() {
		ExcelColumnWrapper wrapper = new ExcelColumnWrapper();
		wrapper.setLabel("label");
		wrapper.setNested("nested");
		wrapper.setOrder(new Integer(0));
		wrapper.setPath("path");
		wrapper.setType(ElementType.FIELD);

		Assert.assertEquals(wrapper.getLabel(), "label");
		Assert.assertEquals(wrapper.getNested(), "nested");
		Assert.assertEquals(wrapper.getPath(), "path");
		Assert.assertEquals(wrapper.getOrder(), new Integer(0));
		Assert.assertEquals(wrapper.getType(), ElementType.FIELD);
	}

	@Test
	public void allFieldsFilledFromBuilderTest() {
		ExcelColumnWrapper wrapper = new ExcelColumnWrapper
				.Builder()
				.label("label")
				.nested("nested")
				.order(new Integer(0))
				.path("path")
				.type(ElementType.FIELD)
				.build();

		Assert.assertEquals(wrapper.getLabel(), "label");
		Assert.assertEquals(wrapper.getNested(), "nested");
		Assert.assertEquals(wrapper.getPath(), "path");
		Assert.assertEquals(wrapper.getOrder(), new Integer(0));
		Assert.assertEquals(wrapper.getType(), ElementType.FIELD);
	}

	@Test
	public void allFieldsFilledFromBuilderOverlapTest() {
		ExcelColumnWrapper wrapper = new ExcelColumnWrapper
				.Builder()
				.label("label")
				.nested("nested")
				.order(new Integer(0))
				.path("path")
				.type(ElementType.FIELD)
				.build();

		Assert.assertEquals(wrapper.getLabel(), "label");
		Assert.assertEquals(wrapper.getNested(), "nested");
		Assert.assertEquals(wrapper.getPath(), "path");
		Assert.assertEquals(wrapper.getOrder(), new Integer(0));
		Assert.assertEquals(wrapper.getType(), ElementType.FIELD);

		wrapper = new ExcelColumnWrapper
				.Builder()
				.label("label1")
				.nested("nested1")
				.order(new Integer(1))
				.path("path1")
				.type(ElementType.METHOD)
				.build();

		Assert.assertEquals(wrapper.getLabel(), "label1");
		Assert.assertEquals(wrapper.getNested(), "nested1");
		Assert.assertEquals(wrapper.getPath(), "path1");
		Assert.assertEquals(wrapper.getOrder(), new Integer(1));
		Assert.assertEquals(wrapper.getType(), ElementType.METHOD);
	}

	@Test
	public void compareObjectEqualTest() {
		ExcelColumnWrapper compare = new ExcelColumnWrapper.Builder().order(new Integer(1)).build();
		ExcelColumnWrapper comparable = new ExcelColumnWrapper.Builder().order(new Integer(1)).build();

		Assert.assertEquals(0, compare.compareTo(comparable));
	}

	@Test
	public void compareObjectLessThanTest() {
		ExcelColumnWrapper compare = new ExcelColumnWrapper.Builder().order(new Integer(1)).build();
		ExcelColumnWrapper comparable = new ExcelColumnWrapper.Builder().order(new Integer(2)).build();

		Assert.assertTrue(compare.compareTo(comparable) < 0);
	}

	@Test
	public void compareObjectMoreThanTest() {
		ExcelColumnWrapper compare = new ExcelColumnWrapper.Builder().order(new Integer(1)).build();
		ExcelColumnWrapper comparable = new ExcelColumnWrapper.Builder().order(new Integer(0)).build();

		Assert.assertTrue(compare.compareTo(comparable) > 0);
	}
}
package ht.xporter.excel.i18n.test;

import java.util.Locale;

import ht.xporter.excel.i18n.I18n;

import org.testng.Assert;
import org.testng.annotations.Test;

public class I18nTest {
	@Test
	public void portugueseMessageTest() {
		I18n i18n = new I18n(new Locale("pt", "BR"));
		String message = i18n.getMessage("xporter.invalid.collection");
		Assert.assertEquals(message, "Os dados n\u00E3o podem estar nulos");
	}

	@Test
	public void defaultLocaleMessageTest() {
		I18n i18n = new I18n();
		String message = i18n.getMessage("xporter.invalid.collection");
		Assert.assertEquals(message, "Data collection should not be null");
	}

	@Test
	public void portugueseMessageWithObjectsTest() {
		I18n i18n = new I18n(new Locale("pt", "BR"));
		String message = i18n.getMessage("xporter.invalid.class", "I18nTest");
		Assert.assertEquals(message, "Classe I18nTest tem que estar anotada com @ExcelReport");
	}

	@Test
	public void defaultLocaleMessageWithObjectsTest() {
		I18n i18n = new I18n();
		String message = i18n.getMessage("xporter.invalid.class", "I18nTest");
		Assert.assertEquals(message, "Class I18nTest should be an @ExcelReport");
	}
}
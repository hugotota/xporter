package ht.xporter.excel.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {
	private ResourceBundle bundle;

	public I18n() {
		loadBundle(null);
	}

	public I18n(Locale locale) {
		loadBundle(locale);
	}

	public String getMessage(String key) {
		return (String) bundle.getObject(key);
	}

	public String getMessage(String key, Object... values) {
		String message = (String) bundle.getObject(key);
		return MessageFormat.format(message, values);
	}

	private void loadBundle(Locale locale) {
		if (locale == null) {
			bundle = ResourceBundle.getBundle("xporter", Locale.getDefault());
			return;
		}

		bundle = ResourceBundle.getBundle("xporter", locale);
	}
}
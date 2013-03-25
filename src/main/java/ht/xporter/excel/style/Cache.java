package ht.xporter.excel.style;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	private static Cache instance;
	private Map<String, Style> styles;

	private Cache() {
		styles = new HashMap<String, Style>();
	}

	public static Cache instance() {
		if (instance == null) {
			instance = new Cache();
		}

		return instance;
	}

	public void addToCache(String key, Style value) {
		styles.put(key, value);
	}

	public Style getFromCache(String key) { 
		return styles.get(key);
	}
}
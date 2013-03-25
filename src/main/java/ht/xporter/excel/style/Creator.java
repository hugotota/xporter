package ht.xporter.excel.style;

import java.lang.reflect.Constructor;

public class Creator {
	private PoiCase poiCase;

	public Creator(PoiCase poiCase) {
		this.poiCase = poiCase;
	}

	public Style style() throws Exception {
		Class<? extends Style> style = poiCase.type().getStyle();
		Constructor<? extends Style> constructor = style.getConstructor(PoiCase.class);
		return constructor.newInstance(poiCase);
	}

	public String column() {
		return poiCase.column();
	}
}
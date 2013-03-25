package ht.xporter.excel.builder;

public class XLSXExtension {
	private String name;

	public XLSXExtension(String name) {
		if (name == null) { throw new NullPointerException(); }
		this.name = name;
	}

	public String filename() {
		String finalName = "";
		if (name.contains(".")) { finalName = name.substring(0, name.lastIndexOf(".")); }
		if ("".equals(finalName)) { finalName = name; }

		return finalName + ".xlsx";
	}
}
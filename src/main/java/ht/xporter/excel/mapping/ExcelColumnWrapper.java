package ht.xporter.excel.mapping;

import java.lang.annotation.ElementType;

public class ExcelColumnWrapper implements Comparable<ExcelColumnWrapper> {
	private String path;
	private String label;
	private Integer order;
	private String nested;
	private Boolean header;
	private String property;
	private ElementType type;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getNested() {
		return nested;
	}

	public void setNested(String nested) {
		this.nested = nested;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public Boolean getHeader() {
		return header;
	}

	public void setHeader(Boolean header) {
		this.header = header;
	}

	public int compareTo(ExcelColumnWrapper wrapper) {
		return Integer.valueOf(this.order).compareTo(wrapper.getOrder());
	}

	public static class Builder {
		private ExcelColumnWrapper wrapper;

		public Builder() {
			wrapper = new ExcelColumnWrapper();
		}

		public Builder property(String property) {
			wrapper.setProperty(property);
			return this;
		}

		public Builder label(String label) {
			wrapper.setLabel(label);
			return this;
		}

		public Builder nested(String nested) {
			wrapper.setNested(nested);
			return this;
		}

		public Builder path(String path) {
			wrapper.setPath(path);
			return this;
		}

		public Builder order(Integer order) {
			wrapper.setOrder(order);
			return this;
		}

		public Builder type(ElementType type) {
			wrapper.setType(type);
			return this;
		}

		public Builder header(Boolean header) {
			wrapper.setHeader(header);
			return this;
		}

		public ExcelColumnWrapper build() {
			return wrapper;
		}
	}
}
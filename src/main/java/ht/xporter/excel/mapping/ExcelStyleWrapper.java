package ht.xporter.excel.mapping;

import ht.xporter.excel.style.CellType;

public class ExcelStyleWrapper {
	public enum BorderFill { ALL, NONE };

	private String column;
	private String pattern;
	private BorderFill border;
	private CellType cellType;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public BorderFill getBorder() {
		return border;
	}

	public void setBorder(BorderFill border) {
		this.border = border;
	}

	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}

	public static class Builder {
		private ExcelStyleWrapper wrapper;

		public Builder() {
			wrapper = new ExcelStyleWrapper();
		}

		public Builder column(String column) {
			wrapper.setColumn(column);
			return this;
		}

		public Builder pattern(String pattern) {
			wrapper.setPattern(pattern);
			return this;
		}

		public Builder border(BorderFill border) {
			wrapper.setBorder(border);
			return this;
		}

		public Builder cellType(CellType cellType) {
			wrapper.setCellType(cellType);
			return this;
		}

		public ExcelStyleWrapper build() {
			return wrapper;
		}
	}
}
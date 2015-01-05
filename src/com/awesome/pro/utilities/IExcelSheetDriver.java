package com.awesome.pro.utilities;

import java.util.List;

/**
 * Interface for performing read/write operations on
 * Excel workbooks.
 * @author sankeerth.reddy
 */
public interface IExcelSheetDriver {
	
	/**
	 * Sets the sheet reference to the specified name.
	 * @param sheetName Name of the sheet.
	 */
	public void setSheet(String sheetName);
	
	/**
	 * @param row Row index.
	 * @param column column index.
	 * @return Value of the cell contained at the specified indices.
	 */
	String getStringCell(int row, int column);
	
	/**
	 * @param row Row index.
	 * @param column column index.
	 * @return Value of the cell contained at the specified indices.
	 */
	int getIntegerCell(int row, int column);

	void setCell(int row, int column, String cellValue);

	void setAllCells(String[][] inputArray);

	/**
	 * @param row Row index.
	 * @return Sequential list of row entries.
	 */
	List<String> getStringRow(int row);

	/**
	 * @param row Row index.
	 * @return Sequential list of row entries.
	 */
	List<Integer> getIntegerRow(int row);

}

package com.awesome.pro.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Wrapper on Apache excel reader library.
 * @author sankeerth.reddy
 */
public class ExcelSheetUitility implements IExcelSheetDriver {
	
	/**
	 * Instance of default logger.
	 */
	private Logger LOGGER = Logger.getLogger(ExcelSheetUitility.class);
	
	/**
	 * Reference to the actively selected work sheet.
	 */
	private XSSFSheet sheet;

	/**
	 * Reference to entire work book.
	 */
	private XSSFWorkbook workbook;

	/**
	 * Name of the excel workbook to read.
	 */
	private String fileName;

	public ExcelSheetUitility(String file) {
		this.fileName = file;
		try {
			InputStream ExcelFileToRead = new FileInputStream(file);
			this.workbook = new XSSFWorkbook(ExcelFileToRead);
			ExcelFileToRead.close();
		} catch (IOException e) {
			LOGGER.error("Error in reading file: " + file, e);
		}
	}
	
	@Override
	public void setSheet(String sheetName) {
		this.sheet = this.workbook.getSheet(sheetName);
		try {
			this.sheet.getRow(0);
		} catch (NullPointerException e) {
			LOGGER.error("ERROR: Specified sheet not found: " + sheetName, e);
		}
	}

	@Override
	public List<String> getStringRow(int row) {
		List<String> rowList = new ArrayList<String>();
		if(this.sheet == null)
			throw new Error("Sheet is not set");
		if(row > sheet.getPhysicalNumberOfRows()){
			LOGGER.warn("Row out of Index");
			return null;
		}
		XSSFRow newRow = sheet.getRow(row);
		for(int i = 0; i <= newRow.getLastCellNum(); i++){
			try {
				rowList.add(newRow.getCell(i).getStringCellValue());
			} catch (NullPointerException e) {
				rowList.add(null);
			}
		}
		return rowList;
	}
	
	@Override
	public List<Integer> getIntegerRow(int row) {
		List<Integer> rowList = new ArrayList<>();
		if(this.sheet == null)
			throw new Error("Sheet is not set");
		if(row > sheet.getPhysicalNumberOfRows()){
			LOGGER.warn("Row out of Index");
			return null;
		}
		XSSFRow newRow = sheet.getRow(row);
		for(int i = 0; i <= newRow.getLastCellNum(); i++){
			try {
				Double temp = newRow.getCell(i).getNumericCellValue();
				rowList.add(temp.intValue());
			} catch (NullPointerException e) {
				rowList.add(-1);
			}
		}
		return rowList;
	}

	@Override
	public String getStringCell(int row, int column) {
		if(this.sheet == null)
			throw new Error("Sheet is not set");
		if(row > sheet.getLastRowNum()){
			LOGGER.warn("Row out of Index");
			return null;
		}
		XSSFRow newRow = sheet.getRow(row);
		if (newRow == null)
			return null;
		if(column >= newRow.getLastCellNum()) {
			LOGGER.warn("Columns out of Index");
			return null;
		}
		try{
			String cellValue = newRow.getCell(column).getStringCellValue();
			return cellValue;
		}
		catch(NullPointerException e) {
			LOGGER.error("Cell value is not set for " + "row: " + row +" column " + column);
			return null;
		}
	}
	
	@Override
	public int getIntegerCell(int row, int column) {
		if(this.sheet == null)
			throw new Error("Sheet is not set");
		if(row > sheet.getLastRowNum()){
			LOGGER.warn("Row out of Index");
			return -1;
		}
		XSSFRow newRow = sheet.getRow(row);
		if(column >= newRow.getLastCellNum()){
			LOGGER.warn("Columns out of Index");
			return -1;
		}
		try{
			Double cellValue = newRow.getCell(column).getNumericCellValue();
			return cellValue.intValue();
		}
		catch(NullPointerException e) {
			LOGGER.error("Cell value is not set for " + "row: " + row +" column " + column);
			return -1;
		}
	}

	@Override
	public void setCell(int row, int column, String cellValue){
		if(this.sheet == null)
			throw new Error("Sheet is not set");
		if(row > sheet.getLastRowNum()){
			LOGGER.warn("Row out of Index and creating a row now");
			sheet.createRow(row);
		}
		XSSFRow newRow = sheet.getRow(row);

		try {
			newRow.getCell(column).setCellValue(cellValue);
		} catch (NullPointerException e) {
			LOGGER.warn("Cell is not created, Creating now");
			newRow.createCell(column);
			newRow.getCell(column).setCellValue(cellValue);
		}

		try {
			FileOutputStream fileOut = new FileOutputStream(this.fileName);
			this.workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (IOException e) {
			LOGGER.error("Unable to write to file: " + this.fileName, e);
		}
	}

	@Override
	public void setAllCells(String[][] inputArray){
		System.out.println(inputArray.length);
		throw new Error("Not Implemented");
	}

}

package com.flairstech.framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author SarahSaloum
 *
 */
public class TestDataActions {
    private String xlsx_name;
    private XSSFWorkbook wb;
    private Sheet sheet;
    private Row row;
    private Cell cell;

    public TestDataActions(final String xlsx_name) {
	this.xlsx_name = xlsx_name;
    }

    public String ReadCellData(String RowName) {
	wb = null;
	int rowNo;
	int columnNo;
	String defaultColumnName = "Data1";
	try {
	    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/TestData/" + xlsx_name);

	    wb = new XSSFWorkbook(fis);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	Sheet sheet = wb.getSheetAt(0);
	rowNo = getRowNoByRowName("Sheet1", RowName);
	columnNo = getColumnNoByColumnName("Sheet1", defaultColumnName);

	row = sheet.getRow(rowNo);
	cell = row.getCell(columnNo);

	return cell.getStringCellValue();
    }

    private int getRowNoByRowName(String sheetName, String rowName) {

	sheet = wb.getSheet(sheetName);
	int rowNo = 0;

	for (int i = 0; i <= sheet.getLastRowNum(); i++) {
	    row = sheet.getRow(i);

	    if (row != null && row.getCell(0).getStringCellValue().equals(rowName)) {
		rowNo = i;
		break;
	    }
	}

	return rowNo;
    }

    private int getColumnNoByColumnName(String sheetName, String columnName) {
	int columnNo = 0;

	if (!columnName.equals("")) {
	    row = sheet.getRow(0);
	    for (int i = 0; i < row.getLastCellNum(); i++) {

		if (row.getCell(i).getStringCellValue().equals(columnName)) {
		    columnNo = i;
		    break;
		}
	    }

	}
	return columnNo;
    }

}
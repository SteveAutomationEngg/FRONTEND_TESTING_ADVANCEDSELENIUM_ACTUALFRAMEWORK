package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetName, int rowNum , int celNum) throws Throwable {
		FileInputStream fis = new FileInputStream("./testdata/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		wb.close();
		
		return data;	
	}
	
	
	public int getRowcount(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("./testdata/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		
		return rowCount;
	}
	
	
	public void setDataIntoExcel(String sheetName, int RowNum, int CelNum,  String data) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./testdata/testscriptsdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Cell cel= wb.getSheet(sheetName).getRow(RowNum).createCell(CelNum);
		cel.setCellType(CellType.STRING);
		cel.setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream("./testdata/testscriptdata.xlsx");
		wb.write(fos);
		wb.close();
	}
	

	
}

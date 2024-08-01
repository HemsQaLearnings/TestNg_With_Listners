package com.Generic_Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

	public FileInputStream fi;
	public FileOutputStream fo;
	public Workbook workbook;
	public Sheet sheet;
	public Row row;
	public Cell cell;
	public CellStyle style;   	

	public int getRowCount(String sheetName) throws IOException 
	{
		fi=new FileInputStream(IframeConstant.ExcelFilePath);
		workbook=WorkbookFactory.create(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;		
	}
	

	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(IframeConstant.ExcelFilePath);
		workbook=WorkbookFactory.create(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(IframeConstant.ExcelFilePath);
		workbook=WorkbookFactory.create(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try{
		data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	public HashMap<String,String> hashMapData(String sheetName, int rowNo, int cellNo) throws IOException
	{
		FileInputStream fis= new FileInputStream(IframeConstant.ExcelFilePath);
		 Workbook wb = WorkbookFactory.create(fis);
		 Sheet sh = wb.getSheet(sheetName);
		 int lastrow=sh.getLastRowNum();
		 
		 HashMap<String, String> map = new HashMap<String,String>();
		 for(int i=0; i<=lastrow; i++)
		 {
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			 String value=sh.getRow(rowNo).getCell(cellNo+1).toString();
			map.put(key, value);
		 }
		 return map;
	}
	public void writeDataIntoExcel(String sheetName,String data) throws IOException
	{
		FileInputStream fis= new FileInputStream(IframeConstant.ExcelFilePath);
		 Workbook wb = WorkbookFactory.create(fis);
		 Sheet sh = wb.getSheet(sheetName);
		 sh.createRow(0).createCell(0).setCellValue(data);
		 
		 FileOutputStream fout=new FileOutputStream(IframeConstant.ExcelFilePath);
		 wb.write(fout);
		 wb.close();
		 
	}
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(IframeConstant.ExcelFilePath);
		if(!xlfile.exists())    // If file not exists then create new file
		{
		workbook=new XSSFWorkbook();
		fo=new FileOutputStream(IframeConstant.ExcelFilePath);
		workbook.write(fo);
		}
				
		fi=new FileInputStream(IframeConstant.ExcelFilePath);
		workbook=new XSSFWorkbook(fi);
			
		if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
			workbook.createSheet(sheetName);
		
		sheet=workbook.getSheet(sheetName);
					
		if(sheet.getRow(rownum)==null)   // If row not exists then create new Row
				sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(IframeConstant.ExcelFilePath);
		workbook.write(fo);		
		workbook.close();
		fi.close();
		fo.close();
	}
	
	
	public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(IframeConstant.ExcelFilePath);
		workbook=WorkbookFactory.create(fi);
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
				
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	
	public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(IframeConstant.ExcelFilePath);
		workbook=WorkbookFactory.create(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		
		cell.setCellStyle(style);		
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
}
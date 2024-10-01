package com.nucleus.qa.testcases;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nucleus.qa.base.TestBase;

public class datadriven extends TestBase {
	
	
	public ArrayList<String> getData(String testcasename, String path) throws IOException {
		ArrayList<String> a = new ArrayList<String>();
		
		File src=new File(path);
		System.out.println(path);
		FileInputStream fis = new FileInputStream(src);
		 HSSFWorkbook workbook = new HSSFWorkbook(fis);   
        // HSSFSheet sheet = workbook.getSheetAt(0); 
         
		int sheets = workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("Result"))
			{
			HSSFSheet sheet=workbook.getSheetAt(i);
			Iterator<Row> rows =sheet.iterator();  //sheet is collection of rows
			HSSFRow firstrow=(HSSFRow) rows.next();
			Iterator<Cell> ce=firstrow.cellIterator(); //row is collection cells
			//System.out.print(ce.toString() + "\t");
			int k=0;
			int coloumn=0;
			while(ce.hasNext()) {
				HSSFCell value=(HSSFCell) ce.next();
				//if(value.getRow(value).getStringCellValue()
				if(value.getStringCellValue().equalsIgnoreCase("ApplicationName")) {
					coloumn=k;
					System.out.println(value.getStringCellValue());
					System.out.println(""+coloumn);
				}
				k++;
			}
			while(rows.hasNext()) {
				Row r =rows.next();
				if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcasename)) {
					
					Iterator<Cell> cv=r.cellIterator();
					while(cv.hasNext()) {
						Cell c =cv.next();
					if(c.getCellType()==CellType.STRING) {
						a.add(c.getStringCellValue());
					}
					else {
						a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
					}
					}
					System.out.println(a);
				}
				
			}
		}
			}
		return a;

		
	}

	public static void main(String[] args) throws IOException, InvalidFormatException {
	
				// TODO Auto-generated method stub

	}
	

}

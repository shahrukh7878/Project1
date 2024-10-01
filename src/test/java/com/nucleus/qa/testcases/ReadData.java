package com.nucleus.qa.testcases;



	import org.apache.poi.hssf.usermodel.HSSFCell;
	import org.apache.poi.hssf.usermodel.HSSFRow;
	import org.apache.poi.hssf.usermodel.HSSFSheet;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;

	public class ReadData {
	    public static  void main(String args[]) throws IOException {
	        
	        //Create an object of File class to open xlsx file
	        File file =    new File("C:\\Users\\ShahrukhAata_l4\\Project\\Test\\TestData\\TestData.xls");
	        
	        //Create an object of FileInputStream class to read excel file
	        FileInputStream inputStream = new FileInputStream(file);
	        
	        //Creating workbook instance that refers to .xls file
	        HSSFWorkbook wb=new HSSFWorkbook(inputStream);
	        
	        //Creating a Sheet object using the sheet Name
	        HSSFSheet sheet=wb.getSheet("Result");
	        
	        //Create a row object to retrieve row at index 1
	        HSSFRow row2=sheet.getRow(1);
	        
	        //Create a cell object to retreive cell at index 5
	        HSSFCell cell=row2.getCell(1);
	        HSSFCell cell1=row2.getCell(2);
	        HSSFCell cell2=row2.getCell(3);
	        HSSFCell cell3=row2.getCell(4);
	        HSSFCell cell4=row2.getCell(5);
	        HSSFCell cell5=row2.getCell(6);
	        HSSFCell cell6=row2.getCell(7);
	        HSSFCell cell7=row2.getCell(8);
	        HSSFCell cell8=row2.getCell(9);
	        HSSFCell cell9=row2.getCell(10);
	        HSSFCell cell10=row2.getCell(11);
	        HSSFCell cell11=row2.getCell(12);
	        
	        
	        //Get the address in a variable
	        String address= cell.getStringCellValue();
	        
	        //Printing the address
	        System.out.println("Address is :"+ address);
	    }
	}

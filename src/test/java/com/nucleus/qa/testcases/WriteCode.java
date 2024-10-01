package com.nucleus.qa.testcases;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteCode {
	public static void Write(String NucleusWebsite,String InfinityFundingWebsite, String MypulseWebsite, String MyNucleusPortalBroker, String MyNucleusPortalAdmin, String InfinityPortalDirectRoleBroker, String InfinityPortalTeleRoleBroker, String InfinityPortalDirectRoleAdmin, String InfinityPortalTeleRoleAdmin, String Mycollection, String MyReportingPortal, String MyAdminPortal    ) throws IOException {
		
		
	
        
    	//Create an object of File class to open xlsx file
        File file =    new File("C:\\Users\\ShahrukhAata_l4\\Project\\Test\\TestData\\TestData.xls");
        
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(inputStream);
        
        //creating a Sheet object using the sheet Name
        HSSFSheet sheet=wb.getSheet("Result");
        
        //Create a row object to retrieve row at index 3
        HSSFRow row2=sheet.createRow(1);
        
        //create a cell object to enter value in it using cell Index
        row2.createCell(1).setCellValue(NucleusWebsite);
        row2.createCell(2).setCellValue(InfinityFundingWebsite);
        row2.createCell(3).setCellValue(MypulseWebsite);
        row2.createCell(4).setCellValue(MyNucleusPortalBroker);
        row2.createCell(5).setCellValue(MyNucleusPortalAdmin);
        row2.createCell(6).setCellValue(InfinityPortalDirectRoleBroker);
        row2.createCell(7).setCellValue(InfinityPortalTeleRoleBroker);
        row2.createCell(8).setCellValue(InfinityPortalDirectRoleAdmin);
        row2.createCell(9).setCellValue(InfinityPortalTeleRoleAdmin);
        row2.createCell(10).setCellValue(Mycollection);
        row2.createCell(11).setCellValue(MyReportingPortal);
        row2.createCell(12).setCellValue(MyAdminPortal);
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\ShahrukhAata_l4\\Project\\Test\\TestData\\TestData.xls");
        wb.write(outputStream);
        wb.close();

    }
}
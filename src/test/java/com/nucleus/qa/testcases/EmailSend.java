package com.nucleus.qa.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.nucleus.qa.base.TestBase;

	public class EmailSend extends TestBase
	{
		
		static datadriven d;
		public static Object sendEmailWithSqData(String messageToSend)
		{
			
			LocalDateTime localDate = LocalDateTime.now().minusDays(0);
			DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
			
			String host = "smtpout.secureserver.net";

			final String user = "integration@mypulse-sandbox.io";
			final String password = "Plom55AD!";

			String to = "shahrukh.aatar@mypulse.io";
			String to1 = "";
			String to2 = "";
			String cc = "";

			Properties props = new Properties();
			props.put("mail.smtp.host", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", host);
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(user, password);
				}
			});

			try
			{
		
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				//message.addRecipient(Message.RecipientType.TO,new
			   //InternetAddress(to1));
				// message.addRecipient(Message.RecipientType.TO,new
				// InternetAddress(to2));
				// message.addRecipient(Message.RecipientType.CC,new
				// InternetAddress(cc));
				message.setSubject("Core Systems Check : " + customFormat.format(localDate));
				//message.setContent(null);
				//message.setText("Hi Julius");
				//message.setText("Please find below the Core Systems Checks done");
				message.setContent(messageToSend, "text/html");
				// message.setText(messageToSend);
				//message.setDescription(htmlContent);

				Transport.send(message);
				System.out.println("message sent successfully...");
				
				

			}
			catch(MessagingException e)
			{
				System.out.println("Exception occured is :" + e);
			}
			return null;
		}
		
		
		public static void main(String[] args) throws InterruptedException, IOException
		{
			
			LocalDateTime localDate = LocalDateTime.now().minusDays(0);
			DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
			
			String Date = customFormat.format(localDate);
			
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			//System.out.println(date.format(formatter));
			String Date1=date.format(formatter);
			
			while(true)
				
			{
				Date sDate = new Date();
				int iHour = sDate.getHours();
				int iMinut = sDate.getMinutes();
				int iSeconds = sDate.getSeconds();
				if(iHour==20){
					if(iMinut==0)
						//if(iSeconds==30)
					{
						System.out.println("5555555555555555555");
						
			File file =    new File("C://Users//ShahrukhAata_l4//Project//Test//TestData//TestData.xls");
			FileInputStream inputStream = new FileInputStream(file);
			HSSFWorkbook wb=new HSSFWorkbook(inputStream);
			
			//HSSFSheet sheet=wb.getSheet("Result");
			HSSFSheet sheet1=wb.getSheetAt(0);
			HSSFRow row2=sheet1.getRow(1);
	        HSSFCell cell=row2.getCell(1); //NucleusWebsite
	        HSSFCell cell1=row2.getCell(2);//InfinityFundingWebsite
	        HSSFCell cell2=row2.getCell(3);//MypulseWebsite
	        HSSFCell cell3=row2.getCell(4);//MyNucleusPortalBroker
	        HSSFCell cell4=row2.getCell(5);//MyNucleusPortalAdmin
	        HSSFCell cell5=row2.getCell(6);//InfinityPortalDirectRoleBroker
	        HSSFCell cell6=row2.getCell(7);//InfinityPortalTeleRoleBroker
	        HSSFCell cell7=row2.getCell(8);//InfinityPortalDirectRoleAdmin
	        HSSFCell cell8=row2.getCell(9);//InfinityPortalTeleRoleAdmin
	        HSSFCell cell9=row2.getCell(10);//Mycollection
	        HSSFCell cell10=row2.getCell(11);//MyReportingPortal
	        HSSFCell cell11=row2.getCell(12);//MyAdminPortal
	        
	      
	      //Get the address in a variable
	        String NucleusWebsite= cell.getStringCellValue();
	        String InfinityFundingWebsite= cell1.getStringCellValue();
	        String MypulseWebsite= cell2.getStringCellValue();
	        String MyNucleusPortalBroker= cell3.getStringCellValue();
	        String MyNucleusPortalAdmin= cell4.getStringCellValue();
	        String InfinityPortalDirectRoleBroker= cell5.getStringCellValue();
	        String InfinityPortalTeleRoleBroker= cell6.getStringCellValue();
	        String InfinityPortalDirectRoleAdmin= cell7.getStringCellValue();
	        String InfinityPortalTeleRoleAdmin= cell8.getStringCellValue();
	        String Mycollection= cell9.getStringCellValue();
	        String MyReportingPortal= cell10.getStringCellValue();
	        String MyAdminPortal= cell11.getStringCellValue();
			
			
		
			
			
			StringBuilder htmlContent = new StringBuilder();
			htmlContent.append("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<style>\r\n"
					+ "table, th, td {\r\n"
					+ " border: 1px solid black;\r\n"
					+ "  border-collapse: collapse;\r\n"
					+ "}\r\n"
					+ "</style>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "<p>Hi Julius,</p>\r\n"
					+ "<p>Please find below the Core Systems Checks done for "+Date+".</p>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "<table style=\"width:100%\">\r\n"
					+ "<table style=\"text-align: center\"> \r\n"
					+ "\r\n"
					+ "  <tr>\r\n"
					+ "    <th style=\"width:5%\"  rowspan=\"2\">Date</th>\r\n"
					+ "    <th style=\"width:5%\" rowspan=\"2\">NCF Website</th>\r\n"
					+ "    <th style=\"width:5%\" rowspan=\"2\">Infinity Website</th>\r\n"
					+ "    <th style=\"width:5%\" rowspan=\"2\">Pulse Website</th>\r\n"
					+ "    <th style=\"width:5%\" colspan=\"2\">My Nucleus Portal</th>\r\n"
					+ "    <th style=\"width:5%\" colspan=\"4\">My Funding Portal</th>\r\n"
					+ "    <th style=\"width:5%\" rowspan=\"2\">MyFunding Portal - Reports</th>\r\n"
					+ "    <th style=\"width:5%\" rowspan=\"2\">MyAdmin Portal - Reports</th>\r\n"
					+ "    <th style=\"width:5%\" rowspan=\"2\">My collections Portal</th>\r\n"
					+ "\r\n"
					+ "  </tr>\r\n"
					+ "  <tr>\r\n"
					+ "    <th style=\"width:5%\">Broker</th>\r\n"
					+ "    <th style=\"width:5%\">Admin</th>\r\n"
					+ "    <th style=\"width:5%\">Direct Role-Broker</th>\r\n"
					+ "    <th style=\"width:5%\">Direct Role-Admin</th>\r\n"
					+ "    <th style=\"width:5%\">Tele Role-Broker</th>\r\n"
					+ "    <th style=\"width:5%\">Tele Role-Admin</th>\r\n"
					+ "    \r\n"
					+ "  </tr>\r\n"
					+ "  <tr>\r\n"
					+ "    <td>"+Date1+"</td>\r\n"
					+ "    <td>"+NucleusWebsite+"</td>\r\n"
					+ "    <td>"+InfinityFundingWebsite+"</td>\r\n"
					+ "    <td>"+MypulseWebsite+"</td>\r\n"
					+ "    <td>"+MyNucleusPortalBroker+"</td>\r\n"
					+ "    <td>"+MyNucleusPortalAdmin+"</td>\r\n"
					+ "    <td>"+InfinityPortalDirectRoleBroker+"</td>\r\n"
					+ "    <td>"+InfinityPortalTeleRoleBroker+"</td>\r\n"
					+ "    <td>"+InfinityPortalDirectRoleAdmin+"</td>\r\n"
					+ "    <td>"+InfinityPortalTeleRoleAdmin+"</td>\r\n"
					+ "    <td>"+Mycollection+"</td>\r\n"
					+ "    <td>"+MyReportingPortal+"</td>\r\n"
					+ "    <td>"+MyAdminPortal+"</td>\r\n"
					+ "  </tr>\r\n"
					+ "</table>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "\r\n"
					+ "");
	    
		
			
			
			
			String Content= htmlContent.toString();
		
			 
					
							sendEmailWithSqData(Content);
							
							Thread.sleep(60000);
						}		
					{
					}
					}
					System.out.println(iHour);
					Thread.sleep(15000);
					
				}	
		}

	}

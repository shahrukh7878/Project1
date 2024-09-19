package com.nucleus.qa.testcases;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.ant.taskdefs.SendEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.infinity.qa.pages.InfinityHomePage;
import com.infinity.qa.pages.InfinityLoginPage;
import com.infinity.qa.pages.InfinityNewProposalPage;
import com.nucleus.qa.base.EmailUtil;
import com.nucleus.qa.base.TestBase;
import com.nucleus.qa.pages.DirectorInformationPage;
import com.nucleus.qa.pages.DocumentsPage;
import com.nucleus.qa.pages.HomePage;
import com.nucleus.qa.pages.LoanInformationPage;
import com.nucleus.qa.pages.LoginPage;
import com.nucleus.qa.pages.NewProposalPage;
import com.nucleus.qa.pages.NucleusSaleforcePage;
import com.nucleus.qa.pages.OfficePage;
import com.nucleus.qa.pages.ShareholderDetailsPage;
import com.nucleus.qa.pages.myPulsePage;
import com.nucleus.qa.util.TestUtil;

public class CoreSystemTesting extends TestBase  {

	 XSSFWorkbook workbook;
	    Sheet sheet;
	    Cell cell;
	    public static ExtentTest test;
	    static ExtentTest WriteExtentReport;
	    public static boolean sStatus;
	    public static String sErrorLog;
	    public static ExtentReports extent;
	    static String Nucleus;
		ExtentTest test1,test2;		  
		LoginPage loginPage;
		HomePage  homePage;
		NewProposalPage newproposalpage;
		LoanInformationPage LoanInfo;
		DirectorInformationPage DirectorInformation;
		DocumentsPage Documents;
		NucleusSaleforcePage NucleusSaleforce;
		ShareholderDetailsPage ShareholderDetails;
		OfficePage Office;
		myPulsePage myPulse;
		TestUtil testutil;
		TestBase TestBaseMethod;
		String Datepath;
		String FilePath;
		InfinityLoginPage InfinityLogin;
		InfinityHomePage  InfinityHome;
		InfinityNewProposalPage InfinityNewProposal;	
		datadriven d;
		DataFormatter formatter=new DataFormatter();
		String sheetName = "Credentilas";
		ExtentTest logger;
		
		
		
		
		String path= "C:\\Users\\ShahrukhAatar\\Documents\\CoreSystemTestData.xls";
		
		@BeforeSuite
		public void start() {
			
			
			
			
			 
			
			String  path2 = System.getProperty("user.dir")+ "\\reports\\index.html";
		//	ExtentSparkReporter esp=new ExtentSparkReporter(System.getProperty("user.dir")+"/ExtentReport/ExtentReports_"+destDir+"/SwarupExtentReport.html");
			Calendar cal = Calendar.getInstance();
			File Dir = new File(path2);
			Dir.mkdir();
			int year = cal.get(Calendar.YEAR);
			Dir = new File(path2+"/"+year);
			Dir.mkdir();
			int month = cal.get(Calendar.MONTH);
			Dir = new File(path2+"/"+year+"/"+(month+1));
			Dir.mkdir();
			int day = cal.get(Calendar.DATE);
			Dir = new File(path2+"/"+year+"/"+(month+1)+"/"+day);
			Dir.mkdir();
			Dir = new File(path2+"/"+year+"/"+(month+1)+"/"+day);
			Dir.mkdir();
			Datepath= Dir.getAbsolutePath();
			
			
			
			 
			
		
			
			Date sDate = new Date();
			String sScreenshotFilename = sDate.getHours()+"_"+sDate.getMinutes()+"_"+sDate.getSeconds();
			FilePath = Datepath + "/" + sScreenshotFilename;
			// String path = System.getProperty("user.dir")+ "\\reports\\"+Datepath+"\\index.html";
			
			
	         // File DestFile=new File(FilePath);
			
			 // Files.copy(path.toPath(), DestFile.toPath());
	         ExtentSparkReporter reporter=new ExtentSparkReporter(FilePath);
	         reporter.config().setReportName("Web Automation Results");
			 reporter.config().setDocumentTitle("TestResult");
			 extent = new ExtentReports();
		     extent.attachReporter(reporter);
		     
		     
		}
		
		@BeforeMethod
		public void setUp() throws IOException {
			
			/*String sRequest = "https://myfunding.ncf-sandbox.com/deleteCompanyData/09448371";
			
			Response response = given()
				.contentType("application/json")
					.accept("application/json")
					//.header("Authorization","a45e7250f2b06ad85f35bb4c24292f12e009088d57efa05aa9a036faeab71ea4")
					//.header("Authorization",sTokenkey)
					//.body(userDetails)
					.when()
					.get(sRequest)
					.then()
					//.statusCode(200)
					//.contentType("application/json")
					.extract()
					.response();

			int iResponseCode = response.getStatusCode();
			System.out.println(response.getStatusCode());
			System.out.println("-------------"+response.asString());
			System.out.println("-------------"+response.getBody().asString());
			//int iResponseCode = response.getStatusCode();
			System.out.println("-------------"+iResponseCode);
			System.out.println("-------------"+response.getStatusLine());
			System.out.println("-------------"+response.getHeader("content-type"));
			System.out.println("-------------"+response.getTime()+" Seconds");*/
			
			
			initialization();
		    loginPage = new LoginPage();
			testutil = new TestUtil();
			homePage = new HomePage();
			Office= new OfficePage();
			newproposalpage =new NewProposalPage();
			LoanInfo=new LoanInformationPage();
			DirectorInformation =new DirectorInformationPage();
			Documents = new DocumentsPage();
			myPulse = new myPulsePage();
			TestBaseMethod =new TestBase();
			NucleusSaleforce= new NucleusSaleforcePage();
			InfinityLogin=new InfinityLoginPage();
			InfinityHome = new InfinityHomePage();
			InfinityNewProposal=new InfinityNewProposalPage();
			 
			 d = new datadriven();	
			 
		}

		
		/*@Test
		public void SaleForce() throws Exception {
			
			//System.out.println(name+user+id);
			// Framework.test=Framework.extent.createTest("Test case ID:"+"<br>"+"Test case Description:Application landing page"+"<br>"+"Expected Result:Application launch successfully"+"</br>");
			//Framework.WriteExtentReport=Framework.test.createNode("Navigate to Application landing page <br>");
			 test1 = extent.createTest("Test1", "Saleforce ");
			 test1.log(Status.INFO, "Starting test case");
			//ExtentTest test=extent.createTest("TestCase1");
			ArrayList data=d.getData("Test");
			String url = (String) data.get(1);
			driver.get(url);
		   String Username = (String) data.get(2);
		   String Password = (String) data.get(3);
		   String FirstName =(String) data.get(4);
		   String LastName = (String) data.get(5);
		   String CompanyName=(String)data.get(6);
		 
		   System.out.println(Username);
		   System.out.println(Password);
		   System.out.println(FirstName);
		   System.out.println(LastName);
		   System.out.println(CompanyName);
		 
		   try {
			NucleusSaleforce.EnterUsername(Username);
			NucleusSaleforce.EnterPassword(Password);
			NucleusSaleforce.ClickOnLogin();
			
			//test1.log(Status.PASS, "Chrome browser has opened",MediaEntityBuilder.createScreenCaptureFromPath(capture(driver)).build());
			Sleep(5000);
			NucleusSaleforce.ClickOnProfile();
			NucleusSaleforce.SwitchToClassic();
			NucleusSaleforce.ClickOnLeadtab();
			NucleusSaleforce.ClickOnNewbutton();
			NucleusSaleforce.SelectOnLeadRecordType();
			NucleusSaleforce.ClickOnContinue();
			NucleusSaleforce.EnterFirstName(FirstName);
			NucleusSaleforce.EnterLastName(LastName);
			NucleusSaleforce.SelectLeadSource();
			NucleusSaleforce.SelectLeadSourceInformation();
			NucleusSaleforce.EnterCompanyName(CompanyName);
			NucleusSaleforce.ClickOnSavebutton();
			//test.fail("do not match");
			
		/*	sStatus=true;
	    	
			}
			catch(Exception e) {
				sStatus=false;
				
				sErrorLog = e.toString();
			}
			
		   
		   if(sStatus==true)
			{
				Framework.CreateResults(true, "Step Pass");
			}
			else
			{
				Framework.CreateResults(false, sErrorLog);
			}
			//driver.get("https://myfunding.ncf-sandbox.com/");
		   }
		catch(Exception e) {
			//File DestFile=new File(sScreenshotFilePath);
			String Error = e.toString();
			test1.fail("Error Message" +Error );
			Screenshot();
			//String screenshotPath = TestBase.Screenshot();
	        test1.fail("Test Case failed check screenshot below"+test1.addScreenCaptureFromPath(sScreenshotFilePath));
		}
		}*/
		
		
		@Test(enabled=false)
		public void TestCase1() throws Exception  {
			 test1 = extent.createTest("Test Case 1", "Nucleus Website");

			try{
				
	        driver.get("https://nucleuscommercialfinance.com/");
	        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Find out more')]"))).isDisplayed();
			driver.findElement(By.xpath("//a[contains(text(),'Find out more')]")).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Types of funding')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'About us')]"))).isDisplayed();
			
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Apply for a Nucleus Loan page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
			driver.findElement(By.xpath("//a[@title='Loans']")).click();
	        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Apply Now')])[2]"))).isDisplayed();
	        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Find out more')]"))).isDisplayed();
	        Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Nucleus Home Page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			driver.findElement(By.xpath("(//a[contains(text(),'Apply Now')])[2]")).click();
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Apply for a Nucleus Loan ')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Business Loans')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Types of funding')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'About us')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Resources')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Apply Now')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Nucleus']"))).isDisplayed();
			

			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Apply for a Nucleus Loan page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			Sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(0,600)");
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Next')])[1]"))).isDisplayed();
			WebElement element = driver.findElement(By.xpath("(//button[contains(text(),'Next')])[1]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			
			Sleep(2000);
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Email must be 5 characters or more')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Phone Number must be 11 characters or more')]"))).isDisplayed();
			
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Personal Information Page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			Sleep(4000);
			((JavascriptExecutor)driver).executeScript("scroll(0,1200)");
			Sleep(4000);
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Contact us')]"))).isDisplayed();
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Contact Us Page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			WebElement element1 = driver.findElement(By.xpath("//a[contains(text(),'Contact us')]"));
			JavascriptExecutor executor1 = (JavascriptExecutor)driver;
			executor1.executeScript("arguments[0].click();", element1);
		
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Submit']"))).isDisplayed();
			WebElement element2 = driver.findElement(By.xpath("//input[@value='Submit']"));
			JavascriptExecutor executor2 = (JavascriptExecutor)driver;
			executor2.executeScript("arguments[0].click();", element2);
			
			System.out.println("1111111111111111111111111111");
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Please fill out this field.')])[1]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Please fill out this field.')])[2]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Please fill out this field.')])[3]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Please fill out this field.')])[4]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Phone Number must be 11 characters or more')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Email must be 5 characters or more')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'One or more fields have an error. Please check and try again.')]"))).isDisplayed();
			
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Contact Us1 Page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			Nucleus = "Working";
			
			} catch(Exception e)  
	        {  
				Nucleus = "Not Working";
	            System.out.println(e); 
	            Screenshot();
				WriteExtentReport =test1.createNode("Navigate to Failed Page");
				WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				
	        }
		}
		
		
		
		
		@Test (enabled=false)
		public void TestCase2() throws Exception {
			 test1 = extent.createTest("Test Case2", "Infinity-Funding Website");
		
			/*ArrayList data=d.getData("TestCase2",path);
			String url = (String) data.get(2);
			
			String Username= (String) data.get(3);
			String Password= (String) data.get(4);
			String CompanyName=(String) data.get(5);
			String Email = (String) data.get(6);
			String PhoneNumber = (String) data.get(7);
			String BirthDay = (String) data.get(8);
			String PostCode = (String) data.get(9);
			String HouseNumber = (String) data.get(10);
			String HouseName = (String) data.get(11);
			String City = (String) data.get(12);
			String Street = (String) data.get(13);
			String Country = (String) data.get(14);
			String Fund = (String) data.get(15);
			String Months = (String) data.get(16);
			String Percent = (String) data.get(17);
			System.out.println(Percent);
			String BirthDay1= (String) data.get(18);
			String Email1 = (String) data.get(19);
			String MobileNumber = (String) data.get(20);
			String PhoneNO = (String) data.get(21);
			String url1 = (String) data.get(22);
			String Bank = (String) data.get(23);
			String BankType = (String) data.get(24);
			String Email2 = (String) data.get(25);
			String MobileNumber1 = (String) data.get(26);
			String Email3 = (String) data.get(27);
			String MobileNumber2 = (String) data.get(28);
			String Name = (String) data.get(29);
			System.out.println("11111111111111111 Before 555555555555555555555555");*/
			
			try {
			driver.get("https://infinity-funding.co.uk/");
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='logo'])[1]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Home')])[1]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Products')])[1]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'FAQ')])[1]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Apply')])[1]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Apply Now')]"))).isDisplayed();
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Infinity Funding Page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
			driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Product')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'GET IN TOUCH')]"))).isDisplayed();
			
			
			driver.findElement(By.xpath("(//a[contains(text(),'Home')])[1]")).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Apply Now')]"))).isDisplayed();
		
			driver.findElement(By.xpath("//a[contains(text(),'Apply Now')]")).click();
			System.out.println("4444444444444444444444444444444444444");
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//form[@id='ApplyForm']//child::div)[7]//child::button"))).isDisplayed();
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Apply Now Page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			WebElement element1 = driver.findElement(By.xpath("(//form[@id='ApplyForm']//child::div)[7]//child::button"));
			JavascriptExecutor executor1 = (JavascriptExecutor)driver;
			executor1.executeScript("arguments[0].click();", element1);

			((JavascriptExecutor)driver).executeScript("scroll(0,800)");
			System.out.println("4444444444444444444444444444444444444");
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your First Name.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your Last Name.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your correct Email.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your Company Name.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your correct Contact Number.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please accept our Privacy Policy to proceed.')]"))).isDisplayed();
			
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Apply Now1 Page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			((JavascriptExecutor)driver).executeScript("scroll(0,800)");
		
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Contact us')]"))).isDisplayed();
			
			WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Contact us')]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Apply Now2 Page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");

			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//form[@id='ApplyForm']//child::div)[7]//child::button"))).isDisplayed();
			WebElement element2 = driver.findElement(By.xpath("(//form[@id='ApplyForm']//child::div)[7]//child::button"));
			JavascriptExecutor executor2 = (JavascriptExecutor)driver;
			executor2.executeScript("arguments[0].click();", element2);
			
			((JavascriptExecutor)driver).executeScript("scroll(0,800)");
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your First Name.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your Last Name.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your correct Email.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your Company Name.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please fill in your correct Contact Number.')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'*Please accept our Privacy Policy to proceed.')]"))).isDisplayed();
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Apply Now3 Page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			}
			catch(Exception e) {
				System.out.println(e);	
			}
			
		}		
			@Test(enabled=false)
		public void TestCase3() throws Exception {
			 test1 = extent.createTest("Test Case 3", "mypulse.io Website");
			
				
			 try {
			
			          driver.get("https://mypulse.io/");
			          new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Login ')]"))).isDisplayed();
				      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login?register=1']"))).isDisplayed();
				      
				      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Sign up')])[1]"))).isDisplayed();
				      
				      Screenshot();
				      WriteExtentReport =test1.createNode("Navigate to Application landing page");
				      WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				      
				      
				      
				      driver.findElement(By.xpath("(//a[contains(text(),'Sign up')])[1]")).click();
				      
				      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Who am I?')]"))).isDisplayed();
				      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Register')]"))).isDisplayed();
				      Screenshot();
				      WriteExtentReport =test1.createNode("Navigate to Sign up page");
				      WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				      
				      driver.navigate().back();
				      
                     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Sign up')])[1]"))).isDisplayed();
				      
				      
				      driver.findElement(By.xpath("(//div[@class='navver'])[1]")).click();
				      
				      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Who Am I?')]"))).isDisplayed();
				      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Contact us')])[1]"))).isDisplayed();
				     
				      Screenshot();
				      WriteExtentReport =test1.createNode("Navigate to Who Am I page");
				      WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				      
				      driver.navigate().back();
				      
                      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Sign up')])[1]"))).isDisplayed();
				      
                      driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
                      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Contact Us')]"))).isDisplayed();
                    
                      driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
                      
                      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='navver'])[2]"))).isDisplayed();
				     
				     driver.findElement(By.xpath("(//div[@class='navver'])[2]")).click();
				    
				      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'GET IN TOUCH')]"))).isDisplayed();
				      
				      driver.findElement(By.xpath("//span[contains(text(),'GET IN TOUCH')]")).click();
				      
				        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'The field is required.')])[1]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'The field is required.')])[2]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'The field is required.')])[3]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'The field is required.')])[4]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'The field is required.')])[5]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'The field is required.')])[6]"))).isDisplayed();
					
						Screenshot();
					    WriteExtentReport =test1.createNode("Navigate to Contact Us page");
					    WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					      
						
						driver.navigate().back();
						
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Sign up')])[1]"))).isDisplayed();
					   
				      Sleep(5000);
				     JavascriptExecutor js = (JavascriptExecutor) driver;
				      js.executeScript("window.scrollBy(0,600)");
				      System.out.println("11111111111111111111111111");
				
				       Sleep(5000);
				       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'What do you get with Pulse?')]"))).isDisplayed();
					   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Learn more')])[2]"))).isDisplayed();
					   System.out.println("11111111111111111111111111");
					   Screenshot();
					   WriteExtentReport =test1.createNode("Navigate to What Do You Get With Pulse Page");
					   WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					   JavascriptExecutor js1 = (JavascriptExecutor) driver;
					   js1.executeScript("window.scrollBy(0,1400)");
					   Sleep(5000);
					   System.out.println("22222222222222222222222222");
					   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//h2[contains(text(),'Spot opportunities and issues in seconds')])"))).isDisplayed();
					   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Learn more')])[2]"))).isDisplayed();
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to Spot Opportunities And Issues In Seconds Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						System.out.println("passsssssssssssss");
						Sleep(5000);
						JavascriptExecutor js2 = (JavascriptExecutor) driver;
						js2.executeScript("window.scrollBy(0,800)");
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Secure, seriously simple')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Understand Open Banking')]"))).isDisplayed();
						System.out.println("pass444444444444444444444444");
						Sleep(6000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to Secure, Seriously Simple Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						JavascriptExecutor js3 = (JavascriptExecutor) driver;
						js3.executeScript("window.scrollBy(0,1300)");
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Like X-ray vision for your business')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Take your firm further')]"))).isDisplayed();
						System.out.println("pass444444444444444444444444");
						Sleep(6000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to Like X-ray Vision For Your Business Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
						
						JavascriptExecutor js4 = (JavascriptExecutor) driver;
						js4.executeScript("window.scrollBy(0,800)");
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Some of our ')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//em[contains(text(),'accounting')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'SOME OF OUR INTEGRATIONS')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Understand Open Accounting')]"))).isDisplayed();
						System.out.println("pass444444444444444444444444");
						Sleep(6000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to Some Of Our Accounting Integrations Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			               
						
						JavascriptExecutor js5 = (JavascriptExecutor) driver;
						js5.executeScript("window.scrollBy(0,1400)");
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Bring your clients up to speed')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Discover Pulse for Accountants')]"))).isDisplayed();
						System.out.println("pass444444444444444444444444");
						Sleep(6000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to Bring Your Clients Up To Speed Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			     
						
						JavascriptExecutor js6 = (JavascriptExecutor) driver;
						js6.executeScript("window.scrollBy(0,1400)");
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Get started in seconds')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Discover Pulse for Businesses')]"))).isDisplayed();
						System.out.println("pass444444444444444444444444");
						Sleep(6000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to Get Started In Seconds Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			     
						
						JavascriptExecutor js7 = (JavascriptExecutor) driver;
						js7.executeScript("window.scrollBy(0,1800)");
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Fast-track funding decisions')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Discover Pulse for Brokers')]"))).isDisplayed();
						System.out.println("pass444444444444444444444444");
						Sleep(6000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to Fast-Track Funding Decisions Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			     
						
						JavascriptExecutor js8 = (JavascriptExecutor) driver;
						js8.executeScript("window.scrollBy(0,1900)");
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Keep your finger on the ')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//em[contains(text(),'pulse')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Join the revolution')])[1]"))).isDisplayed();
						System.out.println("pass444444444444444444444444");
						Sleep(6000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to Keep Your Finger On The Pulse Of Your Business Today Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			     
						 
						
						 
						
			 
			 }
			 
			catch(Exception e) {
				String Error = e.toString();
				    Screenshot();
					WriteExtentReport =test1.createNode("Navigate to Failed page");
					WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"Failed"+Error);
			}
			}
		
		@Test(enabled=false)
		public void TestCase4() throws Exception  {
			
			
			 test1 = extent.createTest("Test Case 4", "MyNucleus Broker Portal-Individual");
			
			try {
			    driver.get("https://www.myfundingportal.co.uk/mynucleus");
			
			    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign In')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign Up')]"))).isDisplayed();
				
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("shahrukh.aatar@mypulse.io");
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys("brokerportal");
				driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
				Sleep(2000);
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'OK')])[4]"))).isDisplayed();
				driver.findElement(By.xpath("(//a[contains(text(),'OK')])[4]")).click();
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Welcome to')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'DASHBOARD')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' NEW PROPOSAL')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' PRODUCT SUPPORT')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' QUERIES')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' LOGOUT')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Log a Query')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
					
					
					Sleep(2000);
					 Screenshot();
					 WriteExtentReport =test1.createNode("Navigate to Application landing page");
					 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					 
					
					JavascriptExecutor js = (JavascriptExecutor) driver;
				      js.executeScript("window.scrollBy(0,600)");
				      
				      
				      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Quarter')]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Product')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Paid Out Deals By Broker')]"))).isDisplayed();
						
						Sleep(2000);
						 Screenshot();
						 WriteExtentReport =test1.createNode("Navigate to Application landing page1");
						 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
						 driver.findElement(By.xpath("//a[contains(text(),' NEW PROPOSAL')]")).click();
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'New Proposal ')]"))).isDisplayed();
						  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Business Information')]"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Loan Information')]"))).isDisplayed();
							
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Director Information')]"))).isDisplayed();
							  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Documents')]"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Limited Company')]"))).isDisplayed();
								
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Limited liability partnership')]"))).isDisplayed();
								
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("clearbtn"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("steponebutton"))).isDisplayed();
								
								Sleep(2000);
								 Screenshot();
								 WriteExtentReport =test1.createNode("Navigate to NEW PROPOSAL Page");
								 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
							 
								 
								 driver.findElement(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]")).click();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Banking Status')]"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Allied Irish(NI)')]"))).isDisplayed();
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Bank of Scotland Commercial')]"))).isDisplayed();
									
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Barclays Corporate')]"))).isDisplayed();
									
									Sleep(2000);
									 Screenshot();
									 WriteExtentReport =test1.createNode("Navigate to OPEN BANKING STATUS Page");
									 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
								 
								
									 driver.findElement(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]")).click();
									 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Accounting Status')]"))).isDisplayed();
									  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Dynamics 365 Business Central')]"))).isDisplayed();
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'QuickBooks Desktop')]"))).isDisplayed();
										
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Wave')]"))).isDisplayed();
										
										Sleep(2000);
										 Screenshot();
										 WriteExtentReport =test1.createNode("Navigate to OPEN ACCOUNTING STATUS Page");
										 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
									 
									 
					  driver.findElement(By.xpath("//a[contains(text(),' PRODUCT SUPPORT')]")).click();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Nucleus Product Support Document')] "))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Nucleus Introducer Brochure')]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[1]"))).isDisplayed();
											
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Product Factsheets')] "))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Nucleus Business Loans'])[1]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[2]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Revenue Based Loans'])[1]	"))).isDisplayed();
												
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[3]"))).isDisplayed();
												
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Nucleus Business Loans'])[2]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[4]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Revenue Based Loans'])[2]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[5]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Product checklist')]"))).isDisplayed();
												
											
						Sleep(2000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to PRODUCT SUPPORT Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										 
												 
					    driver.findElement(By.xpath("//a[contains(text(),' QUERIES')]")).click();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Open Queries')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Unresolved')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Resolved')]"))).isDisplayed();						
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='search'])[1]"))).isDisplayed();
						Sleep(2000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to QUERIES Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					    driver.findElement(By.xpath("//a[contains(text(),' SIC Codes')]")).click();
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'SIC Codes')]"))).isDisplayed();
												  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='Sic_Segment_ID']"))).isDisplayed();
													new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
														
													Sleep(2000);
													 Screenshot();
													 WriteExtentReport =test1.createNode("Navigate to SIC Codes Page");
													 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
												  
													 driver.findElement(By.xpath("//a[contains(text(),' LOGOUT')]")).click();
													 
													 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
													 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
													 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign In')]"))).isDisplayed();
														
													 
													 
													 Sleep(2000);
													 Screenshot();
													 WriteExtentReport =test1.createNode("Navigate to LOGOUT Page");
													 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
												  	
													 
													 
												 
								
								 
			}
		
		catch(Exception e) {
		
			String Error = e.toString();
			System.out.println(Error);
			Screenshot();
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		}
		}
		
		@Test(enabled=false)
		public void TestCase5() throws Exception  {
			
			 test1 = extent.createTest("Test Case 5", "MyNucleus Admin Portal");
			
			try {
				System.out.println("admin login");
			driver.get("https://www.myfundingportal.co.uk/mynucleus");
			
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign In')]"))).isDisplayed();
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign Up')]"))).isDisplayed();
				
			 driver.findElement(By.xpath("//input[@id='email']")).sendKeys("shahrukh.aatar@mypulse.io");
			 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("brokerportal1");
			 driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
				Sleep(2000);
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'OK')])[5]"))).isDisplayed();
				driver.findElement(By.xpath("(//a[contains(text(),'OK')])[5]")).click();
				
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Welcome to')]"))).isDisplayed();
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'DASHBOARD')]"))).isDisplayed();
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' NEW PROPOSAL')]"))).isDisplayed();
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
			    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' PRODUCT SUPPORT')]"))).isDisplayed();
			    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' PRODUCT SUPPORT')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' QUERIES')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' LOGOUT')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Log a Query')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
					
				Sleep(2000);
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate to Application landing page");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,600)");
				      
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Quarter')]"))).isDisplayed();
			    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Product')]"))).isDisplayed();
			    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Paid Out Deals By Broker')]"))).isDisplayed();
						
				Sleep(2000);
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate to Application landing page1");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
				driver.findElement(By.xpath("//a[contains(text(),' NEW PROPOSAL')]")).click();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'New Proposal ')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Business Information')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Loan Information')]"))).isDisplayed();
							
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Director Information')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Documents')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Limited Company')]"))).isDisplayed();
								
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Limited liability partnership')]"))).isDisplayed();
								
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("clearbtn"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("steponebutton"))).isDisplayed();
								
				Sleep(2000);
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate to NEW PROPOSAL Page");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
							 
								 
				driver.findElement(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]")).click();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Banking Status')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Allied Irish(NI)')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Bank of Scotland Commercial')]"))).isDisplayed();
									
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Barclays Corporate')]"))).isDisplayed();
									
				Sleep(2000);
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate to OPEN BANKING STATUS Page");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
								 
								
				driver.findElement(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]")).click();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Accounting Status')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Dynamics 365 Business Central')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'QuickBooks Desktop')]"))).isDisplayed();
										
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Wave')]"))).isDisplayed();
										
										Sleep(2000);
										 Screenshot();
										 WriteExtentReport =test1.createNode("Navigate to OPEN ACCOUNTING STATUS Page");
										 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
									 
									 
					  driver.findElement(By.xpath("//a[contains(text(),' PRODUCT SUPPORT')]")).click();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Nucleus Product Support Document')] "))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Nucleus Introducer Brochure')]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[1]"))).isDisplayed();
											
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Product Factsheets')] "))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Nucleus Business Loans'])[1]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[2]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Revenue Based Loans'])[1]	"))).isDisplayed();
												
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[3]"))).isDisplayed();
												
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Nucleus Business Loans'])[2]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[4]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Revenue Based Loans'])[2]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),' View')])[5]"))).isDisplayed();
					  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Product checklist')]"))).isDisplayed();
												
											
						Sleep(2000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to PRODUCT SUPPORT Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
								
						
						driver.findElement(By.xpath("//a[contains(text(),'BROKER ADMIN')]")).click();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Broker Admin')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add Broker')]"))).isDisplayed();
						
						Sleep(2000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to BROKER ADMIN Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					  
												 
					    driver.findElement(By.xpath("//a[contains(text(),' QUERIES')]")).click();
					    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Open Queries')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Unresolved')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Resolved')]"))).isDisplayed();						
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='search'])[1]"))).isDisplayed();
						Sleep(2000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to QUERIES Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					    driver.findElement(By.xpath("//a[contains(text(),' SIC Codes')]")).click();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'SIC Codes')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='Sic_Segment_ID']"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
														
						Sleep(2000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to SIC Codes Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
												  
						driver.findElement(By.xpath("//a[contains(text(),' LOGOUT')]")).click();
													 
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign In')]"))).isDisplayed();
													 
					    Sleep(2000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to LOGOUT Page");
					    WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
												  					 
			}
		
		catch(Exception e) {
		
			String Error = e.toString();
			System.out.println(Error);
			Screenshot();
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		}
		}

		
		
		@Test(enabled=false)
		public void TestCase6() throws Exception  {
			
			
			 test1 = extent.createTest("Test Case 6", "MyAdmin Portal");
			

			try {
			
			 driver.get("https://myadmin.myfundingportal.co.uk/");
			 Screenshot(); 
			 WriteExtentReport =test1.createNode("Navigate Login Page ");
			 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
							
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign In')]"))).isDisplayed();
			
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("shahrukh.aatar@mypulse.io");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("brokerportal");
			driver.findElement(By.xpath("//select[@name='broker_type']")).click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'Internal')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'External')]"))).isDisplayed();
			
			driver.findElement(By.xpath("//option[contains(text(),'External')]")).click();
			driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
			
			
			
			
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Dashboard')]"))).isDisplayed(); // Dashboard text
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Dashboard')]"))).isDisplayed(); //Dashboard Tab
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Portal')])[1]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'SIC Codes')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Collection Admin')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Underwriters')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Active Portal Access')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Active PDF Access')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'SIC Codes')]"))).isDisplayed();
		
			Screenshot(); 
			WriteExtentReport =test1.createNode("Navigate  After Login Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Products')]"))).isDisplayed();
			driver.findElement(By.xpath("//span[contains(text(),'Products')]")).click();
			
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Products')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add New')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//td[contains(text(),'REVENUE BASED LOANS')])[1]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//td[contains(text(),'NUCLEUS BUSINESS LOANS')])[1]"))).isDisplayed();
			Screenshot(); 
			WriteExtentReport =test1.createNode("Navigate  Products Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
			
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Sic Segments')]"))).isDisplayed();
			
			driver.findElement(By.xpath("//span[contains(text(),'Sic Segments')]")).click();
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'SIC Segments')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add New')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'U - Activities of Nonclassifiable Establishments')]"))).isDisplayed();
			
			Screenshot(); 
			WriteExtentReport =test1.createNode("Navigate  Sic Segments Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			
            driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
			
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Brokers Portal Access')]"))).isDisplayed();
			
			driver.findElement(By.xpath("//span[contains(text(),'Brokers Portal Access')]")).click();
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Brokers Portal Access')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
			
			Screenshot(); 
			WriteExtentReport =test1.createNode("Navigate Brokers Portal Access Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			
		   driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
				
				
		   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Brokers Accounts')]"))).isDisplayed();
				
		   driver.findElement(By.xpath("//span[contains(text(),'Brokers Accounts')]")).click();
				
		   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Broker Accounts')]"))).isDisplayed();
		   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
				
		   Screenshot(); 
		   WriteExtentReport =test1.createNode("Navigate  Brokers Accounts Page ");
		   WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				
				
           driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
				
				
		   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Brokers Product Access')]"))).isDisplayed();
				
		   driver.findElement(By.xpath("//span[contains(text(),'Brokers Product Access')]")).click();
				
		   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Brokers Product Access')]"))).isDisplayed();
		   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
		   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//th[contains(text(),'NBL')]"))).isDisplayed();
		   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//th[contains(text(),'RBL')]"))).isDisplayed();
				
		   Screenshot(); 
		   WriteExtentReport =test1.createNode("Navigate  Brokers Product Access Page ");
		   WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				
				
				
		   driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
					
					
		   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Reapplication Criteria')]"))).isDisplayed();
					
					driver.findElement(By.xpath("//span[contains(text(),'Reapplication Criteria')]")).click();
					
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Reapplication Criteria')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='product']"))).isDisplayed();
					
					Screenshot(); 
					WriteExtentReport =test1.createNode("Navigate  Reapplication Criteria Page ");
					WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					
					 driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
						
						
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Finance Providers')]"))).isDisplayed();
						
						driver.findElement(By.xpath("//span[contains(text(),'Finance Providers')]")).click();
						
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Finance Providers')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add New')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]"))).isDisplayed();
						
						Screenshot(); 
						WriteExtentReport =test1.createNode("Navigate  Finance Providers Page ");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					
						 driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
							
							
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Card Providers')]"))).isDisplayed();
							
							driver.findElement(By.xpath("//span[contains(text(),'Card Providers')]")).click();
							
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Card Providers')]"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add New')]"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]"))).isDisplayed();
							
							Screenshot(); 
							WriteExtentReport =test1.createNode("Navigate  Card Providers Page ");
							WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						
							
                            driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
							
							
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'No Potential Lenders')]"))).isDisplayed();
							
							driver.findElement(By.xpath("//span[contains(text(),'No Potential Lenders')]")).click();
							
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'No Potential Lenders')]"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add New')]"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]"))).isDisplayed();
							
							Screenshot(); 
							WriteExtentReport =test1.createNode("Navigate  No Potential Lenders Page ");
							WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						
                            driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
							
							
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Base Rate')]"))).isDisplayed();
							
							driver.findElement(By.xpath("//span[contains(text(),'Base Rate')]")).click();
							
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Update Pricing')]"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'BOE Rate')]"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Pricing')]"))).isDisplayed();
							
							Screenshot(); 
							WriteExtentReport =test1.createNode("Navigate  Base Rate Page ");
							WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						
							
                            driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
							
							
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Banking Data Classification')]"))).isDisplayed();
							
							driver.findElement(By.xpath("//span[contains(text(),'Banking Data Classification')]")).click();
							
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Banking Data Classification')]"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]"))).isDisplayed();
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Update')]//following::a)[1]"))).isDisplayed();//Add New Button
							
							Screenshot(); 
							WriteExtentReport =test1.createNode("Navigate Banking Data Classification Page ");
							WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						
							
							   driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
								
								
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Risk Check')]"))).isDisplayed();
								
								driver.findElement(By.xpath("//span[contains(text(),'Risk Check')]")).click();
								
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Risk Check')]"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search']"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add New')]"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Upload')]"))).isDisplayed();
								
								Screenshot(); 
								WriteExtentReport =test1.createNode("Navigate Risk Check Page ");
								WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
							
								 driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
									
									
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Broker Arrangement Fee Criteria')]"))).isDisplayed();
									
									driver.findElement(By.xpath("//span[contains(text(),'Broker Arrangement Fee Criteria')]")).click();
									
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Broker Arrangement Fee Criteria')]"))).isDisplayed();
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]"))).isDisplayed();
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add New')]"))).isDisplayed();
								
									
									Screenshot(); 
									WriteExtentReport =test1.createNode("Navigate Broker Arrangement Fee Criteria Page ");
									WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
								
									
									
									 driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
										
										
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'SIC Codes')]"))).isDisplayed();
										
										driver.findElement(By.xpath("//span[contains(text(),'SIC Codes')]")).click();
										
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'SIC Codes')]"))).isDisplayed();
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search']"))).isDisplayed();
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add New')]"))).isDisplayed();
									
										
										Screenshot(); 
										WriteExtentReport =test1.createNode("Navigate SIC Codes Page ");
										WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
									
										 driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
											
											
											new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Collection Admin')]"))).isDisplayed();
											
											driver.findElement(By.xpath("//span[contains(text(),'Collection Admin')]")).click();
											
											new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'myCollection Portal Access')]"))).isDisplayed();
											new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
											new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Activate')]"))).isDisplayed();
											new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Deactivate')]"))).isDisplayed();
										
											
											Screenshot(); 
											WriteExtentReport =test1.createNode("Navigate Collection Admin Page ");
											WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										
											
											 driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]")).click();
												
												
												new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Underwriters')]"))).isDisplayed();
												
												driver.findElement(By.xpath("//span[contains(text(),'Underwriters')]")).click();
												
												new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Underwriters')]"))).isDisplayed();
												new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();
											
												
												Screenshot(); 
												WriteExtentReport =test1.createNode("Navigate Underwriters Page ");
												WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
											
												
												new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@id='userDropdown']"))).isDisplayed();
												 driver.findElement(By.xpath("//img[@id='userDropdown']")).click();
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign out')]"))).isDisplayed();
												 driver.findElement(By.xpath("//a[contains(text(),'Sign out')]")).click();
												 Screenshot(); 
													WriteExtentReport =test1.createNode("Navigate After Logout Page ");
													WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
												
											
									
			/*Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath("(//span[contains(text(),'Portal')])[1]"));
			action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Products')]"))).click().build().perform();*/
			
			
			
		}
		
		catch(Exception e) {
		
			String Error = e.toString();
			Screenshot();
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

			
		}
		}
		
		
		@Test(enabled=false)
		public void TestCase7() throws Exception  {
			
			
			 test1 = extent.createTest("Test Case 7", "Infinity Portal Direct Role-Individual");
			
			try {
			
			driver.get("https://www.myfundingportal.co.uk/login");
			Sleep(2000);
			 Screenshot(); 
				WriteExtentReport =test1.createNode("Navigate Login Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
							
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Sign in')]"))).isDisplayed();
			
			
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("shahrukh.aatar@mypulse.io");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("portal123");
			driver.findElement(By.xpath("//input[@id='login']")).click();
			
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Welcome to the Infinity Funding portal')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' DASHBOARD')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' NEW PROPOSAL')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'BDM PERFORMANCE')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' LOGOUT')]"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search_name']"))).isDisplayed();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
			Sleep(2000);
			Screenshot(); 
			WriteExtentReport =test1.createNode("Navigate After Login Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
		      js.executeScript("window.scrollBy(0,600)");
			
		      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Quarter')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Product')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Paid Out Deals By Broker')]"))).isDisplayed();
				Sleep(2000);
				Screenshot(); 
				WriteExtentReport =test1.createNode("Navigate After Login1 Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				System.out.println("1111111111111111111111111111");
				 driver.findElement(By.xpath("//a[contains(text(),' NEW PROPOSAL')]")).click();
				 System.out.println("22222222222222222222222222222");
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'New Proposal')]"))).isDisplayed();
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Business Information')]"))).isDisplayed();
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Loan Information')]"))).isDisplayed();
			     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Director Information')]"))).isDisplayed();
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Lender Results')]"))).isDisplayed();
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Infinity_lead']"))).isDisplayed();
						
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='comp_name']"))).isDisplayed();
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='clearbtn']"))).isDisplayed();
				
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='steponebutton']"))).isDisplayed();
					
				 Sleep(2000);
				    Screenshot(); 
					WriteExtentReport =test1.createNode("Navigate NEW PROPOSAL Page ");
					WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					
				/*	new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
					
					driver.findElement(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]")).click();
					 
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Banking Status')]"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Business Information')]"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Loan Information')]"))).isDisplayed();
				     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Director Information')]"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Lender Results')]"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Infinity_lead']"))).isDisplayed();
							
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='comp_name']"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='clearbtn']"))).isDisplayed();
					
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='steponebutton']"))).isDisplayed();
						
					  Sleep(2000);
					  Screenshot(); 
					  WriteExtentReport =test1.createNode("Navigate NEW PROPOSAL Page ");
					  WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						*/
						
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
					
					driver.findElement(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]")).click();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Banking Status')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Allied Irish(NI)')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Bank of Scotland Commercial')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Barclays Corporate')]"))).isDisplayed();
							
					Sleep(2000);
					Screenshot(); 
					WriteExtentReport =test1.createNode("Navigate OPEN BANKING STATUS Page ");
					WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
								
								
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
								
								 driver.findElement(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]")).click();
								   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Accounting Status')]"))).isDisplayed();
								   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Dynamics 365 Business Central')]"))).isDisplayed();
								   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'QuickBooks Desktop')]"))).isDisplayed();
								   new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Wave')]"))).isDisplayed();
									
									Sleep(2000);
									 Screenshot();
									 WriteExtentReport =test1.createNode("Navigate to OPEN ACCOUNTING STATUS Page");
									 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
								 	
									 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'BDM PERFORMANCE')]"))).isDisplayed();
									 driver.findElement(By.xpath("//a[contains(text(),'BDM PERFORMANCE')]")).click();
									 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'BDM Performance')]"))).isDisplayed();
									  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='date_range_apr']"))).isDisplayed();
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//select[@id='agent'])[2]"))).isDisplayed();
										
										 Sleep(2000);
										 Screenshot();
										 WriteExtentReport =test1.createNode("Navigate to BDM PERFORMANCE Page");
										 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
									 	
										 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
										 driver.findElement(By.xpath("//a[contains(text(),' SIC Codes')]")).click();
										 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'SIC Codes')]"))).isDisplayed();
										  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='Sic_Segment_ID']"))).isDisplayed();
											new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
												
											Sleep(2000);
											 Screenshot();
											 WriteExtentReport =test1.createNode("Navigate to SIC Codes Page");
											 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										  
											 
											 driver.findElement(By.xpath("//a[contains(text(),' LOGOUT')]")).click();
											 
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login']"))).isDisplayed();
											
											 Sleep(2000);
											 Screenshot();
											 WriteExtentReport =test1.createNode("Navigate to LOGOUT Page");
											 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										  							 
										 
		}
		
		catch(Exception e) {
		
			String Error = e.toString();
			System.out.println(Error);
			Screenshot();
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

			
		}
		}

		

		@Test(enabled=false)
		public void TestCase8() throws Exception  {
			
			
			 test1 = extent.createTest("Test Case 8", "Infinity Portal Tele Role");
			try {	
				
				driver.get("https://www.myfundingportal.co.uk/login");
				Sleep(2000);
				 Screenshot(); 
					WriteExtentReport =test1.createNode("Navigate Login Page ");
					WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Sign in')]"))).isDisplayed();
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("shahrukh.aatar@mypulse.io");
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys("portal12");
				driver.findElement(By.xpath("//input[@id='login']")).click();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Welcome to the Infinity Funding portal')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' DASHBOARD')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' NEW PROPOSAL')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'SELF CALLBACK')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'AGENT PERFORMANCE')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' LOGOUT')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search_name']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
				Sleep(2000);
				Screenshot(); 
				WriteExtentReport =test1.createNode("Navigate After Login Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
			      js.executeScript("window.scrollBy(0,600)");
				
			    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Quarter')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Product')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Paid Out Deals By Broker')]"))).isDisplayed();
				Sleep(2000);
				Screenshot(); 
				WriteExtentReport =test1.createNode("Navigate After Login1 Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				System.out.println("1111111111111111111111111111");
				driver.findElement(By.xpath("//a[contains(text(),' NEW PROPOSAL')]")).click();
				System.out.println("22222222222222222222222222222");
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'New Proposal')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Business Information')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Loan Information')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Director Information')]"))).isDisplayed();
			    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Lender Results')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Infinity_lead']"))).isDisplayed();
							
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='comp_name']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='clearbtn']"))).isDisplayed();
					
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='steponebutton']"))).isDisplayed();
						
					    Sleep(2000);
					    Screenshot(); 
				WriteExtentReport =test1.createNode("Navigate NEW PROPOSAL Page ");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						
					
							
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
						
							driver.findElement(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]")).click();
							  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Banking Status')]"))).isDisplayed();
							  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Allied Irish(NI)')]"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Bank of Scotland Commercial')]"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Barclays Corporate')]"))).isDisplayed();
								
								Sleep(2000);
								    Screenshot(); 
									WriteExtentReport =test1.createNode("Navigate OPEN BANKING STATUS Page ");
									WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
									
									
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
									
									 driver.findElement(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]")).click();
									 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Accounting Status')]"))).isDisplayed();
									  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Dynamics 365 Business Central')]"))).isDisplayed();
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'QuickBooks Desktop')]"))).isDisplayed();
										
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Wave')]"))).isDisplayed();
										
										Sleep(2000);
										 Screenshot();
										 WriteExtentReport =test1.createNode("Navigate to OPEN ACCOUNTING STATUS Page");
										 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
									 	
										 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'SELF CALLBACK')]"))).isDisplayed();
										 driver.findElement(By.xpath("//a[contains(text(),'SELF CALLBACK')]")).click();
										 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Self CallBack')]"))).isDisplayed();
										
											 Sleep(2000);
											 Screenshot();
											 WriteExtentReport =test1.createNode("Navigate to SELF CALLBACK Page");
											 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										 	
											 
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'AGENT PERFORMANCE')]"))).isDisplayed();
											 driver.findElement(By.xpath("//a[contains(text(),'AGENT PERFORMANCE')]")).click();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Agent Performance')]"))).isDisplayed();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='date_range_apr']"))).isDisplayed();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//select[@id='agent'])[2]"))).isDisplayed();
												
											
												 Sleep(2000);
												 Screenshot();
												 WriteExtentReport =test1.createNode("Navigate to AGENT PERFORMANCE Page");
												 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
											 	
												
											
											 
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
											 driver.findElement(By.xpath("//a[contains(text(),' SIC Codes')]")).click();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'SIC Codes')]"))).isDisplayed();
											  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='Sic_Segment_ID']"))).isDisplayed();
												new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
													
												Sleep(2000);
												 Screenshot();
												 WriteExtentReport =test1.createNode("Navigate to SIC Codes Page");
												 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
											  
												 
												 driver.findElement(By.xpath("//a[contains(text(),' LOGOUT')]")).click();
												 
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login']"))).isDisplayed();
												
												 Sleep(2000);
												 Screenshot();
												 WriteExtentReport =test1.createNode("Navigate to LOGOUT Page");
												 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										
				
				
				
					
		}
		
		catch(Exception e) {
		
			String Error = e.toString();
			System.out.println(Error);
			Screenshot();
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

			
		}
		}

		
		
		@Test(enabled=false)
		public void TestCase9() throws Exception  {
			
			
			 test1 = extent.createTest("Test Case 9", "Infinity Portal Admin Direct Role");
			
			try {
					driver.get("https://www.myfundingportal.co.uk/login");
														
					Sleep(2000);
					 Screenshot(); 
						WriteExtentReport =test1.createNode("Navigate Login Page ");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
									
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Sign in')]"))).isDisplayed();
					
					
					driver.findElement(By.xpath("//input[@id='email']")).sendKeys("shahrukh.aatar@mypulse.io");
					driver.findElement(By.xpath("//input[@id='password']")).sendKeys("portal123");
					driver.findElement(By.xpath("//input[@id='login']")).click();
					
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Welcome to the Infinity Funding portal')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' DASHBOARD')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' NEW PROPOSAL')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'BDM PERFORMANCE')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' LOGOUT')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search_name']"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
					Sleep(2000);
					Screenshot(); 
					WriteExtentReport =test1.createNode("Navigate After Login Page ");
					WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					
					
					
					JavascriptExecutor js = (JavascriptExecutor) driver;
				      js.executeScript("window.scrollBy(0,600)");
					
				      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Quarter')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Product')]"))).isDisplayed();
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Paid Out Deals By Broker')]"))).isDisplayed();
						Sleep(2000);
						Screenshot(); 
						WriteExtentReport =test1.createNode("Navigate After Login1 Page ");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						System.out.println("1111111111111111111111111111");
						 driver.findElement(By.xpath("//a[contains(text(),' NEW PROPOSAL')]")).click();
						 System.out.println("22222222222222222222222222222");
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'New Proposal')]"))).isDisplayed();
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Business Information')]"))).isDisplayed();
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Loan Information')]"))).isDisplayed();
					     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Director Information')]"))).isDisplayed();
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Lender Results')]"))).isDisplayed();
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Infinity_lead']"))).isDisplayed();
								
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='comp_name']"))).isDisplayed();
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='clearbtn']"))).isDisplayed();
						
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='steponebutton']"))).isDisplayed();
							
						 Sleep(2000);
						    Screenshot(); 
							WriteExtentReport =test1.createNode("Navigate NEW PROPOSAL Page ");
							WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
							
						
								
							new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
							
								driver.findElement(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]")).click();
								  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Banking Status')]"))).isDisplayed();
								  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Allied Irish(NI)')]"))).isDisplayed();
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Bank of Scotland Commercial')]"))).isDisplayed();
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Barclays Corporate')]"))).isDisplayed();
									
									Sleep(2000);
									    Screenshot(); 
										WriteExtentReport =test1.createNode("Navigate OPEN BANKING STATUS Page ");
										WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										
										
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
										
										 driver.findElement(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]")).click();
										 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Accounting Status')]"))).isDisplayed();
										  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Dynamics 365 Business Central')]"))).isDisplayed();
											new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'QuickBooks Desktop')]"))).isDisplayed();
											
											new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Wave')]"))).isDisplayed();
											
											Sleep(2000);
											 Screenshot();
											 WriteExtentReport =test1.createNode("Navigate to OPEN ACCOUNTING STATUS Page");
											 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										 	
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'BDM PERFORMANCE')]"))).isDisplayed();
											 driver.findElement(By.xpath("//a[contains(text(),'BDM PERFORMANCE')]")).click();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'BDM Performance')]"))).isDisplayed();
											  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
												new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='date_range_apr']"))).isDisplayed();
												new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//select[@id='agent'])[2]"))).isDisplayed();
												
												 Sleep(2000);
												 Screenshot();
												 WriteExtentReport =test1.createNode("Navigate to BDM PERFORMANCE Page");
												 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
											 	
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
												 driver.findElement(By.xpath("//a[contains(text(),' SIC Codes')]")).click();
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'SIC Codes')]"))).isDisplayed();
												  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='Sic_Segment_ID']"))).isDisplayed();
													new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
														
													Sleep(2000);
													 Screenshot();
													 WriteExtentReport =test1.createNode("Navigate to SIC Codes Page");
													 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
												  
													 
													 driver.findElement(By.xpath("//a[contains(text(),' LOGOUT')]")).click();
													 
													 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
													 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
													 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login']"))).isDisplayed();
													
													 Sleep(2000);
													 Screenshot();
													 WriteExtentReport =test1.createNode("Navigate to LOGOUT Page");
													 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					
					
		}
		
		catch(Exception e) {
		
			String Error = e.toString();
			Screenshot();
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

			
		}
		}

			
		
		

		@Test(enabled=false)
		public void TestCase10() throws Exception  {
			
			 test1 = extent.createTest("Test Case 10", "Infinity Portal Admin Tele Role");
			try {
				driver.get("https://www.myfundingportal.co.uk/login");
				Sleep(2000);
				 Screenshot(); 
				 WriteExtentReport =test1.createNode("Navigate Login Page ");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
								
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Sign in')]"))).isDisplayed();
				
				
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("shahrukh.aatar@mypulse.io");
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys("portal121");
				driver.findElement(By.xpath("//input[@id='login']")).click();
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Welcome to the Infinity Funding portal')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' DASHBOARD')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' NEW PROPOSAL')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'SELF CALLBACK')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'AGENT PERFORMANCE')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' LOGOUT')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search_name']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
				Sleep(2000);
				Screenshot(); 
				WriteExtentReport =test1.createNode("Navigate After Login Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
			      js.executeScript("window.scrollBy(0,600)");
				
			      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Quarter')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'All Deals By Product')]"))).isDisplayed();
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Paid Out Deals By Broker')]"))).isDisplayed();
					Sleep(2000);
					Screenshot(); 
					WriteExtentReport =test1.createNode("Navigate After Login1 Page ");
					WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
					System.out.println("1111111111111111111111111111");
					 driver.findElement(By.xpath("//a[contains(text(),' NEW PROPOSAL')]")).click();
					 System.out.println("22222222222222222222222222222");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'New Proposal')]"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Business Information')]"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Loan Information')]"))).isDisplayed();
				     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Director Information')]"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Lender Results')]"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Infinity_lead']"))).isDisplayed();
							
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='comp_name']"))).isDisplayed();
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='clearbtn']"))).isDisplayed();
					
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='steponebutton']"))).isDisplayed();
						
					 Sleep(2000);
					    Screenshot(); 
						WriteExtentReport =test1.createNode("Navigate NEW PROPOSAL Page ");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
						
					
							
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]"))).isDisplayed();
						
							driver.findElement(By.xpath("//a[contains(text(),' OPEN BANKING STATUS')]")).click();
							  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Banking Status')]"))).isDisplayed();
							  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Allied Irish(NI)')]"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Bank of Scotland Commercial')]"))).isDisplayed();
								new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Barclays Corporate')]"))).isDisplayed();
								
								Sleep(2000);
								    Screenshot(); 
									WriteExtentReport =test1.createNode("Navigate OPEN BANKING STATUS Page ");
									WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
									
									
									new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]"))).isDisplayed();
									
									 driver.findElement(By.xpath("//a[contains(text(),' OPEN ACCOUNTING STATUS')]")).click();
									 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'Open Accounting Status')]"))).isDisplayed();
									  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Dynamics 365 Business Central')]"))).isDisplayed();
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'QuickBooks Desktop')]"))).isDisplayed();
										
										new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'Wave')]"))).isDisplayed();
										
										Sleep(2000);
										 Screenshot();
										 WriteExtentReport =test1.createNode("Navigate to OPEN ACCOUNTING STATUS Page");
										 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
									 	
										 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'SELF CALLBACK')]"))).isDisplayed();
										 driver.findElement(By.xpath("//a[contains(text(),'SELF CALLBACK')]")).click();
										 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Self CallBack')]"))).isDisplayed();
										
											 Sleep(2000);
											 Screenshot();
											 WriteExtentReport =test1.createNode("Navigate to SELF CALLBACK Page");
											 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										 	
											 
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'AGENT PERFORMANCE')]"))).isDisplayed();
											 driver.findElement(By.xpath("//a[contains(text(),'AGENT PERFORMANCE')]")).click();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Agent Performance')]"))).isDisplayed();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='date_range_apr']"))).isDisplayed();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//select[@id='agent'])[2]"))).isDisplayed();
												
											
												 Sleep(2000);
												 Screenshot();
												 WriteExtentReport =test1.createNode("Navigate to AGENT PERFORMANCE Page");
												 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
											
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' SIC Codes')]"))).isDisplayed();
											 driver.findElement(By.xpath("//a[contains(text(),' SIC Codes')]")).click();
											 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'SIC Codes')]"))).isDisplayed();
											  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='Sic_Segment_ID']"))).isDisplayed();
												new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]"))).isDisplayed();
													
												Sleep(2000);
												 Screenshot();
												 WriteExtentReport =test1.createNode("Navigate to SIC Codes Page");
												 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
											  
												 
												 driver.findElement(By.xpath("//a[contains(text(),' LOGOUT')]")).click();
												 
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
												 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login']"))).isDisplayed();
												
												 Sleep(2000);
												 Screenshot();
												 WriteExtentReport =test1.createNode("Navigate to LOGOUT Page");
												 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
										
							
		   }
		catch(Exception e) {
			String Error = e.toString();
			System.out.println(Error);
			Screenshot();
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		}
		}

		
		@Test(enabled=false)
		public void TestCase11() throws Exception  {
			
			
			 test1 = extent.createTest("Test Case 11", "Mycollection");
			try {	
	
				driver.get("https://mycollection.myfundingportal.co.uk/login");
				
				Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to Login Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 	
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='myCollection']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),' Sign in')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Forgot password?')]"))).isDisplayed();
				
				
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("anil.jain@nucleus-cf.co.uk");
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys("mycollection@123");
				driver.findElement(By.xpath("//input[@id='login']")).click();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.className("welcometext"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.user-profile"))).isDisplayed();// MyCollection Portal
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='active']//child::a"))).isDisplayed(); //Dashboard
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-plus']//parent::a"))).isDisplayed();//DD Mandate
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-university']//parent::a"))).isDisplayed();//Bouncing & Posting
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-user']//parent::a"))).isDisplayed();//PTX Contact
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-users']//parent::a"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-cog']//parent::a"))).isDisplayed();//Admin
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fas fa-sign-out-alt']//parent::a"))).isDisplayed();//Logout
				
				Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to After Login Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 	
				
				
				driver.findElement(By.xpath("//i[@class='fa fa-plus']//parent::a")).click();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'DD MANDATE')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='product']"))).isDisplayed();//Select Product
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='portfoliodef']"))).isDisplayed();//All
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='date']"))).isDisplayed();//Select Monthly DD Date
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();//SEARCH
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Active Direct Debit')]"))).isDisplayed();//Active Direct Debit
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Inactive Direct Debit')]"))).isDisplayed();//Inactive Direct Debit
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Standing Order')]"))).isDisplayed();//Standing Order
				
				Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to DD MANDATE Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 	
			
				
				driver.findElement(By.xpath("//i[@class='fa fa-university']//parent::a")).click();//BOUNCE & POSTING
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'BOUNCE & POSTING')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='product']"))).isDisplayed();//Select Product
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='portfoliodef']"))).isDisplayed();//All
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='date']"))).isDisplayed();//Select Monthly DD Date
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();//SEARCH
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'All Deals')]"))).isDisplayed();//Active Direct Debit
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Bounce Deals')]"))).isDisplayed();//Inactive Direct Debit
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Paid / Settled')]"))).isDisplayed();//Standing Order
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Posting Deals')]"))).isDisplayed();//Standing Order
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='search'])[1]"))).isDisplayed();//Standing Order
				
				Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to BOUNCE & POSTING Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 	
				
				driver.findElement(By.xpath("//i[@class='fa fa-user']//parent::a")).click();//PTX Contact
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'PTX Contact')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='product']"))).isDisplayed();//Select Product
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='portfoliodef']"))).isDisplayed();//All
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='date']"))).isDisplayed();//Select Monthly DD Date
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();//SEARCH
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'PTX Contact Upload')]"))).isDisplayed();//PTX Contact Upload
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'PTX Mandate Upload')]"))).isDisplayed();//PTX Mandate Upload
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Create Another Mandate')]"))).isDisplayed();//Create Another Mandate
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Update Mandate')]"))).isDisplayed();//Update Mandate
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='search'])[1]"))).isDisplayed();//search field
				
				
				Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to PTX Contact Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 	
				
				
				driver.findElement(By.xpath("//i[@class='fa fa-users']//parent::a")).click();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'ASSIGN AGENT')]"))).isDisplayed();//ASSIGN AGENT Text only
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='product']"))).isDisplayed();//Select Product
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='portfoliodef']"))).isDisplayed();//All
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SEARCH')]"))).isDisplayed();//SEARCH
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'All Deals')]"))).isDisplayed();//PTX Contact Upload
				
				
				Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to ASSIGN AGENT Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 	
				
				
				driver.findElement(By.xpath("//i[@class='fa fa-cog']//parent::a")).click();//Admin
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'ADMIN')]"))).isDisplayed();//ADMIN Text
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'All Users')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']"))).isDisplayed();//search field
				
				
				Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to ADMIN Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 	
				
				driver.findElement(By.xpath("//i[@class='fas fa-sign-out-alt']//parent::a")).click();//Logout
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='myCollection']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),' Sign in')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Forgot password?')]"))).isDisplayed();
				
				Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to After Logout Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 	
				
				
		}
		catch(Exception e) {
			String Error = e.toString();
			Screenshot(); 
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		}
		}
		
		
		
		
		
		
		
		@Test(enabled=false)
		public void TestCase12() throws Exception  {
			
			
			 test1 = extent.createTest("Test Case 11", "MyReporting Portal");
			
			try {	
	
				driver.get("https://reports.myfundingportal.co.uk/login");
				
				 Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to Login Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 	
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='myCollection']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),' Sign in')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login']"))).isDisplayed();
				//new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Forgot password?')]"))).isDisplayed();
				
				
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("anil.jain@mypulse.io");
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys("report@123");
				driver.findElement(By.xpath("//input[@id='login']")).click();
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'Welcome to Reports')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Finance')]"))).isDisplayed();// MyCollection Portal
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'BDM ')]"))).isDisplayed(); //Dashboard
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sales Operation ')]"))).isDisplayed();//DD Mandate
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Collections ')]"))).isDisplayed();//Bouncing & Posting
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Monitoring Reports')]"))).isDisplayed();//PTX Contact
				//new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.user-profile dropbtn"))).isDisplayed();//Assign Agent
				
				 Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to After Login Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
				
				WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Finance')]"));
				 Actions action= new Actions(driver);
				action.moveToElement(element).build().perform();
				
				
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Static Report')]"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Dynamic Report')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Origination Report')]"))).isDisplayed();// MyCollection Portal
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Daily Bank Transaction Report')]"))).isDisplayed(); //Dashboard
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Monthly Interest Report')]"))).isDisplayed();//DD Mandate
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Reconcile Report')]"))).isDisplayed();//Bouncing & Posting
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Outstanding Arrears Report')]"))).isDisplayed();//PTX Contact
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Settled Arrears Report')]"))).isDisplayed();//PTX Contact
				 
				 Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to Finance Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
				
				WebElement element1 = driver.findElement(By.xpath("//button[contains(text(),'BDM ')]"));
				action.moveToElement(element1).build().perform();
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'BDM Performance')])[1]"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Broker Performance')])[1]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'BDM Performance Matrix')])[1]"))).isDisplayed();// MyCollection Portal
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Broker Performance Matrix')])[1]"))).isDisplayed(); //Dashboard
				//new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Monthly Interest Report')]"))).isDisplayed();//DD Mandate
				//new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Reconcile Report')]"))).isDisplayed();//Bouncing & Posting
				
				 Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to BDM Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
				
				
				
				WebElement element2 = driver.findElement(By.xpath("//button[contains(text(),'Sales Operation ')]"));
				action.moveToElement(element2).build().perform();
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Performance')])[5]"))).isDisplayed();//Assign Agent
				
				
				 Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to Sales Operation Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
				
				WebElement element3 = driver.findElement(By.xpath("//button[contains(text(),'Collections ')]"));
				action.moveToElement(element3).build().perform();
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Outsourced Deals Matrix')])"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Outsourced Deals Details')])"))).isDisplayed();//Assign Agent
				
				
				 Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to Collections Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
				
				
				WebElement element4 = driver.findElement(By.xpath("//button[contains(text(),'Monitoring Reports')]"));
				action.moveToElement(element4).build().perform();
				
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Company Status Monitoring')]"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Company Financial Monitoring')]"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'API Performance')]"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Pulse Tracking')]"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Deal Amortization')]"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Open Banking Tracking')]"))).isDisplayed();//Assign Agent
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Open Accounting Tracking')]"))).isDisplayed();//Assign Agent
				
				 Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to Monitoring Reports Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
				
				WebElement element5 = driver.findElement(By.xpath("//a[@class='user-profile dropbtn']"));
				action.moveToElement(element5).build().perform();
				
				driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='myCollection']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),' Sign in')]"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']"))).isDisplayed();
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login']"))).isDisplayed();
				 Sleep(2000);
				 Screenshot();
				 WriteExtentReport =test1.createNode("Navigate to After Logout Page");
				 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
				
		}
		catch(Exception e) {
			String Error = e.toString();
			Screenshot(); 
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		}
		}
		
		
		

		

	/*	@Test
		public void InfinityTest() throws Exception{
			
			 test1 = extent.createTest("Test", "Single Dir");
			//  test1.log(Status.INFO, "Starting test case");
			 // test1.log(Status.PASS, "Some test step");

			try {
				
			ArrayList data=d.getData("Test2");
			String url = (String) data.get(1);
			driver.get(url);
			String Username= (String) data.get(2);
			String Password= (String) data.get(3);
			String CompanyName=(String) data.get(4);
			String Email = (String) data.get(5);
			String PhoneNumber = (String) data.get(6);
			String BirthDay = (String) data.get(7);
			String PostCode = (String) data.get(8);
			String HouseNumber = (String) data.get(9);
			String HouseName = (String) data.get(10);
			String City = (String) data.get(11);
			String Street = (String) data.get(12);
			String Country = (String) data.get(13);
			String Fund = (String) data.get(14);
			String Months = (String) data.get(15);
			String Percent = (String) data.get(16);
			System.out.println(Percent);
			String BirthDay1= (String) data.get(17);
			String Email1 = (String) data.get(18);
			String MobileNumber = (String) data.get(19);
			String PhoneNO = (String) data.get(20);
			String url1 = (String) data.get(21);

			Screenshot();
			//test1.log(Status.PASS, "Login Page");
			WriteExtentReport =test1.createNode("Navigate to Application landing page");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			//test1.createNode("Login Page");
			//test1.addScreenCaptureFromPath(sScreenshotFilePath);
			 InfinityLogin.login(Username,Password);
			 Screenshot();
			 WriteExtentReport = test1.createNode("Navigate to Home Page");
			 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 Sleep(5000);
			 InfinityHome.NewProposal();
			//test1.log(Status.PASS, "Click On NewProposal"+ test1.addScreenCaptureFromPath(sScreenshotFilePath));
			 Screenshot(); 
			 WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
			 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			InfinityNewProposal.EnterInfinityFundingProposal(CompanyName);
			//extent.flush();
			Sleep(5000);
			InfinityNewProposal.ClickOnInfinityFundingProposal();
			//InfinityNewProposal.LimitedCompany();
			InfinityNewProposal.ClickOnCompanyName();
			InfinityNewProposal.PrimaryDirector();
			//test1.log(Status.PASS, test1.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			//newproposalpage.LimitedCompany();
			//newproposalpage.SearchCompanyName(CompanyName);
			//newproposalpage.CompanyName();
			//test1.log(Status.PASS, "Select Company Name");
			//Screenshot();
		    //test1.log(Status.PASS, test1.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			//newproposalpage.PrimaryDirector();
			InfinityNewProposal.EnterEmail(Email);
			InfinityNewProposal.EnterPhoneNumberField(PhoneNumber);
			InfinityNewProposal.EnterBirthDay(BirthDay);
			InfinityNewProposal.EnterPostCode(PostCode);
			InfinityNewProposal.ClickOnFindAddress();
			InfinityNewProposal.SelectAddress();
			InfinityNewProposal.SelectResidentialPropertyYes();
			InfinityNewProposal.BusinessAddressSelectYes();
			InfinityNewProposal.NextButton();
			Screenshot(); 
			WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			LoanInfo.FundingNeeded(Fund);
			LoanInfo.LoanMonths(Months);
			LoanInfo.SelectPurposeFunding();
			LoanInfo.BrokerPercent(Percent);
			LoanInfo.NextStep();
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Director Information Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Sleep(5000);
			try{	
			DirectorInformation.EditDirectorDetails();
			DirectorInformation.DateofBirth(BirthDay1);
			DirectorInformation.Email(Email1);
			Sleep(1000);
			DirectorInformation.DirMobile(MobileNumber);
			
			//DirectorInformation.dirPhone(PhoneNO);
			DirectorInformation.PersonalGuaranteeYes();
			DirectorInformation.EnterPostCode(PostCode);
			DirectorInformation.ClickonFindAddress();
			Sleep(3000);
			DirectorInformation.SelectAddress();
			DirectorInformation.SelectResidentialPropertyYes();
			DirectorInformation.ClickOnSubmit();
			//test1.log(Status.PASS, "Director Information Page");
			} catch(Exception e)  
	       {  
	           System.out.println(e);  
	       }  
			Sleep(3000);
			//DirectorInformation.EditShareholderDetails();
			DirectorInformation.ClickOnNext();
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Documents Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Documents.SelectAccountingPackage();
			Sleep(3000);
			Documents.ClickOnDirector();
			Documents.ClickOnOk();
			Documents.SelectBank(Bank);
			Documents.SelectBankAccountType(BankType);
			Sleep(3000);
			Documents.ClickOnDirector1();
			Sleep(3000);
			Documents.ClickOnOk();
			Documents.SubmitButton();
			//test1.log(Status.PASS, "Documents Page");
			driver.get(url1);
			Sleep(6000);
			Set<String> handles3 = driver.getWindowHandles();
			List<String> hList3 = new ArrayList<String>(handles3);
			if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			    Office.ClickOnPulse();
				Office.SelectFirstEmail();
				//test1.log(Status.PASS, "Office Page");
				Sleep(6000);
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate Email Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				
				Office.ClickOnCompleteApplication();
				Sleep(6000);
				Set<String> handles = driver.getWindowHandles();
				List<String> hList = new ArrayList<String>(handles);
				if(switchToRightWindow("MyNucleus",hList)){
				      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
				      }
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				Office.ClickonCompleteyourOpenBanking();
				
				Sleep(6000);
				Set<String> handles1 = driver.getWindowHandles();
				List<String> hList1 = new ArrayList<String>(handles1);
				if(switchToRightWindow("myPulse - Plaid Open Banking",hList1)){
				      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
				      }
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate myPulse Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				driver.switchTo().frame(0);
			    myPulse.ClickOnContinue();
			    myPulse.ClickOnBank();
			    myPulse.ClickOnBankWebsite();
			    Sleep(6000);
			    Set<String> handles2 = driver.getWindowHandles();
				List<String> hList2 = new ArrayList<String>(handles2);
				if(switchToRightWindow("First Platypus Bank - OAuth Login Page",hList2)){
				      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
				      }
				System.out.println(driver.getTitle());
				 myPulse.ClickOnSignIn();
				//test1.pass("ClickOnSignIn");
				myPulse.ClickOnAccount();
				//test1.pass("ClickOnAccount");
				myPulse.ClickOnConnectAccountInformation();
				if(switchToRightWindow("myPulse - Plaid Open Banking",hList1)){
				      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
				      }
				System.out.println(driver.getTitle());
				Sleep(4000);
				myPulse.Congratulations();	
				Sleep(2000);
				Screenshot();
				Sleep(2000);
				WriteExtentReport =test1.createNode("Navigate Congratulations Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			   // Screenshot();
			   // test1.log(Status.PASS, test1.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			    System.out.println("11111111111111111111111111222222222222222222222222222222222222222222");
		}
		
		catch(Exception e) {
		
			String Error = e.toString();
			
			System.out.println(Error);
			//test1.fail("Error Message" +Error );
			
			Screenshot();
			WriteExtentReport =test1.createNode("Failed Page ");
			WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);
			//Screenshot();
			// test1.fail("Test Case failed check screenshot below"+test1.addScreenCaptureFromPath(sScreenshotFilePath));
			// test1.log(Status.FAIL, test1.addScreenCaptureFromPath(sScreenshotFilePath)+Error);
			//String screenshotPath = TestBase.Screenshot();
	       //test1.fail("Test Case failed check screenshot below"+test1.addScreenCaptureFromPath(screenshotPath));	
		}
			
			
			
			
		}

		
	/*	@DataProvider

	    public Object[][] Authentication() throws Exception{

	         Object[][] testObjArray = ExcelUtils.getTableArray("C://Users//ShahrukhAatar//Downloads//TestData.xlsx","sheet1");
	         return (testObjArray);

			}*/

		
		
		@AfterMethod
		public void tearDown() throws Exception {
			
			driver.quit();
			
		   }

		
		
		@AfterSuite
		public void Exit() {
			
			LocalDateTime localDate = LocalDateTime.now().minusDays(1);
			DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
			
			String Date = customFormat.format(localDate);
			
		  /*  String	statusPulse = "pass";
		    String	statusPulse1 = "fail";
			
			
			String text = 
					
					"<table width='70%' border='1' align='center'>"
	                + "<tr><th>Application</th><th>Status</th></tr>"
	                + "<tr><td>Pulse Website</td><td>" + statusPulse + "</td></tr>"
	                + "<tr><td>nucleus</td><td>" +statusPulse1 + "</td></tr>"
	                + "</tr>";
			
			
			 String[] lists = (String[]) EmailSend.sendEmailWithSqData(text);*/
			
			StringBuilder htmlContent = new StringBuilder();
			htmlContent.append("<html>\n"
	        		+ "<style>\n"
	        		+ "table, th, td {\n"
	        		+ "  border:1px solid black;\n"
	        		+ "  border-collapse: collapse;\n"
	        		+ "}")
	        .append("</style>\n"
	        		+ "<body>\n"
	        		+ "\n"
	        		+ "<h2>Hi Julius,\n"
	        		+ "\n"
	        		+ "Please find below the Core Systems Checks done for \n"
	        		+ "</h2>\n"
	        		+ "\n"
	        		+ "<table style=\"width:100%\">")
            .append(" <tr>\n"
            		+ "  <th rowspan=\"2\">Date</th>\n"
            		+ "  <th rowspan=\"2\">NCF Website</th>\n"
            		+ "  <th rowspan=\"2\">Infinity Website</th>\n"
            		+ "  <th rowspan=\"2\">Pulse Website</th>\n"
            		+ "  <th colspan=\"2\">My Nucleus Portal</th>\n"
            		+ "  <th colspan=\"2\">My Funding Portal</th>\n"
            		+ "  <th colspan=\"2\">Reports - My Funding Portal</th>\n"
            		+ "  <th rowspan=\"2\">My collections Portal</th\n"
            		+ "  </tr>")
            .append(" <tr>\n"
            		+ "    <th>Broker</th>\n"
            		+ "    <th>Admin</th>\n"
            		+ "    <th>Broker</th>\n"
            		+ "    <th>Admin</th>\n"
            		+ "    <th>Broker</th>\n"
            		+ "    <th>Admin</th>\n"
            		+ "  </tr>")
            .append(" <tr>\n"
            		+ "    <td>"+Date+"</td>\n"
            		+ "    <td>"+Nucleus+"</td>\n"
            		+ "    <td>Working</td>\n"
            		+ "    <td>Working</td>\n"
            		+ "    <td>Working</td>\n"
            		+ "    <td>Working</td>\n"
            		+ "    <td>Working</td>\n"
            		+ "    <td>Working</td>\n"
            		+ "    <td>Working</td>\n"
            		+ "    <td>Working</td>\n"
            		+ "    <td>Working</td>\n"
            		+ "   \n"
            		+ "  </tr>\n"
            		+ "</table>")
            .append("  </body>\n")
            .append("</html>");
	        
	        
	        String Content= htmlContent.toString();
	        
	        
			
			
		String test= "<table width='100%' border='1' align='center'>"
				+"<tr><th>Date</th><th>NCF Website</th><th>Infinity Website</th><th>Pulse Website</th><th>My Nucleus Portal</th><th>My Funding Portal</th><th>Reports - My Funding Portal</th><th>My collections Portal</th></tr>"
				+ "<tr><th>"+Date+"</th><th>"+Nucleus+"</th><th> </th><th></th><th>Broker</th><th>Admin</th>"
				+ "</tr>";
		//System.out.println(test);
		
		
				
			/*+"<tr><th> rowspan='2'>Date</th>"
			+ "<th> rowspan='2'>NCF Website</th>"
			+ "<th> rowspan='2'>Infinity Website</th>"
			+ "<th> rowspan='2'>Pulse Website</th>"
			+ "<th> rowspan='2'>My Nucleus Portal</th>"
			+ "<th> rowspan='2'>My Funding Portal</th>"
			+ "<th> rowspan='2'>Reports - My Funding Portal</th>"
			+ "<th> rowspan='2'>My collections Portal</th></tr>"
			
			+"<tr><th>Broker</th></tr>"
			+"<tr><th>Admin</th></tr>"
			+"<tr><th>Broker</th></tr>"
			+"<tr><th>Admin</th></tr>"
			+"<tr><th>Broker</th></tr>"
			+"<tr><th>Admin</th></tr>"
			
            +"<tr><td>date</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"<tr><td>Working</td></tr>"
            +"</tr>"
            +"</table>";*/
		
		
		String[] lists = (String[]) EmailSend.sendEmailWithSqData(Content);
	
		extent.flush();

		}
		
		/*public void CaptureScreenshot() {
			 try {
	             TakesScreenshot ts=(TakesScreenshot)driver;
	             File source=ts.getScreenshotAs(OutputType.FILE);
	             FileUtils.copyFile(source, new File("C:\\Users\\ShahrukhAatar\\OneDrive - Nucleus Services Ltd\\Documents\\Automation\\MyNucleusTest\\test-output"));
	             System.out.println("Screenshot taken");

	 } catch (Exception e) {
	             System.out.println("Exception "+e.getMessage());
	 }       
			
		}*/ 

	}

	
	
	
	
	
	
	
	
	


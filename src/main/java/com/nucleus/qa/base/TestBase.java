package com.nucleus.qa.base;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;








public class TestBase{
	
	public ExtentReports extent = ExtentReporterNG.getReportObject();
    public ExtentTest test;
	
	public static WebDriver driver;
	public static Properties prop;
	public static String Basepathet;
	public static String sReportPath;
	public static String SScreenshotdirpath;
	public static String sScreenshotFilePath;
	public static String path1;
	public static String path;
	
	//static ExtentReports extent;
	
	/*public TestBase() {
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:/Users/ShahrukhAatar/OneDrive - Nucleus Services Ltd/Documents/Automation/MyNucleusTest/src/main/java/com/nucleus/qa/config/config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		
	}*/
	
	public static void CaptureScreenshot() throws AWTException
    {


                try {
                            TakesScreenshot ts=(TakesScreenshot)driver;
                            File source=ts.getScreenshotAs(OutputType.FILE);
                            FileUtils.copyFile(source, new File("C:\\Users\\Documents\\Eclipse\\WorkSpace\\ScreenShot\\"));

                            System.out.println("Screenshot taken");


                } catch (Exception e) {

                            System.out.println("Exception "+e.getMessage());

                }            
    }
	
	public static void Sleep(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public  void switchToWindow(String windowTitle) {
	    Set<String> windows = driver.getWindowHandles();
	    for (String window : windows) {
	        driver.switchTo().window(window);
	        if (driver.getTitle().contains(windowTitle)) {
	            return;
	        }
	    }
	}
	
	public static void Screenshot1() throws Exception {
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("C:\\Users\\ShahrukhAatar\\OneDrive - Nucleus Services Ltd\\Documents\\Automation\\MyNucleusTest\\test-output\\screenshot.png"));
		//DirectorInformation.SecondDirectorResidentialPropertyYes();
		
		
	}
	
	
	public static void initialization() {
		
	
		//ArrayList data=d.getData("");
		//String url =(String) data.get(1);
		System.setProperty("webdriver.edge.driver", "C://Users//ShahrukhAata_l4//Project//Test//driver//edgedriver_win64//msedgedriver.exe");
		driver = new EdgeDriver();
		//WebDriver driver = new EdgeDriver();
		
		/*String browserName=prop.getProperty("browser");
		 * 
		 
		
		if(browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "C://Users//ShahrukhAatar//Downloads//edgedriver_win64//msedgedriver.exe");
			driver = new EdgeDriver();
		}else if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C://Users//ShahrukhAatar//Downloads//edgedriver_win64//chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "C://Users//ShahrukhAatar//Downloads//edgedriver_win64//geckodriver.exe");
			driver = new FirefoxDriver();
	    }*/
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
}
	public static void initializationoffice() {
		
		
		System.setProperty("webdriver.edge.driver", "C://Users//ShahrukhAata_l4//Project//Test//driver//edgedriver_win64//msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
		//driver.get(prop.getProperty("url"));
		driver.get("https://outlook.office.com/mail/");
		
	}
	
	//"C:\Users\ShahrukhAata_l4\Project\Test\driver\edgedriver_win64\msedgedriver.exe"
	public static void initializationInfinity() {
		System.setProperty("webdriver.edge.driver", "C://Users//ShahrukhAata_l4//Project//Test//driver//edgedriver_win64//msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
		//driver.get(prop.getProperty("url"));
		//driver.get("https://myfunding.ncf-sandbox.com/");
	}
	
	
	public static void sendEmailWithSqData() {
		
		LocalDateTime localDate = LocalDateTime.now().minusDays(1);
		DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd MMM");

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
			// message.addRecipient(Message.RecipientType.TO,new
			// InternetAddress(to1));
			// message.addRecipient(Message.RecipientType.TO,new
			// InternetAddress(to2));
			// message.addRecipient(Message.RecipientType.CC,new
			// InternetAddress(cc));
			message.setSubject("Status - " + customFormat.format(localDate));
			//message.setContent(messageToSend, "text/html");
			// message.setText(messageToSend);
			message.setText("HI you have done sending mail with outlook");

			Transport.send(message);
			System.out.println("message sent successfully...");

		}
		catch(MessagingException e)
		{
			System.out.println("Exception occured is :" + e);
		}
	}

	 public static void switchToWindow(int index) {
		    Set<String> windows = driver.getWindowHandles();
		    int totalWin= windows.size();
		    String winTitle = null;
		    for(int i=0;i<totalWin;i++) {
		      if(i==index) {
		        winTitle = windows.toArray()[i].toString();
		    }
		    }
		    driver.switchTo().window(winTitle);
		    System.out.println(winTitle);
		}
	 
	// Set<String> handles = driver.getWindowHandles();
	//	List<String> hList = new ArrayList<String>(handles);
	 public static boolean switchToRightWindow(String windowTitle, List<String> hList) {
			
			for(String e : hList) {
				String title = driver.switchTo().window(e).getTitle();
				
				if(title.contains(windowTitle)) {
					System.out.println("found the right window...");
					return true;
				}	 
			 }
			return false;
		}
	 
	 
	
	 public String getScreenshot( WebDriver driver) throws IOException {
		 
		 TakesScreenshot ts = (TakesScreenshot)driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 File file = new File(System.getProperty(("user.dir") + "//reports//" + ".png"));
		 FileUtils.copyFile(source,file);
		 return System.getProperty(("user.dir") + "//reports//" + ".png");
	 }
	 
	 
		
	
	/*public static void createReports() {
		 ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extentReport.html");
			//create ExtentReports and attach reporter(s)
			  ExtentReports extent = new ExtentReports();
			  extent.attachReporter(htmlReporter);
			//creates a toggle for the given test, add all log events under it
			  ExtentTest test1 = extent.createTest("Test Case");
	}*/
	 
	 
	/* public static void GetScreenshot() throws IOException, HeadlessException, AWTException
		{
		 
		// File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 
		String Basepathsc = Basepathet;//globalIdentifiers.Basepath + "Reports and Screenshots";
		Calendar cal = Calendar.getInstance();
		File Dir = new File(Basepathsc);
		Dir.mkdir();
		int year = cal.get(Calendar.YEAR);
		Dir = new File(Basepathsc+"/"+year);
		Dir.mkdir();
		int month = cal.get(Calendar.MONTH);
		Dir = new File(Basepathsc+"/"+year+"/"+(month+1));
		Dir.mkdir();
		int day = cal.get(Calendar.DATE);
		Dir = new File(Basepathsc+"/"+year+"/"+(month+1)+"/"+day);
		Dir.mkdir();
		Dir = new File(Basepathsc+"/"+year+"/"+(month+1)+"/"+day);
		Dir.mkdir();
		Dir = new File(sReportPath+"/Screenshots and Statements");
		Dir.mkdir();
		Dir = new File(sReportPath+"/Screenshots and Statements"+"/");
		Dir.mkdir();
		SScreenshotdirpath = Dir.getAbsolutePath().replace("\\", "/");
		Date sDate = new Date();
		String sScreenshotFilename = sDate.getHours()+"_"+sDate.getMinutes()+"_"+sDate.getSeconds();
		sScreenshotFilePath = SScreenshotdirpath + "/" + sScreenshotFilename + ".png";
		TakesScreenshot scrShot =((TakesScreenshot)TestBase.driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(sScreenshotFilePath);
		//BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		//ImageIO.write(image, "png", new File(DestFile.getAbsolutePath()));
		//(SrcFile.getAbsolutePath(), DestFile);
		Files.copy(SrcFile.toPath(), DestFile.toPath());
		
		}*/
	 
	 
	 
	
	public static  String Screenshot() throws Exception {
		
		path = System.getProperty("user.dir") + "/Reports and Screenshots";
		 	//String path="C:\\Users\\ShahrukhAatar\\OneDrive - Nucleus Services Ltd\\Documents\\Automation\\MyNucleusTest\\Screenshot";
		//String path = Basepathet;//globalIdentifiers.Basepath + "Reports and Screenshots";
		Calendar cal = Calendar.getInstance();
		File Dir = new File(path);
		Dir.mkdir();
		int year = cal.get(Calendar.YEAR);
		Dir = new File(path+"/"+year);
		Dir.mkdir();
		int month = cal.get(Calendar.MONTH);
		Dir = new File(path+"/"+year+"/"+(month+1));
		Dir.mkdir();
		int day = cal.get(Calendar.DATE);
		Dir = new File(path+"/"+year+"/"+(month+1)+"/"+day);
		Dir.mkdir();
		Dir = new File(path+"/"+year+"/"+(month+1)+"/"+day);
		Dir.mkdir();
		SScreenshotdirpath = Dir.getAbsolutePath();
		Date sDate = new Date();
		String sScreenshotFilename = sDate.getHours()+"_"+sDate.getMinutes()+"_"+sDate.getSeconds();
		sScreenshotFilePath = SScreenshotdirpath + "/" + sScreenshotFilename + ".png";
		//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(sScreenshotFilePath);
		Files.copy(SrcFile.toPath(), DestFile.toPath());
		return (System.getProperty("user.dir") + "/Reports and Screenshots");	
	}
	
	
}
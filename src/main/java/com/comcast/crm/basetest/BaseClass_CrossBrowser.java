package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass_CrossBrowser {
	
	
	public WebDriver driver = null;    //( driver store session id of browser and is non static to perform parallel Execution)
	
	public static WebDriver sdriver = null;   //( here we made this static variable for Listener implmentation class)	
	
	
	/* Create Object */
	public FileUtility fLib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public DataBaseUtility dbLib = new DataBaseUtility();
	

	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS() throws Throwable {
		System.out.println("==Connect to DB, Report Config=====");
		
		dbLib.getDbconnection();
		
	}
	
	
    //for Cross Browser Testing we use @parameters and Receive Browser value from its Testing.XML file
	@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	 public void configBC(String browser) throws Throwable {
		System.out.println("=======Launch the Browser ========");
		
		String BROWSER=browser;
		 
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		sdriver = driver;     //(here we are storing driver browser session id to sdriver variable used in Listener Implementation Class)
	}
	
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("========Login============");
		
		String URL= fLib.getDataFromPropertiesFile("url");
		String USERNAME= fLib.getDataFromPropertiesFile("username");
		String PASSWORD= fLib.getDataFromPropertiesFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
		
		
		
	}
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM() throws Throwable {
		System.out.println("==========Logout=========");
		
		HomePage hp=  new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("============Close the Browser=============");
		
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS() throws Throwable {
		System.out.println("============Close the DB, Report Backup ==============");
		dbLib.closeDbconnection();
		
	}

 }

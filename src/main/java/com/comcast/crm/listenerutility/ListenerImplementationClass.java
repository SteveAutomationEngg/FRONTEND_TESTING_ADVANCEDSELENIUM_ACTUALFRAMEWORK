package com.comcast.crm.listenerutility;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplementationClass  implements ITestListener, ISuiteListener {

	public ExtentReports report;   //we made it public as used in testScript which is in other package and global as it is used in onStart() and onFinish()
                          
	public static ExtentTest test;   //global declare as use to in two diff method onTestStart() -->to create testcase in Extent report &  onTestFailure -->take screenshot
	                                 //static as it going to be used in test class where we cannot create object of this listImplenation class as method invoke automatically
	
	
	
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		
		 //Spark report Configuration
		                             /********capture timestamp and concatenate to file name**********/
		                              String time= new Date().toString().replace(" ","_").replace(":","_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);


		//add Environment information 
		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window-10");
		report.setSystemInfo("BROWSER", "Chrome-100");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report BackUp");
		
		//backup of report
		 report.flush(); 
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
	     System.out.println("==================>"+result.getMethod().getMethodName()+"<======START========");
		
	     // Create test
	     //this code will create test case in Extent report
		test= report.createTest(result.getMethod().getMethodName());
		
		UtilityClassObject.setTest(test);   // ***after creating test object in above line i have set the test object in UtilityClass Object class from where i am going to use that test object in my test script.Now, it will particiapte in parallel execution
		
		//To insert statment inside  Extent Report Before each Testcase Execute
		test.log(Status.INFO,result.getMethod().getMethodName()+"=========>STARTED<=============");
		
		
		
	} 

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("==================>"+result.getMethod().getMethodName()+"<======END========");
		
		//To insert statment inside Extent Report after succesful PASS
		test.log(Status.PASS,result.getMethod().getMethodName()+"=========>COMPLETED<=============");
		
	}

	
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		//capture the testcase method name
	String testName	=result.getMethod().getMethodName();
	
	//Take Screenshot
	TakesScreenshot eDriver = (TakesScreenshot) BaseClass.sdriver;    //sdriver is static as we cannot create object of base class as all @
	String filePath = eDriver.getScreenshotAs(OutputType.BASE64);  //extent report support BASE64 Format
	
	//capture timestamp
	String time= new Date().toString().replace(" ","_").replace(":","_");
	
	//To add the screenshot taken to Extent Report with name testname_timestamp
	test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);  
	
	
	//To insert statment inside Extent Report after FAIL
			test.log(Status.FAIL,result.getMethod().getMethodName()+"=========>FAILED<=============");
	
	/*
	         //write the selenium program to take Screenshot
	
			EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass.sdriver);

			File srcFile = edriver.getScreenshotAs(OutputType.FILE);
			String time= new Date().toString().replace(" ","_").replace(":","_");

			try {
			FileUtils.copyFile(srcFile, new File("./screenshot/"+testName+"+"+time+".png"));  //here we use varaible to get screenshot having test test name.
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			*/
	}

	            
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}

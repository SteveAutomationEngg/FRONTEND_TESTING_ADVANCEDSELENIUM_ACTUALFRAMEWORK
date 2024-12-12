package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
	
	/* ******************READ ONCE****************
	 * We made this class to create threads of those static Object which are not allowed in parallel execution 
	 */
	
	/*
	we have create this class to share two static object which are present in my frameworki.e one in base class --->public static WebDriver sdriver
	other one is in Listener implementation class i.e public static ExtentTest test
    both these not particpate in parallel distributes as well as parllel crossbrowsing testing
	   Here we are creating threads of these object
	   
	   
	   now this class both variable participate in parellel disturbted and parellel crossBrowsing execution
	   */
	
	
	//creating threads of static object 
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	
	
	//getter and setter for access the object
	public static ExtentTest getTest() {
		return test.get();
	}
	
	public static void setTest(ExtentTest actTest) {	
		test.set(actTest);
	}
	
	
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void setDriver(WebDriver actDriver) {
		driver.set(actDriver);
	}
	
	
	
	
	

}
 
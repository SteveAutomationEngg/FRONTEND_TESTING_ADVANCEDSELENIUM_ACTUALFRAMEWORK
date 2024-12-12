package com.comcast.crm.orgtest;

import java.io.FileInputStream;

import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustriesTest {
	
		public static void main(String[] args) throws  Throwable {
			


			/* Create Object */
			
			FileUtility fLib = new FileUtility();
			ExcelUtility elib = new ExcelUtility();
			JavaUtility jLib = new JavaUtility();
			
			
			
			// read common data from Properties file
			
			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String URL = fLib.getDataFromPropertiesFile("url");
			String USERNAME = fLib.getDataFromPropertiesFile("username");
			String PASSWORD = fLib.getDataFromPropertiesFile("password");
			
		      
		      
		      //read test script data from Excel file
				
				String orgName = elib.getDataFromExcel("org", 4, 2)+ jLib.getRandomNumber();
				String industry = elib.getDataFromExcel("org", 4, 3);
				String  type = elib.getDataFromExcel("org", 4, 4);
				
		     
			WebDriver driver =null;
			
			if(BROWSER.equals("chrome")) {
				driver = new ChromeDriver();
			} else if(BROWSER.equals("firefox")) {
				driver = new FirefoxDriver();
			}else if (BROWSER.equals("edge"))  {
				driver = new EdgeDriver();
				} else {
					driver = new ChromeDriver();
				}
			
			//step1: login to app
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(URL);
			
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			
			//step2: navigate to organization module
			driver.findElement(By.linkText("Organizations")).click();
			
			//step3:click on "create Organization" button
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			
			//step4:enter all the details & create new organization
			
			driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
			
			WebElement industryDropDown = driver.findElement(By.xpath("//select[@name='industry']"));
			Select sel = new Select(industryDropDown);
			sel.selectByValue(industry);
			
			WebElement typeDropDown = driver.findElement(By.xpath("//select[@name='accounttype']"));
			Select sel1 = new Select(typeDropDown);
			sel1.selectByValue(type);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			
			
			
			//Verify the industries and type info from Expected Result  
			
			
			
		String actIndustries= driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustries.contains(industry)) {
			System.out.println(industry +"\t" + "Information is verfied == PASS ");
		}else {
			System.out.println(industry +"\t"+ "Information is not verified == FAIL");
		}
		
		
		String actType= driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.contains(type)) {
			System.out.println(type +"\t" + "Information is verfied == PASS ");
		}else {
			System.out.println(type +"\t" + "Information is not verified == FAIL");
		}

		
		
			Thread.sleep(5000);			 
			//step5:Logout
			Actions act = new Actions(driver);  
			act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
			driver.findElement(By.linkText("Sign Out")).click();
			driver.quit();
			
			

	}

		
	}



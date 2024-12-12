package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws Throwable {

		/* Create Object */

		FileUtility fLib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		// read common data from Properties file

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// java program to fetch the system date and add 30 date to that

		String startDate = jLib.getSystemDateYYYYMMDD();
		System.out.println("Support start Date : " + startDate);

		// here we are adding 30 days

		String endDate = jLib.getRequiredDateYYYYMMDD(30);
		System.out.println("Support End Date : " + endDate);

		// read test script data from Excel file

		String lastName = elib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		// step1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step2: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		// step3:click on "create contact" button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step4:enter all the details & create new contact
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);

		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify Header msg from Expected Result
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();

		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + "\t" + "information is verified == PASS ");
		} else {
			System.out.println(startDate + "\t" + " information is not verfied == FAIL");
		}

		if (actEndDate.equals(endDate)) {
			System.out.println(endDate + "\t" + "information is verified == PASS ");
		} else {
			System.out.println(endDate + "\t" + " information is not verfied == FAIL");
		}

		Thread.sleep(5000);
		// step5:Logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}
}
 
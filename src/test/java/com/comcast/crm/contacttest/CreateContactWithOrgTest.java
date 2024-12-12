package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest {
	public static void main(String[] args) throws Throwable {

		// In this Script we create one organization and then create Contact by using
		// same org name

		/* Create Object */

		FileUtility fLib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		// read common data from Properties file

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// read test script data from Excel file
		String orgName = elib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

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
		wLib.waitForPageToLoad(driver); // implict wait
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step2: navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();

		// step3:click on "create Organization" button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// step4:enter all the details & create new organization
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify Header msg from Expected Result
		String orgHeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgHeaderInfo.contains(orgName)) {
			System.out.println(orgName + "\t" + "Org header verified == PASS ");
		} else {
			System.out.println(orgName + "\t" + " Org header is not verfied == FAIL");
		}

		// read test script data from Excel file

		String lastName = elib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step2: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		// step3:click on "create contact" button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step4:enter all the details & create new contact
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

		driver.findElement(By
				.xpath("//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']"))
				.click();

		// switch To Child Window
		wLib.switchToTabOnUrl(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// Switch to parent Window
		wLib.switchToTabOnUrl(driver, "module=Contacts&action");
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify contact info Expected Result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();

		if (actLastName.equals(lastName)) {
			System.out.println(lastName + "\t" + "information is verified == PASS ");
		} else {
			System.out.println(lastName + "\t" + " information is not verfied == FAIL");
		}

		// verify org name info Expected result
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgName);

		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "\t" + "information is verified == PASS ");
		} else {
			System.out.println(orgName + "\t" + " information is not verfied == FAIL");
		}

		// Verify Header Contact info expected Result
		String contactHeaderInfo = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();

		if (contactHeaderInfo.contains(lastName)) {
			System.out.println(lastName + "\t" + "information is verified == PASS ");
		} else {
			System.out.println(lastName + "\t" + " information is not verfied == FAIL");
		}

		driver.quit();

	}

}

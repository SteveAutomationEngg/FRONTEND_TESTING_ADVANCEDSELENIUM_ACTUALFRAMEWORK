package com.comcast.crm.orgtest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrganizationTest {

	public static void main(String[] args) throws Throwable {

		
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
		String orgName = elib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();

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

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		// step2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
	

		// step3:click on "create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateBtn().click();

		// step4:enter all the details & create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		
		cnop.createOrg(orgName);

		// Verify Header msg from Expected Result           [here we are verifying as per manual
		// test case we are getting i.e [ ACC42 ] facebook123333444 - Organization
		// Information which contains Org name]
		
		
        OrganizationInfoPage oip = new OrganizationInfoPage(driver);
        
		String headerInfo =oip.getHeaderMsg().getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "header verified == PASS ");
		} else {
			System.out.println(orgName + " header is not verfied == FAIL");
		}

		// Verify Header orgname info Expected Result                [Here we are verifing jo maine
		// Oganization creation time paar name daala matches final jo aaya after
		// organization creation]

		String actOrgName =oip.getactOrgNameEdt().getText();

		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + "is created == PASS ");
		} else {
			System.out.println(orgName + " is not created == FAIL");
		}

		System.out.println("orgName" + "\t" + orgName);
		System.out.println("actOrgName" + "\t" + actOrgName);

		
		
		  // go back to organization page 
		   hp.getOrgLink().click();
		  
		  //search for organization  
		   op.getSearchEdt().sendKeys(orgName);
		  
		  wLib.select(op.getInDB(), "Organization Name");
		  
		  op.getSearchBtn().click();
		  
		  //in Dyanamic Webtable select and delete org  
		  driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		  
		  wLib.switchToAlertAndAccept(driver);
		
		
		
		Thread.sleep(5000);
		
		//step5:Logout 
		hp.logout();
		
		driver.quit();

	}

}

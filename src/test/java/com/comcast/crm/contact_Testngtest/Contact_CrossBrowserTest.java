 package com.comcast.crm.contact_Testngtest;

import org.openqa.selenium.By;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.basetest.BaseClass_CrossBrowser;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


public class Contact_CrossBrowserTest extends BaseClass_CrossBrowser{
	
	@Test(groups = "smokeTest" )
	public void createContactTest()throws Throwable {

		// read test script data from Excel file
		String lastName = elib.getDataFromExcel("contact",1,2) + jLib.getRandomNumber();


		// step2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// step3:click on "create contact" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();

		// step4:enter all the details & create new contact
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(lastName);
		
		// Verify Header msg from Expected Result
         String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(headerInfo.contains(lastName)) {
			System.out.println(lastName + "\t" + " header verified == PASS ");
		} else {
			System.out.println(lastName + "\t" + " header is not verfied == FAIL");
		}
		
		
		
		// Verify Header Phone Number info Expected Result 
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();

		if (actLastName.contains(lastName)) {
			System.out.println(lastName + "\t" + "information is verified == PASS ");
		} else {
			System.out.println(lastName + "\t" + " information is not verfied == FAIL");
		}

	}
	
	
	@Test(groups = "regressionTest")
	public void createcontactWithOrgTest() throws Throwable {

		// In this Script we create one organization and then create Contact by using
		// same org name
		
		// read test script data from Excel file
		String orgName = elib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		
		

		// step2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3:click on "create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateBtn().click();

		// step4:enter all the details & create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
	
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

	}
	
@Test(groups ="regressionTest")
	
	public void  createContactWithSupportDateTest() throws Throwable {

		// read test script data from Excel file

		String lastName = elib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
		String startDate=jLib.getSystemDateYYYYMMDD();
		String endDate=jLib.getRequiredDateYYYYMMDD(30);
		
		
		// step2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();


		// step3:click on "create contact" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();

		// step4:enter all the details & create new contact
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContactWithSupportDate(lastName, startDate, endDate);

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

	}

	
	
}


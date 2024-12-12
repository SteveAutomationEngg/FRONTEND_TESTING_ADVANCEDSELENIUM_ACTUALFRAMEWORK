package com.comcast.crm.org_Testngtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerutility.ListenerImplementationClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;

import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


@Listeners(com.comcast.crm.listenerutility.ListenerImplementationClass.class)   //i have use @listeners and pass fully qualified listenerImplementation class ** REMEMEBER: IF U USE @LISTENTER DONT USE LISTERNER TAG AT TESTNG.XML IT WILL CONFLICT**
public class OrganizationTest extends BaseClass{

	    
	@Test(groups =  "smokeTest")
	public void createOrganizationTest() throws Throwable {

		/*                  ****************READ ONCE **********************
		 * 
		 *  I have replace ** UtilityClassObject.getTest().log **  which i earlier use there is no issue but it will
		 * not participate in parellel execution as this test object is static came from Listener class 
		 * 
		 *  so made change in place of **ListenerImplementationClass.test.log ** use this  ** UtilityClassObject.getTest.log ** to allow this log to 
		 *  participate in Parellel exection also 
		 */
	
		
		// read test script data from Excel file
	     UtilityClassObject.getTest().log(Status.INFO,"Read Data from Excel");  //This test.log(....) insert log from testScript to Extent Report
		String orgName = elib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// step2: navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3:click on "create Organization" button
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Create Org Page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateBtn().click();

		
		// step4:enter all the details & create new organization
		UtilityClassObject.getTest().log(Status.INFO,"Create a New Org");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);

		cnop.createOrg(orgName);

		UtilityClassObject.getTest().log(Status.INFO,orgName + "=============> Create a new Org");
		
		// Verify Header msg from Expected Result
		
		/*
		 * [here we are verifying as per manual test case we are getting i.e [ ACC42 ]
		 * facebook123333444 - Organization Information which contains Org name]
		 */

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String headerInfo = oip.getHeaderMsg().getText();
		
		/* 
		 if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "header verified == PASS ");
		} else {
			System.out.println(orgName + " header is not verfied == FAIL");
		}
		*/
		boolean status=headerInfo.contains(orgName);
		Assert.assertTrue(status);
		

		// Verify Header orgname info Expected Result

		String actOrgName = oip.getactOrgNameEdt().getText();

		/*
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + "is created == PASS ");
		} else {
			System.out.println(orgName + " is not created == FAIL");
		}
        */
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actOrgName, orgName);
		
		
		
		// System.out.println("orgName" + "\t" + orgName);
		// System.out.println("actOrgName" + "\t" + actOrgName);

	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithIndustriesTest() throws Throwable {

		// read test script data from Excel file

		String orgName = elib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);

		// step2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3:click on "create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateBtn().click();

		// step4:enter all the details & create new organization

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type);

		// Verify the industries and type info from Expected Result
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustries.contains(industry)) {
			System.out.println(industry + "\t" + "Information is verfied == PASS ");
		} else {
			System.out.println(industry + "\t" + "Information is not verified == FAIL");
		}

		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.contains(type)) {
			System.out.println(type + "\t" + "Information is verfied == PASS ");
		} else {
			System.out.println(type + "\t" + "Information is not verified == FAIL");
		}

	}

	@Test(groups ="regressionTest")
	public void createOrganizationWithPhoneNumberTest() throws Throwable {

		// read test script data from Excel file

		String orgName = elib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = elib.getDataFromExcel("org", 7, 3);

		// step2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3:click on "create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateBtn().click();

		// step4:enter all the details & create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg1(orgName, phoneNumber);

		// Verify Header msg from Expected Result

		String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();

		if (actPhoneNumber.contains(phoneNumber)) {
			System.out.println(phoneNumber + "\t" + "information is verified == PASS ");
		} else {
			System.out.println(phoneNumber + "\t" + " information is not verfied == FAIL");
		}

	}
	
	@Test(groups = "regressionTest" ,enabled = false)
	public void deleteOrganizationTest() throws Throwable {


		// read test script data from Excel file
		String orgName = elib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();

		// step2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
	

		// step3:click on "create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateBtn().click();

		// step4:enter all the details & create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);


		  // go back to organization page 
		   Thread.sleep(2000);
		   hp.getOrgLink().click();
		  
		  //search for organization  
		   op.getSearchEdt().sendKeys(orgName);
		  
		  wLib.select(op.getInDB(), "Organization Name");
		  
		  op.getSearchBtn().click();
		  
		  //in Dyanamic Webtable select and delete org 
		  
		  driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		  
		  wLib.switchToAlertAndAccept(driver);
		
		//verify
		  
		  boolean actsearch=op.getNoorgtext().isDisplayed();
			SoftAssert sa=new SoftAssert();
			sa.assertTrue(actsearch);
			sa.assertAll();

	}


}

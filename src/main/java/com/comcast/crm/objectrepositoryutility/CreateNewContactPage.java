package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewContactPage {

WebDriver driver;
	
	WebDriverUtility wdlib=new WebDriverUtility();
	
	public CreateNewContactPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement editlastname;
	
	@FindBy(xpath="//img[@alt='Select']")
	private WebElement orgbtn;
	
	@FindBy(name="search_text")
	private WebElement editsearchtext;
	
	@FindBy(name="search_field")
	private WebElement searchdd;
	
	@FindBy(name="search")
	private WebElement searchbtn;
	
	@FindBy(name="support_start_date")
	private WebElement editstartdate;
	
	@FindBy(name="support_end_date")
	private WebElement editenddate;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	
	public WebElement getEditlastname() {
		return editlastname;
	}
	
	

	public WebElement getOrgbtn() {
		return orgbtn;
	}



	public WebElement getEditsearchtext() {
		return editsearchtext;
	}



	public WebElement getSerachdd() {
		return searchdd;
	}



	public WebElement getSerachbtn() {
		return searchbtn;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	
	
	public WebElement getEditstartdate() {
		return editstartdate;
	}

	public WebElement getEditenddate() {
		return editenddate;
	}

	public void createNewContact(String lastname) {
		editlastname.sendKeys(lastname);
		savebtn.click();
	}
	
	public void createNewContactWithSupportDate(String lastname,String startdate,String enddate) {
		editlastname.sendKeys(lastname);
		editstartdate.clear();
		editstartdate.sendKeys(startdate);
		editenddate.clear();
		editenddate.sendKeys(enddate);
		savebtn.click();
	}
	
	public void createNewContactWithOrg(String lastname,String orgname) {
		editlastname.sendKeys(lastname);
		orgbtn.click();
		wdlib.switchToTabOnUrl(driver,"module=Accounts&action");
		editsearchtext.sendKeys(orgname);
		wdlib.select(searchdd, "Organization Name");
		searchbtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		wdlib.switchToTabOnTitle(driver,"Contacts");
		savebtn.click();
	}
	
	

	}


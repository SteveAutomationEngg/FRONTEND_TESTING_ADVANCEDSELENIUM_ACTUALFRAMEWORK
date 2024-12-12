package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
	
	WebDriver driver;
	
	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industryDB;	
	
	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement typeDB;
	
	@FindBy(id = "phone")
	private WebElement phoneEdt;

	

	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getIndustryDB() {
		return industryDB;
	}

	public WebElement getTypeDB() {
		return typeDB;
	}
	
	
	
	public WebElement getPhoneEdt() {
		return phoneEdt;
	}

	
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String industry) {
		orgNameEdt.sendKeys(orgName);
		
		Select sel = new Select(industryDB);
		sel.selectByVisibleText(industry);
		
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String industry, String type) {
		orgNameEdt.sendKeys(orgName);
		
		Select sel = new Select(industryDB);
		sel.selectByVisibleText(industry);
		
		Select sel1 = new Select(typeDB);
		sel1.selectByValue(type);
		saveBtn.click();
	}

	public void createOrg1(String orgName, String phoneNumber) {
		
		orgNameEdt.sendKeys(orgName);
		phoneEdt.sendKeys(phoneNumber);
		
		saveBtn.click();
	}
}

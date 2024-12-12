package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	WebDriver driver;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement actOrgNameEdt;
	
	public OrganizationInfoPage (WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getactOrgNameEdt() {
		return actOrgNameEdt;
	}

	
}

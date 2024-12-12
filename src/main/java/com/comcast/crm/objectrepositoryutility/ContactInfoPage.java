package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	WebDriver driver;
	
	public ContactInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerinfo;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement editlastname;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement editstartdate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement editenddate;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement editorgname;
	
	

	public WebElement getEditlastname() {
		return editlastname;
	}

	public WebElement getEditorgname() {
		return editorgname;
	}

	public WebElement getEditstartdate() {
		return editstartdate;
	}

	public WebElement getEditenddate() {
		return editenddate;
	}

	public WebElement getHeaderinfo() {
		return headerinfo;
	}

}

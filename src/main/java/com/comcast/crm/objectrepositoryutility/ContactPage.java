package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	
	WebDriver driver;
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContactBtn;

	public WebElement getCreateContactBtn() {
		return createContactBtn;
	}
	
	public ContactPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}


}

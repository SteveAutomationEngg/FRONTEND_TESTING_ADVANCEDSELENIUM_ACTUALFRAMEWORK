package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignLink;
	

	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(linkText = "Products")                        //Automation Lead added it for GitHub
	private WebElement productsLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	public HomePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}


	public WebElement getOrgLink() {
		return orgLink;
	}


	public WebElement getContactsLink() {
		return contactsLink;
	}


	public WebElement getCampaignLink() {
		return campaignLink;
	}
	
	
	
	public WebElement getMoreLink() {
		return moreLink;
	}
	
	public WebElement getAdminImg() {
		return adminImg;
	}


	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	
	public WebElement getProductsLink() {
		return productsLink;
	}


	//business logic
	public void navigateToCampaignPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		
		campaignLink.click();	
	}
	
	public void logout() throws Throwable {
		Actions act = new Actions(driver);
		act.moveToElement(adminImg).perform();
		Thread.sleep(5000);
		signOutLink.click();
	}
	
	
	
}
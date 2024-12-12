package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {

	 WebDriver driver;
	 
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createBtn;

	@FindBy(xpath = "//input[@class='txtBox']")
	private WebElement searchEdt;
	
	@FindBy(id ="bas_searchfield")
	private WebElement inDB;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement searchBtn;
	
	@FindBy(xpath="//span[contains(text(),'No Organization Found !')]")
	private WebElement noorgtext;
	
	
	

	public OrganizationsPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getCreateBtn() {
		return createBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getInDB() {
		return inDB;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	public WebElement getNoorgtext() {
		return noorgtext;
	}
	
	
	
	
}
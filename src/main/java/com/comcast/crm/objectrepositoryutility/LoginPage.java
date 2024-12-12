package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.mysql.cj.jdbc.Driver;

/**
 * @author MOHIT KUMAR
 * 
 * This Class Contains Login Page elements and Bussiness library like loginToApp()
 */

public class LoginPage extends WebDriverUtility {
	
	WebDriver driver;
	
	//Rule1: Create a separate java class
	
	//Rule2: Object identification
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;

	
	
	//rule3:Object Initilization
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//rule4: Object Encapsulation
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	
	//Rule5: Object Utilization
	
	
	//business logic
	/**
	 * login to application based on username , password arguments
	 * @param username
	 * @param password
	 */
	public void loginToApp(String username, String password) {
		driver.manage().window().maximize();
		getUsernameEdt().sendKeys(username);
		getPasswordEdt().sendKeys(password);
		getLoginButton().click();
	}
	
	     //I have overload the loginToApp()
	/**
	 * login to application based on username , password , url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url, String username, String password) {
		
		//implicit wait (In This POM class we extend Webdriver utility to use this method)
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		getUsernameEdt().sendKeys(username);
		getPasswordEdt().sendKeys(password);
		getLoginButton().click();
	}
}

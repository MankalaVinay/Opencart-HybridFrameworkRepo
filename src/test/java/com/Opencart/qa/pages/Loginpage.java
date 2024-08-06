package com.Opencart.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	@FindBy(id="input-password")
	private WebElement passwordField;
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton; 
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	 private WebElement emailPasswordNotMatchingWarningMsg;
	
public Loginpage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
	public void enterEmailAdress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPasswordText(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public void clickOnLogin() {
		loginButton.click();
	}
	public String retrieveEmailPasswordNotMatchingMessageText() {
		String warningText=emailPasswordNotMatchingWarningMsg.getText();
		return warningText;
	}
	
	
}


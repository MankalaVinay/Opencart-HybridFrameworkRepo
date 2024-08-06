package com.Opencart.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	WebDriver driver;
//	@FindBy(xpath="//div[@id='content']/h1")
	//private WebElement accountSuccessPageHeading;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	@CacheLookup 
	private WebElement accountSuccessPageHeading;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public String retrieveAccountSuccessPageHeading() {
		String accountSuccessPageHeadingTxt=accountSuccessPageHeading.getText();
		return accountSuccessPageHeadingTxt;
	}

}

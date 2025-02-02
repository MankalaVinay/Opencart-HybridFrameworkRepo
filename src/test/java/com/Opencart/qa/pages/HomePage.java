package com.Opencart.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
    //objects
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement myAccountDropMenu;
	@FindBy(linkText="Login")
	private WebElement loginOption;
	@FindBy(linkText="Register")
	 private WebElement registerOption;
	@FindBy(name="search")
	private WebElement searchOption;
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement SearchOptionButton;
	public void enterProductIntoSearchBoxField(String productTxt) {
		searchOption.sendKeys(productTxt);
	}
	public void searchButton() {
		SearchOptionButton.click();
	}
	

	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Actions
	public void clickOnMyAccount() {
		 myAccountDropMenu.click();
	}
	public void selectLoginOption() {
		loginOption.click();
	}
	public void selectRegisterOption() {
		registerOption.click();
	}

}

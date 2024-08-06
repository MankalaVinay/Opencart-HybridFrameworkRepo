package com.Opencart.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	@FindBy(name="firstname")
	private WebElement firstNameFiled;
	@FindBy(name="lastname")
	private WebElement lastNameField;
	@FindBy(name="email")
	private WebElement emailTextAdressField;
	@FindBy(name="telephone")
	private WebElement telephoneTextAdressField;
	@FindBy(id="input-password")
	private WebElement passwordTextField;
	@FindBy(id="input-confirm")
	private WebElement PasswordConfirmText;
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement contineButton;
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement duplicateEmailAddressWarningMsg;
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	@FindBy(xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarning;
	@FindBy(xpath="//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarning;
	@FindBy(xpath="//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailNameWarning;
	@FindBy(xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarning;
	@FindBy(xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWaring;
	public String retrievePasswordWarningTxt() {
		String passwordWarningTxt=passwordWaring.getText();
		return passwordWarningTxt;
		
	}
	
	public String retrieveTelephoneWarningTxt() {
		String telephoneWarningTxt=telephoneWarning.getText();
		return telephoneWarningTxt;
	}
	
	
	public String retrieveLastNameTxt() {
	String lastNameTxtWarning=	lastNameWarning.getText();
	return lastNameTxtWarning;
	}
	public String retrieveEmailTxtWarning() {
		String emailWarningTxt=emailNameWarning.getText();
		return emailWarningTxt;
	}

	public void enterFirstName(String firstNameText) {
		firstNameFiled.sendKeys(firstNameText);
	}
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	public void enterEmailAddress(String emailText) {
		emailTextAdressField.sendKeys(emailText);
	}
	public void enterTelephoneAddress(String TelephoneTxt) {
		telephoneTextAdressField.sendKeys(TelephoneTxt);
	}
	public void enterPasswordTxt(String passwordTxt) {
		passwordTextField.sendKeys(passwordTxt);
	}
	public void enterConfirmPasswordField(String confirmPasswordTxt) {
		PasswordConfirmText.sendKeys(confirmPasswordTxt);
	}
	public void clickPrivacyPolicyFiled() {
		privacyPolicyField.click();

	}
	public void clickContinueButton() {
		contineButton.click();
	}
	public void selectYesNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningTxt=duplicateEmailAddressWarningMsg.getText();
		return duplicateEmailWarningTxt;
	}
	public String retrievePrivacyPolicyWarnig() {
		String privacyPolicyWarningTxt= privacyPolicyWarning.getText();
		return privacyPolicyWarningTxt;
	}
	public String retrieveFirstNameWarning() {
		String firstNameWarningTxt=firstNameWarning.getText();
		return firstNameWarningTxt;
	}

}

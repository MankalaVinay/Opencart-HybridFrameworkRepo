package com.Opencart.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Opencart.qa.Utilities.Utilities;
import com.Opencart.qa.base.BaseClass;
import com.Opencart.qa.pages.AccountSuccessPage;
import com.Opencart.qa.pages.HomePage;
import com.Opencart.qa.pages.RegisterPage;


public class RegisterTest extends BaseClass{
public WebDriver driver;
	public RegisterTest() {
		super();
	}
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browser"));
		HomePage homePage= new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectRegisterOption();

	}
	@AfterMethod
	public void tearDown() {
		driver.quit();	
	}
	@Test(priority=1)
	public void VerifyRegisteringanAccountbyprovidingonlyMandatoryFields()
	{
		RegisterPage registerPage= new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateTimestamp());
		registerPage.enterTelephoneAddress(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPasswordTxt(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerPage.clickPrivacyPolicyFiled();
		registerPage.clickContinueButton();
		
	
		AccountSuccessPage accountSuccessPage=new AccountSuccessPage(driver);
		String actualRegistrationsuccessHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();
		

	
		Assert.assertEquals(actualRegistrationsuccessHeading,dataprop.getProperty("registeredAccountSuccessfullyCreated"),"Registration Account not Created");


	}
	@Test(priority=2)
	public void verifyRegistrationAccountByprovidingAllFields()
	{
		RegisterPage registerPage= new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateTimestamp());
		registerPage.enterTelephoneAddress(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPasswordTxt(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.clickPrivacyPolicyFiled();
		registerPage.clickContinueButton();
		AccountSuccessPage accountSuccessPage=new AccountSuccessPage(driver);
		String actualRegistrationsuccessHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();

		Assert.assertEquals(actualRegistrationsuccessHeading,dataprop.getProperty("registeredAccountSuccessfullyCreated"),"Registration Account not Created");

	}
	@Test(priority=3)
	public void verifyingRegisterAccountWithExistingEmailAddress() {
		RegisterPage registerPage= new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneAddress(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPasswordTxt(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.clickPrivacyPolicyFiled();
		registerPage.clickContinueButton();
		String actualWarningMsg =registerPage.retrieveDuplicateEmailAddressWarning();

		
	
		Assert.assertTrue(actualWarningMsg.contains(dataprop.getProperty("duplicateEmailWarning")),"Warning: E-Mail Address is already registered! is not displayed");

	}
	@Test(priority=4)
	public void verifyingRegisteringAccountwithoutFilligAnyDetails() {

		RegisterPage registerPage= new RegisterPage(driver);
		registerPage.clickContinueButton();
		
		
		String actualprivacyWarningMsg= registerPage.retrievePrivacyPolicyWarnig();
		Assert.assertTrue(actualprivacyWarningMsg.contains(dataprop.getProperty("privacyPolicywarning")),"Warning: You must agree to the Privacy Policy! is not displayed");
		
		
		String actualFirstnameWarningMsg=registerPage.retrieveFirstNameWarning();
		Assert.assertTrue(actualFirstnameWarningMsg.contains(dataprop.getProperty("firstNameWarning")),"First Name must be between 1 and 32 characters! is not displayed");
		String actualLastnameWarningMsg=registerPage.retrieveLastNameTxt();
		Assert.assertTrue(actualLastnameWarningMsg.contains(dataprop.getProperty("lastNameWarning")),"Last Name must be between 1 and 32 characters! is not displayed");

		String actualEmailnameWarningMsg =registerPage.retrieveEmailTxtWarning();
		Assert.assertTrue(actualEmailnameWarningMsg.contains(dataprop.getProperty("EmailnameWarningMsg"))," "
				+ "E-Mail Address does not appear to be valid! is not displayed");


		String actualTelephonenameWarningMsg =registerPage.retrieveTelephoneWarningTxt();
		Assert.assertTrue(actualTelephonenameWarningMsg.contains(dataprop.getProperty("TelephonenameWarningMsg")),"Telephone must be between 3 and 32 characters! is not displayed");

		String actualPasswordnameWarningMsg =registerPage.retrievePasswordWarningTxt();
		Assert.assertTrue(actualPasswordnameWarningMsg.contains(dataprop.getProperty("PasswordnameWarningMsg")),"Password must be between 4 and 20 characters! is not displayed");



	}


}

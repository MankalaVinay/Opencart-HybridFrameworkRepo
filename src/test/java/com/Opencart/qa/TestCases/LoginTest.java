package com.Opencart.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Opencart.qa.Utilities.Utilities;
import com.Opencart.qa.base.BaseClass;
import com.Opencart.qa.pages.AccountPage;
import com.Opencart.qa.pages.HomePage;
import com.Opencart.qa.pages.Loginpage;

public class LoginTest extends BaseClass{
	public WebDriver driver; 
	public LoginTest() {
		super();
	}
	@BeforeMethod
	public void setup() {
		//loadPropertiesFile();
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browser"));
		HomePage homePage= new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyingLoginWithValidCredentials(String email, String password) {

		Loginpage loginPage=new Loginpage(driver);
		loginPage.enterEmailAdress(email);
		loginPage.enterPasswordText(password);
		loginPage.clickOnLogin();
		AccountPage accountPage = new AccountPage(driver);

		Assert.assertTrue(accountPage.getDisplayStatuSOfAccountInformaionOption(),"Edit your account information option is not displayed");



	}
	@DataProvider(name="validCredentialsSupplier")
	public Object[][]  supplyTestData() {
		Object[][] data= Utilities.getTestDataFromExcel("LoginData");
		return data;
	}
	@Test(priority=2)
	public void verifyLoginwithInvalidCredentials() {
		Loginpage loginPage=new Loginpage(driver);
		loginPage.enterEmailAdress(Utilities.generateTimestamp());
		loginPage.enterPasswordText(dataprop.getProperty("invalidPassword"));
		loginPage.clickOnLogin();
		String actualWarningMessage	= loginPage.retrieveEmailPasswordNotMatchingMessageText();
		String expectedWarningMsg= dataprop.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMsg), "Expected warning msg not displayed");

	}
	@Test(priority=3)
	public void verifyinvalidEmailAddressAndValidPassword() {
		Loginpage loginPage= new Loginpage(driver);
		loginPage.enterEmailAdress(Utilities.generateTimestamp());
		loginPage.enterPasswordText(prop.getProperty("validPassword"));
		loginPage.clickOnLogin();

		String actualWarningMessage	= loginPage.retrieveEmailPasswordNotMatchingMessageText();
		String expectedWarningMsg= dataprop.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMsg), "Expected warning msg not displayed");




	}
	@Test(priority=4)
	public void verifyValidUsernameAndInValidPassword() {
		Loginpage loginPage= new Loginpage(driver);
		loginPage.enterEmailAdress(prop.getProperty("validEmail"));
		loginPage.enterPasswordText(dataprop.getProperty("invalidPassword"));
		loginPage.clickOnLogin();
		String actualWarningMessage	= loginPage.retrieveEmailPasswordNotMatchingMessageText();
		String expectedWarningMsg= dataprop.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMsg), "Expected warning msg not displayed");


	}
	@Test(priority=5)
	public void verifyWithoutProvidingAnyCredentials() {
		Loginpage loginPage= new Loginpage(driver);
		loginPage.clickOnLogin();

		String actualWarningMessage	= loginPage.retrieveEmailPasswordNotMatchingMessageText();
		String expectedWarningMsg= dataprop.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMsg), "Expected warning msg not displayed");



	}




}

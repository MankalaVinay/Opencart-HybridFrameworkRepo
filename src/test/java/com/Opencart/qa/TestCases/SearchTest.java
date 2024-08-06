package com.Opencart.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Opencart.qa.base.BaseClass;
import com.Opencart.qa.pages.HomePage;
import com.Opencart.qa.pages.SearchPage;

public class SearchTest extends BaseClass {
public	WebDriver driver;
	public SearchTest() {
		super();
	}
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browser"));

	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=1)
	public void verifySearchWIthValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataprop.getProperty("validProduct"));
		homePage.searchButton();

		SearchPage searchPage=new SearchPage(driver); 



		////div[@id='search']/descendant::button
		Assert.assertTrue(searchPage.displayStatusOfHPProduct(),"Not displayed search results");

	}
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataprop.getProperty("inValidProduct"));

		homePage.searchButton();
		SearchPage searchPage= new SearchPage(driver);
		String actualsearchMsg=	searchPage.retrieveNOProductMsgTxt();

		Assert.assertEquals(actualsearchMsg,dataprop.getProperty("noProductMatched"),"There is no product that matches the search criteria. is not displayed");
	}
	@Test(priority=3,dependsOnMethods= {"verifySearchWithInvalidProduct","verifySearchWIthValidProduct"})
	public void VerifySearchingWithoutProvidingAnyProductName() {
		HomePage homePage= new HomePage(driver); 
		homePage.searchButton();
		//driver.findElement(By.name("search")).sendKeys("");
		SearchPage searchPage= new SearchPage(driver);

		String actualsearchMsg=	searchPage.retrieveNOProductMsgTxt();
		Assert.assertEquals(actualsearchMsg,dataprop.getProperty("noProductMatched"),"There is no product that matches the search criteria. is not displayed");

	}
}

package com.Opencart.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.Opencart.qa.Utilities.Utilities;

public class BaseClass {
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	public BaseClass() { //constructor 
	//public void loadPropertiesFile() {  //method
		prop =new Properties();
		File file= new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\opencart\\qa\\config\\Config.Properties");
	
		dataprop=new Properties();
		
	File file1= new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\opencart\\qa\\TestData\\Testdata.Properties");
	try {	
	FileInputStream fis1= new FileInputStream(file1);
		
			dataprop.load(fis1);
		} 
	catch (IOException e) {
			
			e.printStackTrace();
		};
		try {
		FileInputStream fis= new FileInputStream(file);
		
			prop.load(fis);
		}
		 catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeBrowserAndOpenApplicationUrl(String browser){
		
		if(browser.equalsIgnoreCase("chrome")) {
		 driver= new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver= new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("safari")) {
			driver= new SafariDriver();
		}
		
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_WAIT_TIME));
			driver.get(prop.getProperty("url"));
		return driver;
	}

}

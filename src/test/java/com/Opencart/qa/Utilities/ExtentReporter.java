package com.Opencart.qa.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinjaTestAutomation Result report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		Properties configProp=new Properties();
	try {	
	File configFile=new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\opencart\\qa\\Config\\Config.Properties");
	
	FileInputStream configFis=new FileInputStream(configFile);
		
			configProp.load(configFis);
		} 
	catch (IOException e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("BrowserName", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
	extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
	extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
	extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
	extentReport.setSystemInfo("java Version", System.getProperty("java.version"));
	//System.getProprties(System.Out) in sysout to get all propeties of system.
	
	
	return extentReport;
	}

}

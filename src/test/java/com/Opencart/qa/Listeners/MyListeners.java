package com.Opencart.qa.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Opencart.qa.Utilities.ExtentReporter;
import com.Opencart.qa.Utilities.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		
	 extentReport=ExtentReporter.generateExtentReport();
	
		
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		String testName=result.getName();
	   extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+"started executing");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.PASS, testName+"got Successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName();
		WebDriver driver=null;
		
		
	try {
		driver=	(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		
		e.printStackTrace();
	}
	
	String destinationScreenshotPath = null;
	try {
		destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+"got failed");
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String testName=result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+"got Sktpped");
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	

}

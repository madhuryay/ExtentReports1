package com.training.Demo_ExtentReports;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceLogin {
	
	static WebDriver driver;
	static ExtentReports extent;
	static ExtentHtmlReporter report;
	String dateformat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	
	public String takescreenshot() throws IOException
	{
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		String dstpath = System.getProperty("user.dir")+"//Reports//Screenshots"+dateformat+"_SFDC.PNG";
		File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
		File dstfile = new File(dstpath);
		FileUtils.copyFile(srcfile, dstfile);
		return dstpath;
	}
	
	@BeforeClass
	public void initialize()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		extent = new ExtentReports();
		report = new ExtentHtmlReporter(System.getProperty("user.dir")+"//Reports//"+dateformat+"_Secondreport.html");
		extent.attachReporter(report);
	}
	
	@AfterClass
	public void closereport()
	{
		extent.flush();
		driver.close();
	}

	@Test
	public void Login_01() throws Exception
	{
		ExtentTest test =extent.createTest("Login_TC01");
		driver.get("https://login.salesforce.com/");
		test.log(Status.INFO, "Login page launched");
		driver.findElement(By.id("username")).sendKeys("madhuryayadunath@gmail.com");		
		test.info("usename entered");
		driver.findElement(By.id("password")).sendKeys("da1989");
		test.info("password entered");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		if(driver.findElement(By.id("error")).getText().equals("Please check your username and password. If you still can't log in, contact your Salesforce administrator."))
		{
		test.log(Status.PASS, "Login_TC01 passed");
		AssertJUnit.assertEquals(driver.findElement(By.id("error")).getText(), "Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
	}
		else {
			test.addScreenCaptureFromPath(takescreenshot());
		test.log(Status.FAIL,"Login_TC01 FAILED");
		AssertJUnit.fail("Login_TC01 FAILED");
	}
		
	}
	
	@Test
	public void Login_02() throws Exception
	{
		ExtentTest test =extent.createTest("Login_TC02");
		driver.get("https://login.salesforce.com/");
		test.log(Status.INFO, "Login page launched");
		driver.findElement(By.id("username")).sendKeys("madhuryayadunath@gmail.com");		
		test.info("usename entered");
		driver.findElement(By.id("password")).sendKeys("da1989");
		test.info("password entered");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		if(driver.findElement(By.id("error")).getText().equals("errormessage"))
		{
		test.log(Status.PASS, "Login_TC02 passed");
		AssertJUnit.assertEquals(driver.findElement(By.id("error")).getText(), "errormessage");
	}
		else {
			test.addScreenCaptureFromPath(takescreenshot());
		test.log(Status.FAIL,"Login_TC02 FAILED");
		AssertJUnit.fail("Login_TC02 FAILED");
	}
		
	}
}

package com.userapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) {
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myExtentReport.html");
		
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("RESTAssured API Testing Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Project Name", "RestAssuredAPITesting_User_Project");
		extent.setSystemInfo("user", "Kajetan");  //what is it?
		
	}
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());  //create new entry it the report
		
		test.log(Status.PASS, "Test Case PASSED is "+result.getName());
	}
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());  //create new entry it the report		
		
		test.log(Status.FAIL, "Test Case FAILED is "+result.getName());
		test.log(Status.FAIL, "Test Case FAILED is "+result.getThrowable());  //to get error/exception to the report
	}
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		
		test.log(Status.SKIP, "Test Case SKIPPED is "+result.getName());
	}
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}

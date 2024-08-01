package com.Generic_Utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LisImplClass implements ITestListener{

	ExtentSparkReporter htmlreport;
	ExtentReports report;
	ExtentTest test ;

	@Override
	public void onStart(ITestContext context) {
		//TestScript Execution Starts from here
		
				htmlreport= new ExtentSparkReporter(".\\TestNg_Dch\\extentReport.html");
				// specify location of the report 
				htmlreport.config().setDocumentTitle("OrangeHRM.Automation Testing"); // Title of the Report
				htmlreport.config().setReportName("Seniour SDET Hemanth"); // Name of the report
				htmlreport.config().setTheme(Theme.STANDARD);
						 
				report = new ExtentReports();
				report.attachReporter(htmlreport);
				report.setSystemInfo("Application", "Author API");
				report.setSystemInfo("Base Browser", "chrome");
				report.setSystemInfo("Base Platform", System.getProperty("os.name"));
				report.setSystemInfo("Environment", "QA");
				report.setSystemInfo("Reporter Name", "Hems");	
	}
	@Override
	public void onTestStart(ITestResult result) {
		//TestScript execution starts from here
		String methodName = result.getMethod().getMethodName();
			test = report.createTest(methodName);
			Reporter.log(methodName+"==== Execution Started ", true);
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName+"Passed");
		Reporter.log(methodName+"==== Execution onTestSuccess succesfully ", true);
	}
	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
			try {
				String path = WebDriverUtils.takescreenshot(BaseClass.sdriver, methodName);
				test.addScreenCaptureFromPath(path);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			test.log(Status.FAIL, methodName+"Failed");
			test.log(Status.FAIL, result.getThrowable());
			Reporter.log(methodName+"==== Execution onTestFailure Failed ");
		} 
	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+"Skipped");
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(methodName+"==== Execution onTestSkipped Skipped ");
	
	}
	@Override
	public void onFinish(ITestContext context) {
	report.flush();
	}
	
	}

	
	
	


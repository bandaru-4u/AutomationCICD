package TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent=ExtentReportNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> tl=new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test=extent.createTest(result.getMethod().getMethodName());
		tl.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		tl.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		tl.get().fail(result.getThrowable());
		String filePath = null;
    try {
    	driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		filePath= getScreenshot(result.getMethod().getMethodName(),driver);
	} 
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    tl.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
}

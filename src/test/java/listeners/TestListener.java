package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import base.BaseTest;
import utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Initializes the Spark reporter to create the HTML file in the reports folder
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExecutionReport.html");
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Creates a test entry in the report when a test method begins
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed: Cart verification successful.");
        
        // Generates a unique timestamp to ensure the screenshot is fresh
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = result.getName() + "_" + timestamp;
        
        // Capture and attach the screenshot of the current page (Cart Page)
        ScreenshotUtil.takeScreenshot(BaseTest.driver, fileName);
        test.addScreenCaptureFromPath("../screenshots/" + fileName + ".png");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        
        // Captures screenshot at the exact moment of failure
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = result.getName() + "_Fail_" + timestamp;
        
        ScreenshotUtil.takeScreenshot(BaseTest.driver, fileName);
        test.addScreenCaptureFromPath("../screenshots/" + fileName + ".png");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Writes all test results and images into the final HTML report
        extent.flush();
    }
}
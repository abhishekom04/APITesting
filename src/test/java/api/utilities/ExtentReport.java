package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport implements ITestListener {

    public ExtentSparkReporter extentSparkReporter;  //used for setting up the UI of the report
    public ExtentReports extents;  //projecting common data like environment data, user data, project name, module name
    public ExtentTest test;  //used for creating entries to the report i.e if the test fails entering that data
    String repName;

    public void onStart(ITestContext context) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
        repName="Test-Result_"+timeStamp+".html";

        extentSparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        extentSparkReporter.config().setDocumentTitle("API Test Report");
        extentSparkReporter.config().setReportName("Petstore User API");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extents = new ExtentReports();

        extents.attachReporter(extentSparkReporter);
        extents.setSystemInfo("os.name", "Windows");
        //extents.setSystemInfo("os.name", System.getProperty("os.name"));
        extents.setSystemInfo("user.name", System.getProperty("user.name"));
        extents.setSystemInfo("java.version", "1.8");
        extents.setSystemInfo("Environment","QA");
        extents.setSystemInfo("User","admin");

    }

    public void onTestSuccess(ITestResult result) {

        test = extents.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.assignCategory(result.getMethod().getMethodName());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {

        test = extents.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.assignCategory(result.getMethod().getMethodName());
        test.createNode(result.getName());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {

        test = extents.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.assignCategory(result.getMethod().getMethodName());
        test.createNode(result.getName());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {

        //if this method is not called then report is not generated
        //makes everything ready in the report
        extents.flush();
    }

    public void onTestStart(ITestResult result) {}

    public void onTestFailedButWithinTestResult(ITestResult result) {}


}

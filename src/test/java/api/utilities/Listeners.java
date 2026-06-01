package api.utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {

    public void onTestStart(ITestResult result) {

        System.out.println("[Listeners] Starting test " + result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {

        System.out.println("[Listeners] Test " + result.getMethod().getMethodName() + " successfull");
    }

    public void onTestSkipped(ITestResult result) {

        System.out.println("[Listeners] Test " + result.getMethod().getMethodName() + " skipped");
    }

    public void onTestFailure(ITestResult result) {

        System.out.println("[Listeners] Test " + result.getMethod().getMethodName() + " failed");
    }

}

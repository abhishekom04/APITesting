package api.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 1; // retry up to 1 time

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test: "+result.getName()+" again, attempt " + retryCount);
            return true;
        }
        return false;
    }
}

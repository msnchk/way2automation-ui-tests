package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int maxRetryCount = 2;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(!iTestResult.isSuccess()) {
            if (retryCount < maxRetryCount) {
                retryCount++;
                return true;
            }
        }
        return false;
    }
}

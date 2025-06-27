package listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        if (!(result.getInstance() instanceof BaseTest)) {
            throw new IllegalStateException("Test class must extend BaseTest to support screenshots.");
        }
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        attachScreenshotToAllure(driver);    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    private byte[] attachScreenshotToAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

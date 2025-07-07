package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {
    private static final int TIMEOUT_SECONDS = 20;

    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        getWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForNumberOfTabs(WebDriver driver, int numberOfTabs) {
        getWait(driver).until(ExpectedConditions.numberOfWindowsToBe(numberOfTabs));
    }

    public static void waitForAlert(WebDriver driver) {
        getWait(driver).until(ExpectedConditions.alertIsPresent());
    }
}

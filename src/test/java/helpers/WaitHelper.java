package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {
    private static final int TIMEOUT_SECONDS = 15;

    private WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = getWait(driver);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = getWait(driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForGetURL(WebDriver driver, String url) {
        WebDriverWait wait = getWait(driver);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void waitForNewTab(WebDriver driver) {
        WebDriverWait wait = getWait(driver);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }
}

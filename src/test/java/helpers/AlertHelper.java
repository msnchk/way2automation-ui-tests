package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class AlertHelper {
    private final WebDriver driver;
    private final Actions actions;

    public AlertHelper(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
    }

    @Step("Check if alert present")
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            return false;
        }
    }

    @Step("Get alert text if exists")
    public String getAlertTextIfExists() {
        try {
            Alert alert = driver.switchTo().alert();
            return alert.getText();
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            return "";
        }
    }

    @Step("Accept alert")
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}

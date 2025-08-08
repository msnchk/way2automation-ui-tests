package pages.way2autojquery;

import helpers.EnvKeys;
import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AlertPage extends BasePage {
    public static final String ALERT_PAGE_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.JQUERY_ALERT_PATH.get();

    @FindBy(xpath = "//a[contains(text(), 'Input Alert')]")
    private WebElement inputAlertTab;

    @FindBy(xpath = "//iframe[@src='alert/input-alert.html']")
    private WebElement iframe;

    @FindBy(xpath = "//button[contains(text(), 'Click the button to demonstrate the Input box')]")
    private WebElement displayAlertButton;

    @FindBy(xpath = "//p[contains(text(), 'Hello')]")
    private WebElement welcomeText;

    public AlertPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Open 'Input Alert' tab")
    public void openInputAlertTab() {
        inputAlertTab.click();
    }

    @Step("Click 'Display Alert' button inside iframe")
    public void clickDisplayAlertButton() {
        switchToIframe(iframe);
        displayAlertButton.click();
    }

    @Step("Enter user name into alert and accept it")
    public void enterUserNameInAlert(String name) {
        WaitHelper.waitForAlert(driver);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(name);
        alert.accept();
    }

    public String getExpectedText(String name) {
        return "Hello " + name + "! How are you today?";
    }

    @Step("Get greeting message from the page")
    public String getWelcomeText() {
        return welcomeText.getText();
    }
}

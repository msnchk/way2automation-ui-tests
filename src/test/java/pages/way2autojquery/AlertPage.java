package pages.way2autojquery;

import helpers.PropertyProvider;
import helpers.WaitHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AlertPage extends BasePage {
    public static final String ALERT_PAGE_URL = PropertyProvider.jqueryBaseUrl() + "alert.php";

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

    public void openInputAlertTab() {
        inputAlertTab.click();
    }

    public void clickDisplayAlertButton() {
        switchToIframe(iframe);
        displayAlertButton.click();
    }

    public void enterUserNameInAlert(String name) {
        WaitHelper.waitForAlert(driver);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(name);
        alert.accept();
    }

    public String getExpectedText(String name) {
        return "Hello " + name + "! How are you today?";
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }
}

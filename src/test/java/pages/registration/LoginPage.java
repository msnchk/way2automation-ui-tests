package pages.registration;

import helpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class LoginPage extends BasePage {
    public static final String LOGIN_FORM_URL = LoginConfirmPage.LOGIN_CONFIRM_URL + "login";

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "[id*='input_username']")
    private WebElement usernameDescriptionInput;

    @FindBy(css = "button.btn.btn-danger")
    private WebElement loginButton;

    private By alertMessageSelector = By.cssSelector("div.alert.alert-danger");

    public LoginPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public void inputLoginData(String username, String password, String usernameDescription) {
        WaitHelper.waitForElementToBeClickable(driver, usernameInput);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        usernameDescriptionInput.sendKeys(usernameDescription);
        actions.pause(1000).perform();
    }

    public void clickLoginButton() {
        loginButton.click();
        actions.pause(2000).perform();
    }

    public String getAlertTextIfPresent() {
        List<WebElement> alerts = driver.findElements(alertMessageSelector);
        return alerts.isEmpty() ? null : alerts.get(0).getText();
    }

    public boolean isButtonLoginEnabled() {
        return loginButton.isEnabled();
    }
}

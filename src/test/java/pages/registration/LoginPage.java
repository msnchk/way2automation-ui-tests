package pages.registration;

import helpers.EnvKeys;
import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class LoginPage extends BasePage {
    public static final String LOGIN_FORM_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.LOGIN_FORM_PATH.get();

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "[id*='input_username']")
    private WebElement usernameDescriptionInput;

    @FindBy(css = "button.btn.btn-danger")
    private WebElement loginButton;

    @FindBy(css = "input.ng-invalid.ng-touched ")
    private WebElement invalidTouchedUsernameError;

    private By alertMessageSelector = By.cssSelector("div.alert.alert-danger");

    public LoginPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Fill login form with username, password and description:")
    public void inputLoginData(String username, String password, String usernameDescription) {
        WaitHelper.waitForElementToBeClickable(driver, usernameInput);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        usernameDescriptionInput.sendKeys(usernameDescription);
        actions.pause(1000).perform();
    }

    @Step("Click on the 'Login' button")
    public void clickLoginButton() {
        loginButton.click();
        actions.pause(2000).perform();
    }

    @Step("Check if error alert is displayed and get its text")
    public String getAlertTextIfPresent() {
        List<WebElement> alerts = driver.findElements(alertMessageSelector);
        return alerts.isEmpty() ? null : alerts.get(0).getText();
    }

    @Step("Check if the 'Login' button is enabled")
    public boolean isButtonLoginEnabled() {
        return loginButton.isEnabled();
    }

    @Step("Click on the 'Username' field and then unfocus")
    public void touchUsernameInputAndUnfocus() {
        WaitHelper.waitForElementToBeClickable(driver, usernameInput);
        usernameInput.click();
        unfocusElement(usernameInput);
    }

    @Step("Check if validation error for 'Username' is displayed")
    public boolean isInvalidTouchedUsernameErrorDisplayed() {
        return invalidTouchedUsernameError.isDisplayed();
    }
}

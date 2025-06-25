package pages.registration;

import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseLoginPage {
    public static final String LOGIN_FORM_URL = LoginConfirmPage.LOGIN_CONFIRM_URL + "login";

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "[id*='input_username']")
    private WebElement usernameDescriptionInput;

    @FindBy(css = "button.btn.btn-danger")
    private WebElement loginButton;

    @FindBy(css = "div.alert.alert-danger")
    private WebElement alertMessage;

    public LoginPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public void login(String username, String password, String usernameDescription) {
        WaitHelper.waitForElementToBeClickable(driver, usernameInput);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        usernameDescriptionInput.sendKeys(usernameDescription);
        loginButton.click();
        actions.pause(2000).perform();
    }

    public String getAlertText() {
        return alertMessage.getText();
    }

    public boolean isButtonLoginEnabled() {
        return loginButton.isEnabled();
    }
}

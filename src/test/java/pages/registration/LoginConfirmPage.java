package pages.registration;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginConfirmPage extends BaseLoginPage{
    public static final String LOGIN_CONFIRM_URL = PropertyProvider.loginFormUrl();

    @FindBy(xpath = "//p[contains(@class, 'ng-scope') and normalize-space(text())=\"You're logged in!!\"]")
    private WebElement successLoginMessage;

    public LoginConfirmPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public boolean isConfirmingTextDisplayed() {
        return successLoginMessage.isDisplayed();
    }
}

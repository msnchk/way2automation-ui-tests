package pages.registration;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginConfirmPage extends BaseLoginPage{
    public static final String LOGIN_CONFIRM_URL = PropertyProvider.loginFormUrl();

    public LoginConfirmPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }
}

package pages.registration;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;

public class LoginConfirmPage extends BasePage {
    public static final String LOGIN_CONFIRM_URL = PropertyProvider.loginFormUrl();

    public LoginConfirmPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }
}

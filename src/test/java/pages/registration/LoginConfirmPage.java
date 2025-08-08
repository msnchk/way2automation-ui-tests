package pages.registration;

import helpers.EnvKeys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;

public class LoginConfirmPage extends BasePage {
    public static final String LOGIN_CONFIRM_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.LOGIN_CONFIRM_PATH.get();

    public LoginConfirmPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }
}

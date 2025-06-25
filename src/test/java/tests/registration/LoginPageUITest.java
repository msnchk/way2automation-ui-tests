package tests.registration;

import data.registration.LoginDataProvider;
import data.registration.LoginDataCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.registration.LoginConfirmPage;
import pages.registration.LoginPage;
import tests.BaseTest;

public class LoginPageUITest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void openMainPage() {
        getDriver().get(LoginPage.LOGIN_FORM_URL);
        loginPage = new LoginPage(getDriver(), getActions());
    }

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void shouldTestLoginWithValidAndInvalidData(LoginDataCase data) {
        loginPage.login(data.getUsername(), data.getPassword(), data.getUsernameDescription());
        SoftAssert soft = new SoftAssert();

        if (data.isExpectedSuccess()) {
            LoginConfirmPage loginConfirmPage = new LoginConfirmPage(getDriver(), getActions());
            soft.assertEquals(
                    getDriver().getCurrentUrl(),
                    LoginConfirmPage.LOGIN_CONFIRM_URL,
                    "There was no redirect to the page that confirms authorization.");
            soft.assertTrue(
                    loginConfirmPage.isConfirmingTextDisplayed(),
                    "Text about success login on confirming page wasn't displayed");
        } else {
            soft.assertEquals(
                    getDriver().getCurrentUrl(),
                    LoginPage.LOGIN_FORM_URL,
                    "There was unexpected redirect to the page that confirms authorization.");

            if (data.isButtonShouldBeEnabled()) {
                soft.assertEquals(
                        loginPage.getAlertText(),
                        data.getExpectedErrorMessage(),
                        "Alert text doesn't match expected");
            } else {
                soft.assertFalse(
                        loginPage.isButtonLoginEnabled(),
                        "Login button shouldn't be enable after this input");
            }
        }
        soft.assertAll();
    }
}

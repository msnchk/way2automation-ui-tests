package tests.registration;

import data.registration.LoginDataProvider;
import data.registration.LoginDataCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
        loginPage.inputLoginData(data.getUsername(), data.getPassword(), data.getUsernameDescription());

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(
                loginPage.isButtonLoginEnabled(),
                data.isButtonShouldBeEnabled(),
                "Button enabled state doesn't match expected"
        );
        loginPage.clickLoginButton();

        soft.assertEquals(
                loginPage.getAlertTextIfPresent(),
                data.getExpectedErrorMessage(),
                "Alert's presence doesn't match expected"

        );
        soft.assertEquals(
                getDriver().getCurrentUrl(),
                data.getExpectedURL(),
                "URL doesn't match expected"
        );
        soft.assertAll();
    }
}

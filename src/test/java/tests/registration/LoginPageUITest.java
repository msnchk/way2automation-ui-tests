package tests.registration;

import data.registration.LoginDataProvider;
import data.registration.LoginDataCase;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.registration.LoginPage;
import tests.BaseTest;

@Epic("Way2Automation Registration Module")
public class LoginPageUITest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void openMainPage() {
        getDriver().get(LoginPage.LOGIN_FORM_URL);
        loginPage = new LoginPage(getDriver(), getActions());
    }

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class,
    description = "Test login form behavior with various data combinations")
    @Feature("Login data handling")
    @Story("Login with different data combinations")
    @Severity(SeverityLevel.CRITICAL)
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

    @Test(description = "Verify that username field shows validation error when touched and left empty")
    @Feature("Form validation")
    @Story("Click on username field and left it empty")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDisplayInvalidTouchedUsernameError() {
        loginPage.touchUsernameInputAndUnfocus();
        Assert.assertTrue(loginPage.isInvalidTouchedUsernameErrorDisplayed());
    }

    @Test(description = "Verify that login page doesn't have horizontal or vertical scrollbars")
    @Feature("Page layout")
    @Severity(SeverityLevel.MINOR)
    public void shouldCheckScrollOnPage() {
        SoftAssert soft = new SoftAssert();
        soft.assertFalse(loginPage.hasVerticalScroll());
        soft.assertFalse(loginPage.hasHorizontalScroll());
        soft.assertAll();
    }
}

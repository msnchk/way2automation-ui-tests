package data.registration;

import org.testng.annotations.DataProvider;
import pages.registration.LoginConfirmPage;
import pages.registration.LoginPage;

public class LoginDataProvider {
    private static final String DEFAULT_USERNAME = "angular";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String DEFAULT_DESCRIPTION = "test";
    private static final String WRONG_DATA_MESSAGE = "Username or password is incorrect";

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {
                        new LoginDataCase(
                                DEFAULT_USERNAME,
                                DEFAULT_PASSWORD,
                                DEFAULT_DESCRIPTION,
                                true,
                                null,
                                LoginConfirmPage.LOGIN_CONFIRM_URL)
                },
                {
                        new LoginDataCase(
                                "wrong_username",
                                DEFAULT_PASSWORD,
                                DEFAULT_DESCRIPTION,
                                true,
                                WRONG_DATA_MESSAGE,
                                LoginPage.LOGIN_FORM_URL)
                },
                {
                        new LoginDataCase(
                                DEFAULT_USERNAME,
                                "wrong_password",
                                DEFAULT_DESCRIPTION,
                                true,
                                WRONG_DATA_MESSAGE,
                                LoginPage.LOGIN_FORM_URL)
                },
                {
                        new LoginDataCase(
                                "",
                                DEFAULT_PASSWORD,
                                DEFAULT_DESCRIPTION,
                                false,
                                null,
                                LoginPage.LOGIN_FORM_URL)
                },
                {
                        new LoginDataCase(
                                DEFAULT_USERNAME,
                                "",
                                DEFAULT_DESCRIPTION,
                                false,
                                null,
                                LoginPage.LOGIN_FORM_URL)
                },
                {
                        new LoginDataCase(
                                "12",
                                DEFAULT_PASSWORD,
                                DEFAULT_DESCRIPTION,
                                false,
                                null,
                                LoginPage.LOGIN_FORM_URL)
                },
                {
                        new LoginDataCase(
                                DEFAULT_USERNAME,
                                "12",
                                DEFAULT_DESCRIPTION,
                                false,
                                null,
                                LoginPage.LOGIN_FORM_URL)
                }
        };
    }
}
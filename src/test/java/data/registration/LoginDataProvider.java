package data.registration;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    private static final String WRONG_DATA_MESSAGE = "Username or password is incorrect";
    private static final String DEFAULT_USERNAME = "angular";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String DEFAULT_DESCRIPTION = "test";

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
                                true)
                },
                {
                        new LoginDataCase(
                                "wrong_username",
                                DEFAULT_PASSWORD,
                                DEFAULT_DESCRIPTION,
                                false,
                                WRONG_DATA_MESSAGE,
                                true)
                },
                {
                        new LoginDataCase(
                                DEFAULT_USERNAME,
                                "wrong_password",
                                DEFAULT_DESCRIPTION,
                                false,
                                WRONG_DATA_MESSAGE,
                                true)
                },
                {
                        new LoginDataCase(
                                "",
                                DEFAULT_PASSWORD,
                                DEFAULT_DESCRIPTION,
                                false,
                                null,
                                false)
                },
                {
                        new LoginDataCase(
                                DEFAULT_USERNAME,
                                "",
                                DEFAULT_DESCRIPTION,
                                false,
                                null,
                                false)
                },
                {
                        new LoginDataCase(
                                "12",
                                DEFAULT_PASSWORD,
                                DEFAULT_DESCRIPTION,
                                false,
                                null,
                                false)
                },
                {
                        new LoginDataCase(
                                DEFAULT_USERNAME,
                                "12",
                                DEFAULT_DESCRIPTION,
                                false,
                                null,
                                false)
                }
        };
    }
}
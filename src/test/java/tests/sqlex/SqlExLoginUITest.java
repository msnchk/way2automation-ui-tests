package tests.sqlex;

import helpers.PropertyProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.sqlex.SqlExMainPage;
import tests.BaseTest;

@Epic("SqlEx Main Page UI")
public class SqlExLoginUITest extends BaseTest {
    private SqlExMainPage sqlExMainPage;

    @BeforeMethod
    public void openMainPage() {
        getDriver().get(SqlExMainPage.SQLEX_MAIN_PAGE_URL);
        sqlExMainPage = new SqlExMainPage(getDriver(), getActions());
    }

    @Test(description = "Verify that user can be authorized on SQLEx via stored cookies or valid username and password")
    @Feature("Login")
    @Story("User is authorized using cookies if available, or logs in with username and password")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldLoginSuccessfully() {
        sqlExMainPage.sqlExAuthorize(PropertyProvider.sqlExLogin(), PropertyProvider.sqlExPassword());

        Assert.assertTrue(
                sqlExMainPage.isConfirmingLoginElementDisplayed(),
                "Element that confirms successful login wasn't displayed.");
    }
}

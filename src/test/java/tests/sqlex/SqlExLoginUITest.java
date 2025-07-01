package tests.sqlex;

import helpers.PropertyProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.sqlex.SqlExMainPage;
import tests.BaseTest;

public class SqlExLoginUITest extends BaseTest {
    private SqlExMainPage sqlExMainPage;

    @BeforeMethod
    public void openMainPage() {
        getDriver().get(SqlExMainPage.SQLEX_MAIN_PAGE_URL);
        sqlExMainPage = new SqlExMainPage(getDriver(), getActions());
    }

    @Test
    public void shouldLoginSuccessfully() {
        sqlExMainPage.sqlExAuthorize(PropertyProvider.sqlExLogin(), PropertyProvider.sqlExPassword());

        Assert.assertTrue(
                sqlExMainPage.isConfirmingLoginElementDisplayed(),
                "Element that confirms successful login wasn't displayed.");
    }
}

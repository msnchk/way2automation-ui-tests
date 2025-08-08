package tests.httpwatch;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.httpwatch.AuthPage;
import pages.httpwatch.ImagePage;
import tests.BaseTest;

@Epic("HttpWatch Authentication")
public class BasicAuthTest extends BaseTest {

    @Test(description = "Verify that image requiring basic authentication is displayed after providing login and password")
    @Feature("Basic Authentication")
    @Story("User provides login and password and gets access to protected image")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldDisplayImageViaBasicAuth() {
        getDriver().get(AuthPage.HTTPWATCH_AUTH_URL);
        AuthPage authPage = new AuthPage(getDriver(), getActions());
        authPage.clickDisplayImageButton();
        String imageBasicAuthUrl = authPage.getImageBasicAuthURL();
        getDriver().get(imageBasicAuthUrl);
        ImagePage imagePage = new ImagePage(getDriver(), getActions());

        Assert.assertTrue(imagePage.isImageLoaded(), "Auth image didn't load");
    }
}

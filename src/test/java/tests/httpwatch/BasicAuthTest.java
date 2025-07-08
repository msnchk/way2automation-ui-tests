package tests.httpwatch;

import helpers.PropertyProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.httpwatch.AuthPage;
import pages.httpwatch.ImagePage;
import tests.BaseTest;

public class BasicAuthTest extends BaseTest {

    @Test
    public void shouldDisplayImageViaBasicAuth(){
        getDriver().get(AuthPage.HTTPWATCH_AUTH_URL);
        AuthPage authPage = new AuthPage(getDriver(), getActions());
        authPage.clickDisplayImageButton();
        String imageBasicAuthUrl = authPage
                .getImageBasicAuthURL(PropertyProvider.httpWatchLogin(), PropertyProvider.httpWatchPassword());
        getDriver().get(imageBasicAuthUrl);
        ImagePage imagePage = new ImagePage(getDriver(), getActions());

        Assert.assertTrue(imagePage.isImageLoaded(), "Auth image didn't load");
    }
}

package tests;

import helpers.PropertyProvider;
import helpers.WaitHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;

public class MainPageUITest extends BaseTest {
    public static final String BASE_URL = PropertyProvider.baseUrl();
    private MainPage mainPage;

    @BeforeMethod
    public void openMainPage() {
        getDriver().get(MainPage.MAIN_PAGE_URL);
        mainPage = new MainPage(getDriver(), getActions());
    }

    @Test
    public void shouldWaitPopupAndNavigateOnRegisterNowClick() {
        mainPage.waitForPopup();
        mainPage.clickButtonRegisterNowOnPopup();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                BASE_URL + "lifetime-membership-club/",
                "Membership club page wasn't opened");
    }

    @Test
    public void shouldNavigateToSeleniumWithJavaFromMenu() {
        mainPage.navigateToSeleniumWithJavaFromMenu();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                BASE_URL + "selenium-training/online-training-webinars/",
                "Selenium training page wasn't opened");
    }

    @Test
    public void shouldNavigateToYouTubeFromHeader() {
        mainPage.navigateToYouTubeFromHeaderIcon();
        WaitHelper.waitForNumberOfTabs(getDriver(), 2);
        Object[] windowHandles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) windowHandles[1]);

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://www.youtube.com/c/seleniumappiumtutorialtraining",
                "YouTube page was not opened (in the second tab)");
    }

    @Test
    public void shouldNavigateToAPITestingWithJavaFromFlipBox() {
        mainPage.navigateToAPITestingWithJavaFromFlipBox();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                BASE_URL + "soapui/rest-api-webservices-testing-training",
                "Java API testing page wasn't opened");

    }

    @Test
    public void shouldNavigateToRobotFrameworkForAutomationFromCarousel() {
        mainPage.waitForPopup();
        mainPage.clickButtonClosePopup();
        mainPage.navigateToRobotFrameworkForAutomationFromCarousel();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://www.selenium-tutorial.com/p/robot-framework-python-ui-api-automation",
                "Selenium tutorial website wasn't opened");
    }
}

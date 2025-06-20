package tests;

import helpers.WaitHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;

public class MainPageUITest extends BaseTest {
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

        Assert.assertEquals(getDriver().getCurrentUrl(),
                MainPage.MEMBERSHIP_CLUB_URL, "Membership club page wasn't opened");
    }

    @Test
    public void shouldNavigateToSeleniumWithJavaFromMenu() {
        mainPage.waitForPopup();
        mainPage.clickButtonClosePopup();
        mainPage.navigateToSeleniumWithJavaFromMenu();

        Assert.assertEquals(getDriver().getCurrentUrl(),
                MainPage.SELENIUM_TRAINING_URL, "Selenium training page wasn't opened");
    }

    @Test
    public void shouldNavigateToYouTubeFromHeader() {
        mainPage.waitForPopup();
        mainPage.clickButtonClosePopup();
        mainPage.navigateToYouTubeFromHeaderIcon();
        WaitHelper.waitForNumberOfTabs(getDriver(), 2);
        Object[] windowHandles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) windowHandles[1]);

        Assert.assertEquals(getDriver().getCurrentUrl(),
                MainPage.YOUTUBE_CHANNEL_URL, "YouTube page was not opened (in the second tab)");
    }

    @Test
    public void shouldNavigateToAPITestingWithJavaFromFlipBox() {
        mainPage.waitForPopup();
        mainPage.clickButtonClosePopup();
        mainPage.navigateToAPITestingWithJavaFromFlipBox();

        Assert.assertEquals(getDriver().getCurrentUrl(),
                MainPage.JAVA_API_TESTING_URL, "Java API testing page wasn't opened");

    }

    @Test
    public void shouldNavigateToRobotFrameworkForAutomationFromCarousel() {
        mainPage.waitForPopup();
        mainPage.clickButtonClosePopup();
        mainPage.navigateToRobotFrameworkForAutomationFromCarousel();

        Assert.assertEquals(getDriver().getCurrentUrl(),
                MainPage.ROBOT_FRAMEWORK_SELENIUM_TUTORIAL_URL, "Selenium tutorial website wasn't opened");
    }
}

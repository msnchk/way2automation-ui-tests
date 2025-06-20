package tests;

import helpers.PropertyProvider;
import helpers.WaitHelper;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;

public class MainPageUITest extends BaseTest {
    private MainPage mainPage;
    private final WaitHelper waitHelper = new WaitHelper();
    private final String membershipClubUrl = PropertyProvider.getInstance().getProperty("way2automation.membership-club.url");
    private final String seleniumTrainingUrl = PropertyProvider.getInstance().getProperty("way2automation.selenium-training.url");
    private final String youTubeChannelUrl = PropertyProvider.getInstance().getProperty("way2automation.youtube.url");
    private final String javaApiTestingUrl = PropertyProvider.getInstance().getProperty("way2automation.java-api-testing.url");
    private final String seleniumTutorialUrl = PropertyProvider.getInstance().getProperty("selenium-tutorial.robot-framework.url");

    @BeforeMethod
    public void openMainPage() {
        getDriver().get(MainPage.MAIN_PAGE_URL);
        mainPage = new MainPage(getDriver(), getActions());
    }

    @Test
    public void shouldCheckPopupAndNavigateOnRegisterNowClick() {
        Assert.assertTrue(mainPage.isPopupVisible(), "Popup should be visible");
        mainPage.clickButtonRegisterNowOnPopup();

        Assert.assertTrue(isCurrentPageMatchExpected(membershipClubUrl), "Expected URL: " + membershipClubUrl + ", but get " + getDriver().getCurrentUrl());
    }

    @Test
    public void shouldNavigateToSeleniumWithJavaFromMenu() {
        mainPage.hidePopup();
        mainPage.navigateToSeleniumWithJavaFromMenu();

        Assert.assertTrue(isCurrentPageMatchExpected(seleniumTrainingUrl), "Expected URL: " + seleniumTrainingUrl + ", but get " + getDriver().getCurrentUrl());
    }

    @Test
    public void shouldNavigateToYouTubeFromHeader() {
        mainPage.hidePopup();
        mainPage.navigateToYouTubeFromHeaderIcon();
        waitHelper.waitForNewTab(getDriver());
        Object[] windowHandles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) windowHandles[1]);

        Assert.assertTrue(isCurrentPageMatchExpected(youTubeChannelUrl), "Expected URL: " + youTubeChannelUrl + ", but get " + getDriver().getCurrentUrl());
    }

    @Test
    public void shouldNavigateToAPITestingWithJavaFromFlipBox() {
        mainPage.hidePopup();
        mainPage.navigateToAPITestingWithJavaFromFlipBox();

        Assert.assertTrue(isCurrentPageMatchExpected(javaApiTestingUrl), "Expected URL: " + javaApiTestingUrl + ", but get " + getDriver().getCurrentUrl());
    }

    @Test
    public void shouldNavigateToRobotFrameworkForAutomationFromCarousel() {
        mainPage.hidePopup();
        mainPage.navigateToRobotFrameworkForAutomationFromCarousel();

        Assert.assertTrue(isCurrentPageMatchExpected(seleniumTutorialUrl), "Expected URL: " + seleniumTutorialUrl + ", but get " + getDriver().getCurrentUrl());
    }

    public boolean isCurrentPageMatchExpected(String expectingUrl) {
        try {
            waitHelper.waitForGetURL(getDriver(), expectingUrl);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}

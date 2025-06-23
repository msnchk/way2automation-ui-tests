package tests;

import helpers.PropertyProvider;
import helpers.WaitHelper;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;

@Epic("Way2Automation Main Page UI")
public class MainPageUITest extends BaseTest {
    public static final String BASE_URL = PropertyProvider.baseUrl();
    private MainPage mainPage;

    @BeforeMethod
    public void openMainPage() {
        getDriver().get(MainPage.MAIN_PAGE_URL);
        mainPage = new MainPage(getDriver(), getActions());
    }

    @Test
    @Feature("Popup Handling")
    @Story("Clicking 'Register Now' in the popup navigates to Membership page")
    @Severity(SeverityLevel.NORMAL)
    public void shouldWaitPopupAndNavigateOnRegisterNowClick() {
        mainPage.waitForPopup();
        mainPage.clickButtonRegisterNowOnPopup();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                BASE_URL + "lifetime-membership-club/",
                "Membership club page wasn't opened");
    }

    @Test
    @Feature("Top Menu Navigation")
    @Story("Navigate to Selenium with Java course from top menu")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNavigateToSeleniumWithJavaFromMenu() {
        mainPage.navigateToSeleniumWithJavaFromMenu();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                BASE_URL + "selenium-training/online-training-webinars/",
                "Selenium training page wasn't opened");
    }

    @Test
    @Feature("Header Links")
    @Story("Clicking YouTube icon opens Way2Automation channel in a new tab")
    @Severity(SeverityLevel.MINOR)
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
    @Feature("Course Cards")
    @Story("Navigate to API Testing with Java course from flip box")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNavigateToAPITestingWithJavaFromFlipBox() {
        mainPage.navigateToAPITestingWithJavaFromFlipBox();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                BASE_URL + "soapui/rest-api-webservices-testing-training",
                "Java API testing page wasn't opened");

    }

    @Test
    @Feature("Carousel Navigation")
    @Story("Navigate to Robot Framework course from carousel block")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNavigateToRobotFrameworkForAutomationFromCarousel() {
        mainPage.waitForPopup();
        mainPage.clickButtonClosePopup();
        mainPage.navigateToRobotFrameworkForAutomationFromCarousel();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://www.selenium-tutorial.com/p/robot-framework-python-ui-api-automation",
                "Selenium tutorial website wasn't opened");
    }

    @Test
    @Feature("Top Menu Navigation")
    @Story("Navigate to Cypress course from top menu")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNavigateToCypressTutorialFromMenu() {
        mainPage.navigateToCypressTutorialFromMenu();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://www.selenium-tutorial.com/p/cypress-learn-in-depth-implementation-with-live-projects",
                "Selenium tutorial website wasn't opened");
    }

    @Test
    @Feature("Buttons")
    @Story("Clicking 'Register Now' button on main page navigates to Membership page")
    @Severity(SeverityLevel.NORMAL)
    public void shouldClickRegisterNowAndNavigateToMembershipPage() {
        mainPage.clickButtonRegisterNowOnMainPage();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                BASE_URL + "lifetime-membership-club/",
                "Membership club page wasn't opened");
    }
}

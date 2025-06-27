package tests.way2automation;

import helpers.PropertyProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.way2automation.MainPage;
import tests.BaseTest;

@Epic("Way2Automation Main Page UI")
public class MainPageUIFailTest extends BaseTest {
    public static final String BASE_URL = PropertyProvider.baseUrl();
    private MainPage mainPage;

    @BeforeMethod
    public void openMainPage() {
        getDriver().get(MainPage.MAIN_PAGE_URL);
        mainPage = new MainPage(getDriver(), getActions());
    }

    @Test(description = "TC-2-fail: Verify navigation from top menu to 'Selenium with Java' course page")
    @Description("Упадет из-за несоответствия URL ожидаемому. Должен прикрепиться скрин страницы 'Selenium Training Online'")
    @Feature("Top Menu Navigation")
    @Story("Navigate to Selenium with Java course from top menu")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNavigateToSeleniumWithJavaFromMenu() {
        mainPage.getHeader().navigateToSeleniumWithJavaFromMenu();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                BASE_URL + "selenium-training",
                "Selenium training page wasn't opened");
    }

    @Test(description = "TC-4-fail: Verify that 'Read more' button on 'API Testing with Java' flip box navigates to this course page")
    @Description("Упадет, так как кнопка 'Read more' еще не видима. Ожидается скрин флипбокса.")
    @Feature("Course Cards")
    @Story("Navigate to API Testing with Java course from flip box")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNavigateToAPITestingWithJavaFromFlipBox() {
        mainPage.failNavigateToAPITestingWithJavaFromFlipBox();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                BASE_URL + "soapui/rest-api-webservices-testing-training",
                "Java API testing page wasn't opened");

    }

    @Test(description = "TC-6-fail: Verify navigation from top menu to 'Cypress' course page")
    @Description("Упадет либо потому, что попап появился и не дает кликать по меню, либо потому, что попап не появился.")
    @Feature("Top Menu Navigation")
    @Story("Navigate to Cypress course from top menu")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNavigateToCypressTutorialFromMenu() {
        mainPage.getPopup().waitForPopup();
        mainPage.getHeader().navigateToCypressTutorialFromMenu();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://www.selenium-tutorial.com/p/cypress-learn-in-depth-implementation-with-live-projects",
                "Selenium tutorial website wasn't opened");
    }
}

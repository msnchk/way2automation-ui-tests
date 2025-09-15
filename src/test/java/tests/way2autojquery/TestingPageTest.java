package tests.way2autojquery;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2autojquery.AlertPage;
import pages.way2autojquery.DroppablePage;
import pages.way2autojquery.FramesAndWindowsPage;
import pages.way2autojquery.NewTabPage;
import tests.BaseTest;

@Epic("Way2Automation way2auto_jquery UI Testing")
public class TestingPageTest extends BaseTest {

    @Test(description = "Verify that a draggable element can be dropped onto the target area")
    @Feature("Drag and Drop")
    @Story("User can drag an element and drop it onto the droppable area")
    @Severity(SeverityLevel.NORMAL)
    public void shouldMoveDraggableElementToDroppableArea() {
        getDriver().get(DroppablePage.DROPPABLE_URL);
        DroppablePage droppablePage = new DroppablePage(getDriver(), getActions());
        droppablePage.moveDraggableElementToDroppableArea();

        Assert.assertEquals(
                droppablePage.getTextFromDroppable(),
                "Dropped!",
                "The text in the droppable area didn't change."
        );
    }

    @Test(description = "Verify that new tabs open correctly after clicking on links")
    @Feature("Tabs Handling")
    @Story("The user navigates to new tabs via links")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldOpenNewTabs() {
        getDriver().get(FramesAndWindowsPage.FRAMES_AND_WINDOWS_URL);
        FramesAndWindowsPage framesAndWindowsPage = new FramesAndWindowsPage(getDriver(), getActions());
        NewTabPage newTabPage = framesAndWindowsPage.clickOpenNewTabAndSwitch();

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(
                newTabPage.getElementHelper().getWindowCount(),
                2,
                "Expected 2 tabs to be open after the first click"
        );
        soft.assertEquals(
                getDriver().getCurrentUrl(),
                NewTabPage.NEW_TAB_URL,
                "Second new tab didn't open correctly"
        );
        newTabPage.clickOpenNewTabAndSwitch();

        soft.assertEquals(
                newTabPage.getElementHelper().getWindowCount(),
                3,
                "Expected 3 tabs to be open after the second click"
        );
        soft.assertEquals(
                getDriver().getCurrentUrl(),
                NewTabPage.NEW_TAB_URL,
                "Third tab didn't open correctly");
        soft.assertAll();
    }

    @Test(description = "Verify that user can enter a name in alert and get a personalized greeting")
    @Feature("Alerts")
    @Story("User interacts with alert and enters his name")
    @Severity(SeverityLevel.NORMAL)
    public void shouldEnterUsernameInAlert() {
        getDriver().get(AlertPage.ALERT_PAGE_URL);
        AlertPage alertPage = new AlertPage(getDriver(), getActions());
        alertPage.openInputAlertTab();
        alertPage.clickDisplayAlertButton();
        String name = "Some User";
        alertPage.enterUserNameInAlert(name);

        Assert.assertEquals(
                alertPage.getWelcomeText(),
                alertPage.getExpectedText(name),
                "Displayed greeting text doesn't match expected"
        );
    }
}

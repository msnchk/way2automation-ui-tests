package tests.way2autojquery;

import helpers.WaitHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2autojquery.AlertPage;
import pages.way2autojquery.DroppablePage;
import pages.way2autojquery.FramesAndWindowsPage;
import pages.way2autojquery.NewTabPage;
import tests.BaseTest;

public class TestingPageTest extends BaseTest {

    @Test
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

    @Test
    public void shouldOpenNewTabs() {
        getDriver().get(FramesAndWindowsPage.FRAMES_AND_WINDOWS_URL);
        FramesAndWindowsPage framesAndWindowsPage = new FramesAndWindowsPage(getDriver(), getActions());
        NewTabPage newTabPage = framesAndWindowsPage.clickOpenNewTab();
        Object[] windowHandles1 = getDriver().getWindowHandles().toArray();
        SoftAssert soft = new SoftAssert();

        soft.assertEquals(
                windowHandles1.length,
                2,
                "Expected 2 tabs to be open after the first click");
        getDriver().switchTo().window((String) windowHandles1[1]);

        soft.assertEquals(
                getDriver().getCurrentUrl(),
                NewTabPage.NEW_TAB_URL,
                "First new tab didn't open correctly"
        );
        newTabPage.clickOpenNewTab();
        Object[] windowHandles2 = getDriver().getWindowHandles().toArray();

        soft.assertEquals(
                windowHandles2.length,
                3,
                "Expected 3 tabs to be open after the second click"
        );
        getDriver().switchTo().window((String) windowHandles2[2]);

        soft.assertEquals(
                getDriver().getCurrentUrl(),
                NewTabPage.NEW_TAB_URL,
                "Third tab didn't open correctly");
        soft.assertAll();
    }

    @Test
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

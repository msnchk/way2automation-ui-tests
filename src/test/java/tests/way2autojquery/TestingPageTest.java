package tests.way2autojquery;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.way2autojquery.DroppablePage;
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
}

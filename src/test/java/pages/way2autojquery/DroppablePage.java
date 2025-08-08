package pages.way2autojquery;

import helpers.EnvKeys;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class DroppablePage extends BasePage {
    public static final String DROPPABLE_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.JQUERY_DROPPABLE_PATH.get();

    @FindBy(className = "demo-frame")
    private WebElement iframe;

    @FindBy(id = "draggable")
    private WebElement draggable;

    @FindBy(id = "droppable")
    private WebElement droppable;

    public DroppablePage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Drag the element inside frame and drop it into the droppable area")
    public void moveDraggableElementToDroppableArea() {
        switchToIframe(iframe);
        actions
                .dragAndDrop(draggable, droppable)
                .perform();
    }

    @Step("Get displayed text from droppable area")
    public String getTextFromDroppable() {
        return droppable.getText();
    }
}

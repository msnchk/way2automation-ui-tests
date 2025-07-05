package pages.way2autojquery;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class DroppablePage extends BasePage {
    public static final String DROPPABLE_URL = PropertyProvider.jqueryBaseUrl() + ("droppable.php");

    @FindBy(className = "demo-frame")
    private WebElement iframe;

    @FindBy(id = "draggable")
    private WebElement draggable;

    @FindBy(id = "droppable")
    private WebElement droppable;

    public DroppablePage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public void moveDraggableElementToDroppableArea() {
        switchToIframe();
        actions
                .dragAndDrop(draggable, droppable)
                .perform();
        ;
    }

    public String getTextFromDroppable() {
        return droppable.getText();
    }

    private void switchToIframe() {
        driver.switchTo().frame(iframe);
    }
}

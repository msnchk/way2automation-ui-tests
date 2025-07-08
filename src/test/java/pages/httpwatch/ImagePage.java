package pages.httpwatch;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ImagePage extends BasePage {
    @FindBy(tagName = "img")
    private WebElement image;

    public ImagePage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public boolean isImageLoaded() {
        return (Boolean)((JavascriptExecutor) driver)
                .executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0", image);
    }
}

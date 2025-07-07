package pages.way2autojquery;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class FramesAndWindowsPage extends BasePage {
    public static final String FRAMES_AND_WINDOWS_URL = PropertyProvider.jqueryBaseUrl() + "frames-and-windows.php";

    @FindBy(className = "demo-frame")
    private WebElement iframe;

    @FindBy(xpath = "//a[text()='New Browser Tab']")
    private WebElement openNewTabText;

    public FramesAndWindowsPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public NewTabPage clickOpenNewTab() {
        switchToIframe(iframe);
        openNewTabText.click();
        return new NewTabPage(driver, actions);
    }
}

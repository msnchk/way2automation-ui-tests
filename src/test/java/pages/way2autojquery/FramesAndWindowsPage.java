package pages.way2autojquery;

import helpers.PropertyProvider;
import io.qameta.allure.Step;
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

    @Step("Click 'Open new tab' inside frame and switch on last tab")
    public NewTabPage clickOpenNewTabAndSwitch() {
        switchToIframe(iframe);
        openNewTabText.click();
        switchToLastTab();
        return new NewTabPage(driver, actions);
    }
}

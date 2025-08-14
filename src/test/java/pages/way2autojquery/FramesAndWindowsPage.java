package pages.way2autojquery;

import helpers.EnvKeys;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class FramesAndWindowsPage extends BasePage {
    public static final String FRAMES_AND_WINDOWS_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.JQUERY_FRAMES_PATH.get();

    @FindBy(className = "demo-frame")
    private WebElement iframe;

    @FindBy(xpath = "//a[text()='New Browser Tab']")
    private WebElement openNewTabText;

    public FramesAndWindowsPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Click 'Open new tab' inside frame and switch on last tab")
    public NewTabPage clickOpenNewTabAndSwitch() {
        elementHelper.switchToIframe(iframe);
        openNewTabText.click();
        elementHelper.switchToLastTab();
        return new NewTabPage(driver, actions);
    }
}

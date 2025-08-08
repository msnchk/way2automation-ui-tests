package pages.way2autojquery;

import helpers.EnvKeys;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class NewTabPage extends BasePage {
    public static final String NEW_TAB_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.JQUERY_NEW_TAB_PATH.get();

    @FindBy(xpath = "//a[text()='New Browser Tab']")
    private WebElement openNewTabText;

    public NewTabPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Click 'Open new tab' and switch on last tab")
    public NewTabPage clickOpenNewTabAndSwitch() {
        openNewTabText.click();
        switchToLastTab();
        return new NewTabPage(driver, actions);
    }
}

package pages.way2autojquery;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class NewTabPage extends BasePage {
    public static final String NEW_TAB_URL = PropertyProvider.jqueryBaseUrl() + "frames-windows/defult1.html#";

    @FindBy(xpath = "//a[text()='New Browser Tab']")
    private WebElement openNewTabText;

    public NewTabPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public NewTabPage clickOpenNewTab() {
        openNewTabText.click();
        return new NewTabPage(driver, actions);
    }
}

package pages.sqlex;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class SqlExMainPage extends BasePage {
    public static final String SQLEX_MAIN_PAGE_URL = PropertyProvider.sqlExBaseUrl();

    @FindBy(name = "login")
    private WebElement usernameInput;

    @FindBy(name = "psw")
    private WebElement passwordInput;

    @FindBy(name = "subm1")
    private WebElement submitButton;

    @FindBy(css = "a.none[href='/personal.php']")
    private WebElement confirmingLoginElement;

    public SqlExMainPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public void sqlExLogIn(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
        actions.pause(500).perform();
    }

    public boolean isConfirmingLoginElementDisplayed() {
        return confirmingLoginElement.isDisplayed();
    }
}

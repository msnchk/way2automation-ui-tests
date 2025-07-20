package pages.sqlex;

import helpers.CookiesHelper;
import helpers.PropertyProvider;
import io.qameta.allure.Step;
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

    @Step("Log in to SQLEx using login and password or saved cookies")
    public void sqlExAuthorize(String username, String password) {
        if (CookiesHelper.cookieFileExists()) {
            driver.manage().deleteAllCookies();
            CookiesHelper.loadCookiesFromFile(driver);
            driver.navigate().refresh();
        } else {
            sqlExLogIn(username, password);
            CookiesHelper.saveCookiesToFile(driver);
        }
    }

    @Step("Check if confirming successful login element is displayed")
    public boolean isConfirmingLoginElementDisplayed() {
        return confirmingLoginElement.isDisplayed();
    }

    private void sqlExLogIn(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
        actions.pause(500).perform();
    }
}

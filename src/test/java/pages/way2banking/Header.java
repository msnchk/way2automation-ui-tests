package pages.way2banking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class Header extends BasePage {
    @FindBy(css = "button[ng-click='home()']")
    private WebElement homeButton;

    public Header(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public BankingMainPage goHome() {
        homeButton.click();
        return new BankingMainPage(driver, actions);
    }
}

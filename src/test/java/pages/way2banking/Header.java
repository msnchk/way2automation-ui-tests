package pages.way2banking;

import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class Header extends BasePage {
    @FindBy(css = "button[ng-click='home()']")
    private WebElement homeButton;

    @FindBy(css = "strong.mainHeading")
    private WebElement mainHeading;

    public Header(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public BankingMainPage goHome() {
        clickWhenClickable(homeButton);
        return new BankingMainPage(driver, actions);
    }

    public void waitHeaderToBeVisibleAndClickable() {
        WaitHelper.waitForElementToBeVisible(driver, homeButton);
        WaitHelper.waitForElementToBeClickable(driver, homeButton);
    }
}

package pages.way2banking;

import io.qameta.allure.Step;
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

    @Step("Click Home button")
    public BankingMainPage goHome() {
        elementHelper.clickAfterPause(homeButton);
        return new BankingMainPage(driver, actions);
    }
}

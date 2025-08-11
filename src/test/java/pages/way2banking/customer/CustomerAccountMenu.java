package pages.way2banking.customer;

import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.way2banking.Header;

public class CustomerAccountMenu extends BasePage {
    private final Header header;

    @FindBy(css = "button[ng-click='transactions()']")
    private WebElement transactionsButton;

    @FindBy(css = "button[ng-click='deposit()']")
    private WebElement depositButton;

    @FindBy(css = "button[ng-click='withdrawl()']")
    private WebElement withDrawlButton;

    @FindBy(xpath = "//div[strong/span[@class='fontBig ng-binding']]")
    private WebElement welcomeDiv;

    public CustomerAccountMenu(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.header = new Header(driver, actions);
    }

    public TransactionsPage openTransactionsPage() {
        clickWhenClickable(transactionsButton);
        return new TransactionsPage(driver, actions);
    }

    public DepositPage openDepositPage() {
        clickWhenClickable(depositButton);
        return new DepositPage(driver, actions);
    }

    public WithdrawPage openWithdrawPage() {
        clickWhenClickable(withDrawlButton);
        return new WithdrawPage(driver, actions);
    }

    public String getWelcomeText() {
        WaitHelper.waitForElementToBeVisible(driver, welcomeDiv);
        return welcomeDiv.getText();
    }

    public Header getHeader() {
        return header;
    }
}

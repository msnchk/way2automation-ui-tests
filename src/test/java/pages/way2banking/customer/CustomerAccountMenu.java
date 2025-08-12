package pages.way2banking.customer;

import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.way2banking.Header;

import java.math.BigDecimal;

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

    @FindBy(xpath = "//div[contains(@class, 'center')]/strong[2]")
    private WebElement balance;

    public CustomerAccountMenu(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.header = new Header(driver, actions);
    }

    @Step("Open Transactions page")
    public TransactionsPage openTransactionsPage() {
        clickAfterPause(transactionsButton);
        return new TransactionsPage(driver, actions);
    }

    @Step("Open Deposit page")
    public DepositPage openDepositPage() {
        clickAfterPause(depositButton);
        return new DepositPage(driver, actions);
    }

    @Step("Open Withdraw page")
    public WithdrawPage openWithdrawPage() {
        clickAfterPause(withDrawlButton);
        return new WithdrawPage(driver, actions);
    }

    @Step("Get welcome text from the account menu")
    public String getWelcomeText() {
        WaitHelper.waitForElementToBeVisible(driver, welcomeDiv);
        return welcomeDiv.getText();
    }

    @Step("Get current account balance")
    public BigDecimal getBalance() {
        WaitHelper.waitForElementToBeVisible(driver, balance);
        WaitHelper.waitForElementToBeClickable(driver, balance);
        String text = balance.getText().trim();
        return new BigDecimal(text);
    }

    @Step("Turn to page header")
    public Header getHeader() {
        return header;
    }
}

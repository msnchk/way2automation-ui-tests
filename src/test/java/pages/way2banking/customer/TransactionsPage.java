package pages.way2banking.customer;

import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.way2banking.Header;

import java.math.BigDecimal;
import java.util.List;

public class TransactionsPage extends BasePage {
    private final Header header;

    @FindBy(css = "button[ng-click='back()']")
    private WebElement backButton;

    @FindBy(css = "button[ng-click='reset()']")
    private WebElement resetButton;

    @FindBy(xpath = "//table//tbody//tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//table//tbody//tr/td[2]")
    private List<WebElement> amountColumn;

    @FindBy(xpath = "//a[normalize-space()='Date-Time']")
    private WebElement dateTimeHeader;

    private By cell = By.tagName("td");

    public TransactionsPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.header = new Header(driver, actions);
    }

    @Step("Check if transaction with amount {desiredAmount} exists")
    public boolean isAmountExists(BigDecimal desiredAmount) {
        WaitHelper.waitForElementToBeVisible(driver, dateTimeHeader);
        WaitHelper.waitForElementToBeClickable(driver, dateTimeHeader);
        for(WebElement cell: amountColumn) {
            String amount = cell.getText().trim();
            if (desiredAmount.toString().equals(amount)) {
                return true;
            }
        }
        return false;
    }

    @Step("Get transaction type for amount {desiredAmount}")
    public String getTransactionTypeOfAmount(BigDecimal desiredAmount) {
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(cell);
            String amount = cells.get(1).getText().trim();
            if (desiredAmount.toString().equals(amount)) {
                return cells.get(2).getText().trim();
            }
        }
        return "";
    }

    @Step("Go back to Customer Account menu")
    public CustomerAccountMenu goBack() {
        clickAfterPause(backButton);
        return new CustomerAccountMenu(driver, actions);
    }

    @Step("Reset transactions list")
    public void resetTransactions() {
        clickAfterPause(resetButton);
    }

    @Step("Check if transactions table is empty")
    public boolean isTransactionsTableEmpty() {
        return tableRows.isEmpty();
    }

    @Step("Turn to page header")
    public Header getHeader() {
        return header;
    }
}

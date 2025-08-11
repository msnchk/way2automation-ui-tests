package pages.way2banking.customer;

import helpers.WaitHelper;
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
    private List<WebElement> amountCells;

    @FindBy(xpath = "//a[normalize-space()='Date-Time']")
    private WebElement dateTimeColumn;

    private By cell = By.tagName("td");

    public TransactionsPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.header = new Header(driver, actions);
    }

    public boolean isAmountExists(BigDecimal desiredAmount) {
        WaitHelper.waitForElementToBeVisible(driver, dateTimeColumn);
        WaitHelper.waitForElementToBeClickable(driver, dateTimeColumn);
        for(WebElement cell: amountCells) {
            String amount = cell.getText().trim();
            if (desiredAmount.toString().equals(amount)) {
                return true;
            }
        }
        return false;
    }

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

    public CustomerAccountMenu clickBack() {
        clickWhenClickable(backButton);
        return new CustomerAccountMenu(driver, actions);
    }

    public Header getHeader() {
        return header;
    }
}

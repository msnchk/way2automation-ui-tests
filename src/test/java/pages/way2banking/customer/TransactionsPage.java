package pages.way2banking.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class TransactionsPage extends BasePage {

    @FindBy(xpath = "//button[normalize-space(text())='Back']")
    private WebElement backButton;

    @FindBy(xpath = "//table//tbody//tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//table//tbody//tr/td[2]")
    private List<WebElement> amountCells;

    private By cell = By.tagName("td");

    public TransactionsPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public boolean isAmountExists(String desiredAmount) {
        for(WebElement cell: amountCells) {
            String amount = cell.getText().trim();
            if (desiredAmount.equals(amount)) {
                return true;
            }
        }
        return false;
    }

    public String getTransactionTypeByAmount(String desiredAmount) {
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(cell);
            String amount = cells.get(1).getText().trim();
            if (desiredAmount.equals(amount)) {
                return cells.get(2).getText().trim();
            }
        }
        return "";
    }
}

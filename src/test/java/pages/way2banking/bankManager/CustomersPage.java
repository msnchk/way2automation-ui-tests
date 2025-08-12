package pages.way2banking.bankManager;

import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class CustomersPage extends BasePage {
    private final BankManagerMenu bankManagerMenu;

    @FindBy(xpath = "//table//tbody//tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//table//tbody//tr/td[1]")
    private List<WebElement> firstNameColumn;

    @FindBy(xpath = "//table//tbody//tr/td[2]")
    private List<WebElement> lastNameColumn;

    @FindBy(xpath = "//a[normalize-space()='First Name']")
    private WebElement firstNameHeader;

    private By deleteButtonLocator = By.xpath(".//button[@ng-click='deleteCust(cust)']");

    private By cell = By.tagName("td");

    public CustomersPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.bankManagerMenu = new BankManagerMenu(driver, actions);
    }

    @Step("Delete customer with First Name: {desiredFirstName} and Last Name: {desiredLastName}")
    public void deleteCustomer(String desiredFirstName, String desiredLastName) {
        WebElement row = findCustomerRow(desiredFirstName, desiredLastName);
        if (row == null) {
            throw new NoSuchElementException(
                    String.format("Customer '%s %s' not found in the table", desiredFirstName, desiredLastName)
            );
        }
        row.findElement(deleteButtonLocator).click();
    }

    @Step("Check if customer exists with First Name: {desiredFirstName} and Last Name: {desiredLastName}")
    public boolean isCustomerExists(String desiredFirstName, String desiredLastName) {
        return findCustomerRow(desiredFirstName, desiredLastName) != null;
    }

    @Step("Turn to Bank Manager menu")
    public BankManagerMenu getBankManagerMenu() {
        return bankManagerMenu;
    }

    private WebElement findCustomerRow(String desiredFirstName, String desiredLastName) {
        WaitHelper.waitForElementToBeVisible(driver, firstNameHeader);
        WaitHelper.waitForElementToBeClickable(driver, firstNameHeader);
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(cell);
            String firstName = cells.get(0).getText().trim();
            String lastName = cells.get(1).getText().trim();
            if (desiredFirstName.equals(firstName) && desiredLastName.equals(lastName)) {
                return row;
            }
        }
        return null;
    }
}

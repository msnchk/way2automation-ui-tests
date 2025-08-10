package pages.way2banking.bankManager;

import data.way2banking.CustomerData;
import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class OpenAccountPage extends BasePage {
    private final BankManagerMenu bankManagerMenu;

    @FindBy(name = "userSelect")
    private WebElement userSelect;

    @FindBy(name = "currency")
    private WebElement currencySelect;

    @FindBy(css = "button[type='submit']")
    private WebElement processButton;

    public OpenAccountPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.bankManagerMenu = new BankManagerMenu(driver, actions);
    }

    public void openAccountForCustomer(CustomerData customer, String currency) {
        WaitHelper.waitForElementToBeVisible(driver, userSelect);
        selectDropdownByText(userSelect, customer.getFirstName() + " " + customer.getLastName());
        selectDropdownByText(currencySelect,currency);
        processButton.click();
    }

    public BankManagerMenu getBankManagerMenu() {
        return bankManagerMenu;
    }
}

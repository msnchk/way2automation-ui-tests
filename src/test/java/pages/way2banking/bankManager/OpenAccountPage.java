package pages.way2banking.bankManager;

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

    public void openAccountForCustomer(String firstName, String lastName, String currency) {
        WaitHelper.waitForElementToBeVisible(driver, userSelect);
        selectDropdownByText(userSelect, firstName + " " + lastName);
        selectDropdownByText(currencySelect, currency);
        processButton.click();
    }

    public BankManagerMenu getBankManagerMenu() {
        return bankManagerMenu;
    }
}

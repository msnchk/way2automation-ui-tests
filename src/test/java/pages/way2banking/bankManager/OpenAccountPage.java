package pages.way2banking.bankManager;

import helpers.WaitHelper;
import io.qameta.allure.Step;
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

    @Step("Open account for customer: {firstName} {lastName} with currency: {currency}")
    public void openAccountForCustomer(String firstName, String lastName, String currency) {
        WaitHelper.waitForElementToBeVisibleAndClickable(driver, userSelect);
        elementHelper.selectDropdownByText(userSelect, firstName + " " + lastName);
        elementHelper.selectDropdownByText(currencySelect, currency);
        processButton.click();
    }

    @Step("Turn to Bank Manager Menu")
    public BankManagerMenu getBankManagerMenu() {
        return bankManagerMenu;
    }
}

package pages.way2banking.customer;

import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.math.BigDecimal;

public class DepositPage extends BasePage {
    private final CustomerAccountMenu customerAccountMenu;

    @FindBy(xpath = "//input[@type='number' and @placeholder='amount']")
    private WebElement amountInput;

    @FindBy(xpath = "//button[@type='submit' and normalize-space(text())='Deposit']")
    private WebElement depositButton;

    @FindBy(xpath = "//span[@ng-show='message']")
    private WebElement message;

    public DepositPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.customerAccountMenu = new CustomerAccountMenu(driver, actions);
    }

    public void depositAmount(BigDecimal amount) {
        WaitHelper.waitForElementToBeVisible(driver, amountInput);
        amountInput.sendKeys(amount.toString());
        clickWhenClickable(depositButton);
    }

    public String getMessageText() {
        return message.getText();
    }

    public CustomerAccountMenu getCustomerAccountMenu() {
        return customerAccountMenu;
    }

    public void waitForMessage() {
        WaitHelper.waitForElementToBeVisible(driver, message);
        WaitHelper.waitForElementToBeClickable(driver, message);
    }
}

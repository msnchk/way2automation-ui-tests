package pages.way2banking.customer;

import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.math.BigDecimal;

public class DepositPage extends BasePage {
    private final CustomerAccountMenu customerAccountMenu;

    @FindBy(xpath = "//label[normalize-space(.)='Amount to be Deposited :']/following-sibling::input")
    private WebElement amountInput;

    @FindBy(xpath = "//button[@type='submit' and normalize-space(text())='Deposit']")
    private WebElement depositButton;

    @FindBy(xpath = "//form[@ng-submit='deposit()']//preceding-sibling::span[@ng-show='message']")
    private WebElement message;

    public DepositPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.customerAccountMenu = new CustomerAccountMenu(driver, actions);
    }

    @Step("Deposit amount: {amount}")
    public void depositAmount(BigDecimal amount) {
        WaitHelper.waitForElementToBeVisibleAndClickable(driver, amountInput);
        amountInput.sendKeys(amount.toString());
        elementHelper.clickAfterPause(depositButton);
    }

    @Step("Get message text after deposit")
    public String getMessageText() {
        return message.getText();
    }

    @Step("Turn to Customer Account Menu page")
    public CustomerAccountMenu getCustomerAccountMenu() {
        return customerAccountMenu;
    }
}

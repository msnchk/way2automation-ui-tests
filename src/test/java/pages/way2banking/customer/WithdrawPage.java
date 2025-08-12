package pages.way2banking.customer;

import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.math.BigDecimal;

public class WithdrawPage extends BasePage {
    private final CustomerAccountMenu customerAccountMenu;

    @FindBy(xpath = "//label[normalize-space(.)='Amount to be Withdrawn :']/following-sibling::input")
    private WebElement amountInput;

    @FindBy(xpath = "//button[@type='submit' and normalize-space(text())='Withdraw']")
    private WebElement withdrawButton;

    @FindBy(xpath = "//form[@ng-submit='withdrawl()']//preceding-sibling::span[@ng-show='message']")
    private WebElement message;

    public WithdrawPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.customerAccountMenu = new CustomerAccountMenu(driver, actions);
    }

    @Step("Withdraw amount: {amount}")
    public void withdrawAmount(BigDecimal amount) {
        WaitHelper.waitForElementToBeVisible(driver, amountInput);
        amountInput.sendKeys(amount.toString());
        clickAfterPause(withdrawButton);
    }

    @Step("Get confirmation message text after withdrawal")
    public String getMessageText() {
        return message.getText();
    }

    @Step("Turn to Customer Account Menu page")
    public CustomerAccountMenu getCustomerAccountMenu() {
        return customerAccountMenu;
    }
}

package pages.way2banking.customer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class DepositPage extends BasePage {
    private final CustomerAccountMenu customerAccountMenu;

    @FindBy(xpath = "//input[@type='number' and @placeholder='amount']")
    private WebElement amountInput;

    @FindBy(xpath = "//button[normalize-space(text())='Deposit']")
    private WebElement depositButton;

    @FindBy(xpath = "//span[@ng-show='message']")
    private WebElement message;

    public DepositPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.customerAccountMenu = new CustomerAccountMenu(driver, actions);
    }

    public void depositAmount(String amount) {
        amountInput.sendKeys(amount);
        depositButton.click();
    }

    public String getMessageText() {
        return message.getText();
    }
}

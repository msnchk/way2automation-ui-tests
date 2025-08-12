package pages.way2banking.bankManager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.way2banking.Header;

public class BankManagerMenu extends BasePage {
    private final Header header;

    @FindBy(css = "button[ng-click='addCust()']")
    private WebElement addCustomerButton;

    @FindBy(css = "button[ng-click='openAccount()']")
    private WebElement openAccountButton;

    @FindBy(css = "button[ng-click='showCust()']")
    private WebElement customersButton;

    public BankManagerMenu(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.header = new Header(driver, actions);
    }

    @Step("Open Add Customer page")
    public AddCustomerPage openAddCustomerPage() {
        clickAfterPause(addCustomerButton);
        return new AddCustomerPage(driver, actions);
    }

    @Step("Open Open Account page")
    public OpenAccountPage openAccountPage() {
        clickAfterPause(openAccountButton);
        return new OpenAccountPage(driver, actions);
    }

    @Step("Open Customers page")
    public CustomersPage openCustomersPage() {
        clickAfterPause(customersButton);
        return new CustomersPage(driver, actions);
    }

    @Step("Turn to page header")
    public Header getHeader() {
        return header;
    }
}

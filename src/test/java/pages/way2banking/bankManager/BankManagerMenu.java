package pages.way2banking.bankManager;

import helpers.EnvKeys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.way2banking.Header;

public class BankManagerMenu extends BasePage {
    public static final String BANK_MANAGER_PAGE_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.BANK_MANAGER_PAGE_PATH.get();

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

    public AddCustomerPage openAddCustomerPage() {
        clickWhenClickable(addCustomerButton);
        return new AddCustomerPage(driver, actions);
    }

    public OpenAccountPage openAccountPage() {
        clickWhenClickable(openAccountButton);
        return new OpenAccountPage(driver, actions);
    }

    public CustomersPage openCustomersPage() {
        clickWhenClickable(customersButton);
        return new CustomersPage(driver, actions);
    }

    public Header getHeader() {
        return header;
    }
}

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

    @FindBy(xpath = "//button[contains(@class, 'btn') and normalize-space(text())='Add Customer']")
    private WebElement addCustomerButton;

    @FindBy(xpath = "//button[contains(@class, 'btn') and normalize-space(text())='Open Account']")
    private WebElement openAccountButton;

    @FindBy(xpath = "//button[contains(@class, 'btn') and normalize-space(text())='Customers']")
    private WebElement customersButton;

    public BankManagerMenu(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.header = new Header(driver, actions);
    }

    public AddCustomerPage openAddCustomerPage() {
        clickWhenClickable(addCustomerButton);
        return new AddCustomerPage(driver, actions);
    }

    public OpenAccountPage openOpenAccountPage() {
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

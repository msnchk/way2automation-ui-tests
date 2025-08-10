package pages.way2banking.customer;

import data.way2banking.CustomerData;
import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.way2banking.Header;

public class CustomerLoginPage extends BasePage {
    private final Header header;

    @FindBy(name = "userSelect")
    private WebElement userSelect;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    public CustomerLoginPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.header = new Header(driver, actions);
    }

    public WelcomePage selectCustomerNameWithNoAccountsAndLogin(CustomerData customer) {
        login(customer);
        return new WelcomePage(driver, actions);
    }

    public CustomerAccountMenu selectCustomerNameAndLogin(CustomerData customer) {
        login(customer);
        return new CustomerAccountMenu(driver, actions);
    }

    private void login(CustomerData customer) {
        WaitHelper.waitForElementToBeVisible(driver, userSelect);
        selectDropdownByText(userSelect, customer.getFirstName() + " " + customer.getLastName());
        loginButton.click();
    }
}

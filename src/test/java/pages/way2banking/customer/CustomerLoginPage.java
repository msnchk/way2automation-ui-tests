package pages.way2banking.customer;

import helpers.WaitHelper;
import io.qameta.allure.Step;
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

    @Step("Select customer '{firstName} {lastName}' and perform login")
    public CustomerAccountMenu selectCustomerNameAndLogin(String firstName, String lastName) {
        WaitHelper.waitForElementToBeVisible(driver, userSelect);
        selectDropdownByText(userSelect, firstName + " " + lastName);
        clickAfterPause(loginButton);
        return new CustomerAccountMenu(driver, actions);
    }
}

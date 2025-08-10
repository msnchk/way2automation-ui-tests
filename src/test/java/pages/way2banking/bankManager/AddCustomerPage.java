package pages.way2banking.bankManager;

import data.way2banking.CustomerData;
import helpers.EnvKeys;
import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AddCustomerPage extends BasePage {
    public static final String ADD_CUSTOMER_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.BANK_MANAGER_ADD_CUST_PATH.get();

    private final BankManagerMenu bankManagerMenu;

    @FindBy(xpath = "//input[@ng-model='fName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@ng-model='lName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@ng-model='postCd']")
    private WebElement postCodeInput;

    @FindBy(xpath = "//button[@type='submit' and normalize-space(text())='Add Customer']")
    private WebElement addButton;

    public AddCustomerPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.bankManagerMenu = new BankManagerMenu(driver, actions);
    }

    public void fillFormWithCustomerData(CustomerData customerData) {
        WaitHelper.waitForElementToBeVisible(driver, firstNameInput);
        firstNameInput.sendKeys(customerData.getFirstName());
        lastNameInput.sendKeys(customerData.getLastName());
        postCodeInput.sendKeys(customerData.getPostCode());
        addButton.click();
    }

    public BankManagerMenu getBankManagerMenu() {
        return bankManagerMenu;
    }
}

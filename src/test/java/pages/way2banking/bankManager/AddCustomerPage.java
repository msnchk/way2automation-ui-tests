package pages.way2banking.bankManager;

import helpers.EnvKeys;
import helpers.WaitHelper;
import io.qameta.allure.Step;
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

    @Step("Fill the customer form with First Name: {firstName}, Last Name: {lastName}, Post Code: {postCode} and submit")
    public void fillFormWithCustomerData(String firstName, String lastName, String postCode) {
        WaitHelper.waitForElementToBeVisible(driver, firstNameInput);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postCodeInput.sendKeys(postCode);
        addButton.click();
    }

    @Step("Turn to Bank Manager menu")
    public BankManagerMenu getBankManagerMenu() {
        return bankManagerMenu;
    }
}

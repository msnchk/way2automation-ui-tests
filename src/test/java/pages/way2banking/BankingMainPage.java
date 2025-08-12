package pages.way2banking;

import helpers.EnvKeys;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.way2banking.bankManager.BankManagerMenu;
import pages.way2banking.customer.CustomerLoginPage;
import pages.way2banking.sampleform.SampleFormPage;

public class BankingMainPage extends BasePage {
    public static final String BANKING_MAIN_PAGE_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.BANKING_MAIN_PAGE_PATH.get();

    @FindBy(xpath = "//a[contains(@class, 'btn') and normalize-space(text())='Sample Form']")
    private WebElement sampleFormLink;

    @FindBy(xpath = "//button[contains(@class, 'btn') and normalize-space(text())='Customer Login']")
    private WebElement customerLoginButton;

    @FindBy(xpath = "//button[contains(@class, 'btn') and normalize-space(text())='Bank Manager Login']")
    private WebElement bankManagerButton;

    public BankingMainPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Open Sample Form page")
    public SampleFormPage openSampleFormPage() {
        clickAfterPause(sampleFormLink);
        return new SampleFormPage(driver, actions);
    }

    @Step("Open Customer Login page")
    public CustomerLoginPage openCustomerLoginPage() {
        clickAfterPause(customerLoginButton);
        return new CustomerLoginPage(driver, actions);
    }

    @Step("Open Bank Manager menu")
    public BankManagerMenu openBankManagerMenu() {
        clickAfterPause(bankManagerButton);
        return new BankManagerMenu(driver, actions);
    }
}

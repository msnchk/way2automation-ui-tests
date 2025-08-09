package pages.way2banking;

import helpers.EnvKeys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class BankingMainPage extends BasePage {
    public static final String BANKING_MAIN_PAGE_URL =
            EnvKeys.WAY2_BASE_URL.get() + EnvKeys.BANKING_MAIN_PAGE_PATH.get();

    @FindBy(xpath = "//a[contains(@class, 'btn') and normalize-space(text())='Sample Form']")
    private WebElement sampleFormLink;

    @FindBy(xpath = "//a[contains(@class, 'btn') and normalize-space(text())='Customer Login']")
    private WebElement customerLoginLink;

    @FindBy(xpath = "//a[contains(@class, 'btn') and normalize-space(text())='Bank Manager']")
    private WebElement bankManagerLink;

    public BankingMainPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public SampleFormPage openSampleFormPage() {
        clickWhenClickable(sampleFormLink);
        return new SampleFormPage(driver, actions);
    }

    public CustomerLoginPage openCustomerLoginPage() {
        clickWhenClickable(customerLoginLink);
        return new CustomerLoginPage(driver, actions);
    }

    public BankManagerPage openBankManagerPage() {
        clickWhenClickable(bankManagerLink);
        return new BankManagerPage(driver, actions);
    }
}

package tests.way2banking;

import data.way2banking.CustomerData;
import data.way2banking.CustomerDataProvider;
import data.way2banking.SampleFormData;
import data.way2banking.SampleFormDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.bankManager.AddCustomerPage;
import pages.way2banking.bankManager.BankManagerMenu;
import pages.way2banking.BankingMainPage;
import pages.way2banking.SampleFormPage;
import tests.BaseTest;

public class BankingUITest extends BaseTest {
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void openBankingMainPage() {
        getDriver().get(BankingMainPage.BANKING_MAIN_PAGE_URL);
        bankingMainPage = new BankingMainPage(getDriver(), getActions());
    }

    @Test(
            dataProvider = "sampleData",
            dataProviderClass = SampleFormDataProvider.class
    )
    public void shouldTestSampleFormWithValidData(SampleFormData sampleFormData) {
        SampleFormPage sampleFormPage = bankingMainPage.openSampleFormPage();
        sampleFormPage.fillFormWithUserData(sampleFormData);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                sampleFormPage.isSuccessMessageDisplayed(),
                "Success message isn't displayed");
        softAssert.assertEquals(
                sampleFormPage.getSuccessMessageText(),
                "User registered successfully!",
                "Success message doesn't match expected"
        );
        softAssert.assertAll();
    }

    @Test(
            dataProvider = "customerData",
            dataProviderClass = CustomerDataProvider.class
    )
    public void shouldAddCustomerWithValidData(CustomerData customerData) {
        BankManagerMenu bankManagerMenu = bankingMainPage.openBankManagerMenu();
        AddCustomerPage addCustomerPage = bankManagerMenu.openAddCustomerPage();
        addCustomerPage.fillFormWithCustomerData(customerData);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                addCustomerPage.isAlertPresent(),
                "Alert didn't appear");
        softAssert.assertTrue(
                addCustomerPage.getAlertTextIfExistsAndAccept().contains("Customer added successfully with customer id"),
                "Alert text doesn't match expected"
        );
        softAssert.assertAll();
    }
}

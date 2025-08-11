package tests.way2banking;

import data.way2banking.customer.CustomerData;
import data.way2banking.customer.CustomerDataProvider;
import data.way2banking.sampleform.SampleFormData;
import data.way2banking.sampleform.SampleFormDataProvider;
import helpers.BankingPreconditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.bankManager.AddCustomerPage;
import pages.way2banking.bankManager.BankManagerMenu;
import pages.way2banking.BankingMainPage;
import pages.way2banking.sampleform.SampleFormPage;

public class BankingUITest extends BankingPreconditions {
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void setUp() {
        getDriver().get(BankingMainPage.BANKING_MAIN_PAGE_URL);
        bankingMainPage = new BankingMainPage(getDriver(), getActions());
    }

    @Test(dataProvider = "sampleData", dataProviderClass = SampleFormDataProvider.class
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

    @Test(dataProvider = "customerData", dataProviderClass = CustomerDataProvider.class
    )
    public void shouldAddCustomerWithValidData(CustomerData customerData) {
        BankManagerMenu bankManagerMenu = bankingMainPage.openBankManagerMenu();
        AddCustomerPage addCustomerPage = bankManagerMenu.openAddCustomerPage();
        addCustomerPage.fillFormWithCustomerData(customerData.getFirstName(), customerData.getLastName(), customerData.getPostCode());

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

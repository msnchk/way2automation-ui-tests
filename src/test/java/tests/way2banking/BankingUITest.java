package tests.way2banking;

import data.way2banking.customer.CustomerData;
import data.way2banking.customer.CustomerDataProvider;
import data.way2banking.sampleform.SampleFormData;
import data.way2banking.sampleform.SampleFormDataProvider;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.bankManager.AddCustomerPage;
import pages.way2banking.BankingMainPage;
import pages.way2banking.sampleform.SampleFormPage;

@Epic("Way2Banking UI")
public class BankingUITest extends BankingPreconditions {
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void setUp() {
        getDriver().get(BankingMainPage.BANKING_MAIN_PAGE_URL);
        bankingMainPage = new BankingMainPage(getDriver(), getActions());
    }

    @Test(
            description = "Verify that user can successfully submit Sample Form with valid data",
            dataProvider = "sampleData",
            dataProviderClass = SampleFormDataProvider.class
    )
    @Feature("Sample Form")
    @Story("User fills form with valid data and sees success message")
    @Severity(SeverityLevel.NORMAL)
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
            description = "Verify that Bank Manager can add a new customer with valid data",
            dataProvider = "customerData",
            dataProviderClass = CustomerDataProvider.class)
    @Feature("Bank Manager")
    @Story("Manager adds customer with valid data")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldAddCustomerWithValidData(CustomerData customerData) {
        AddCustomerPage addCustomerPage = bankingMainPage
                .openBankManagerMenu()
                .openAddCustomerPage();
        addCustomerPage.fillFormWithCustomerData(customerData.getFirstName(), customerData.getLastName(), customerData.getPostCode());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                addCustomerPage.getAlertHelper().isAlertPresent(),
                "Alert didn't appear");
        softAssert.assertTrue(
                addCustomerPage.getAlertHelper().getAlertTextIfExists().contains("Customer added successfully with customer id"),
                "Alert text doesn't match expected"
        );
        softAssert.assertAll();
    }
}

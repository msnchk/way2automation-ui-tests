package tests.way2banking;

import data.way2banking.customer.CustomerData;
import helpers.BankingPreconditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.BankingMainPage;
import pages.way2banking.bankManager.AddCustomerPage;
import pages.way2banking.bankManager.OpenAccountPage;
import tests.BaseTest;

public class BankingUITestWithCustomer extends BankingPreconditions {
    private final CustomerData customer = CustomerData.builder().build();
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void setUp() {
        bankingMainPage = createCustomer(customer);
    }

//    @BeforeMethod
//    public void createCustomer() {
//        getDriver().get(AddCustomerPage.ADD_CUSTOMER_URL);
//        AddCustomerPage addCustomerPage = new AddCustomerPage(getDriver(), getActions());
//        addCustomerPage.fillFormWithCustomerData(customer.getFirstName(), customer.getLastName(), customer.getPostCode());
//        addCustomerPage.acceptAlert();
//        bankingMainPage = addCustomerPage
//                .getBankManagerMenu()
//                .getHeader()
//                .goHome();
//    }

    @Test
    public void shouldOpenAccountForCustomer() {
        OpenAccountPage openAccountPage = bankingMainPage
                .openBankManagerMenu()
                .openAccountPage();
        openAccountPage.openAccountForCustomer(customer.getFirstName(), customer.getLastName(), "Dollar");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                openAccountPage.isAlertPresent(),
                "Alert didn't appear");
        softAssert.assertTrue(
                openAccountPage.getAlertTextIfExistsAndAccept().contains("Account created successfully with account Number"),
                "Alert text doesn't match expected"
        );
        softAssert.assertAll();
    }
}

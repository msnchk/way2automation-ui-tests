package tests.way2banking;

import data.way2banking.CustomerData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.BankingMainPage;
import pages.way2banking.bankManager.AddCustomerPage;
import pages.way2banking.bankManager.OpenAccountPage;
import pages.way2banking.customer.CustomerAccountMenu;
import tests.BaseTest;

public class BankingUITestWithCustomer extends BaseTest {
    private final CustomerData customer = CustomerData.builder().build();
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void createCustomer() {
        getDriver().get(AddCustomerPage.ADD_CUSTOMER_URL);
        AddCustomerPage addCustomerPage = new AddCustomerPage(getDriver(), getActions());
        addCustomerPage.fillFormWithCustomerData(customer);
        addCustomerPage.acceptAlert();
        bankingMainPage = addCustomerPage.getBankManagerMenu().getHeader().goHome();
    }

    @Test
    public void shouldOpenAccountForCustomer() {
        OpenAccountPage openAccountPage = bankingMainPage
                .openBankManagerMenu()
                .openOpenAccountPage();
        openAccountPage.openAccountForCustomer(customer, "Dollar");

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

    @Test
    public void shouldTestCustomerLogin() {
        CustomerAccountMenu customerAccountMenu = bankingMainPage
                .openCustomerLoginPage()
                .selectCustomerNameAndLogin(customer);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(
                customerAccountMenu
                        .getWelcomeText()
                        .contains("Welcome " + customer.getFirstName() + " " + customer.getLastName() + " !!")
        );

//        DepositPage depositPage = customerLoginPage
//                .selectCustomerNameAndLogin(customer)
//                .openDepositPage();
//        depositPage.depositAmount("155");
//
//
//        softAssert.assertEquals(
//                depositPage.getMessageText(),
//                "Deposit Successful",
//                "Message text doesn't match"
//                );
        softAssert.assertAll();
    }
}

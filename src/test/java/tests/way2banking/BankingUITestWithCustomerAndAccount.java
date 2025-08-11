package tests.way2banking;

import data.way2banking.customer.CustomerData;
import helpers.BankingPreconditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.BankingMainPage;
import pages.way2banking.bankManager.AddCustomerPage;
import pages.way2banking.bankManager.OpenAccountPage;
import pages.way2banking.customer.CustomerAccountMenu;
import tests.BaseTest;

public class BankingUITestWithCustomerAndAccount extends BankingPreconditions {
    private final CustomerData customer = CustomerData.builder().build();
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void setUp() {
        bankingMainPage = createCustomerWithAccount(customer);
    }

//    @BeforeMethod
//    public void createCustomerAndAccount() {
//        getDriver().get(AddCustomerPage.ADD_CUSTOMER_URL);
//        AddCustomerPage addCustomerPage = new AddCustomerPage(getDriver(), getActions());
//        addCustomerPage.fillFormWithCustomerData(customer.getFirstName(), customer.getLastName(), customer.getPostCode());
//        addCustomerPage.acceptAlert();
//        OpenAccountPage openAccountPage = addCustomerPage
//                .getBankManagerMenu()
//                .openAccountPage();
//        openAccountPage.openAccountForCustomer(customer.getFirstName(), customer.getLastName(), "Dollar");
//        openAccountPage.acceptAlert();
//        bankingMainPage = openAccountPage
//                .getBankManagerMenu()
//                .getHeader()
//                .goHome();
//    }

    @Test
    public void shouldTestCustomerAccountLogin() {
        CustomerAccountMenu customerAccountMenu = bankingMainPage
                .openCustomerLoginPage()
                .selectCustomerNameAndLogin(customer.getFirstName(), customer.getLastName());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                customerAccountMenu
                        .getWelcomeText()
                        .contains("Welcome " + customer.getFirstName() + " " + customer.getLastName() + " !!")
        );
        softAssert.assertAll();
    }
}

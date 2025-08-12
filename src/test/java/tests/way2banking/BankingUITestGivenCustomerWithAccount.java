package tests.way2banking;

import data.way2banking.customer.CustomerData;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.BankingMainPage;
import pages.way2banking.bankManager.CustomersPage;
import pages.way2banking.customer.CustomerAccountMenu;

@Epic("Way2Banking UI")
public class BankingUITestGivenCustomerWithAccount extends BankingPreconditions {
    private final CustomerData customer = CustomerData.builder().build();
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void setUp() {
        bankingMainPage = createCustomerWithAccount(customer)
                .getHeader()
                .goHome();
    }

    @Test(description = "Verify that customer can log into account successfully")
    @Feature("Customer")
    @Story("Customer logs into account after account creation")
    @Severity(SeverityLevel.CRITICAL)
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

    @Test(description = "Verify that Bank Manager can delete a customer who has an account")
    @Feature("Bank Manager")
    @Story("Manager deletes customer with existing account and verify absence in customer list")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldDeleteCustomerWhoHasAccount() {
        CustomersPage customersPage = bankingMainPage
                .openBankManagerMenu()
                .openCustomersPage();
        customersPage.deleteCustomer(customer.getFirstName(), customer.getLastName());
        Assert.assertFalse(
                customersPage.isCustomerExists(customer.getFirstName(), customer.getLastName()),
                "Customer wasn't deleted");
    }
}

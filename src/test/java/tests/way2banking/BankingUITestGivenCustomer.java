package tests.way2banking;

import data.way2banking.customer.CustomerData;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.BankingMainPage;
import pages.way2banking.bankManager.OpenAccountPage;

@Epic("Way2Banking UI")
public class BankingUITestGivenCustomer extends BankingPreconditions {
    private final CustomerData customer = CustomerData.builder().build();
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void setUp() {
        bankingMainPage = createCustomer(customer)
                .getHeader()
                .goHome();
    }

    @Test(description = "Verify that Manager can open an account for an existing customer")
    @Feature("Bank Manager")
    @Story("Manager opens account for an existing customer")
    @Severity(SeverityLevel.CRITICAL)
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
                openAccountPage.getAlertTextIfExists().contains("Account created successfully with account Number"),
                "Alert text doesn't match expected"
        );
        softAssert.assertAll();
    }
}

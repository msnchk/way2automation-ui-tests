package tests.way2banking;


import data.way2banking.customer.CustomerData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.BankingMainPage;
import pages.way2banking.customer.CustomerAccountMenu;
import pages.way2banking.customer.TransactionsPage;
import pages.way2banking.customer.WithdrawPage;

import java.math.BigDecimal;

public class BankingUITestGivenCustomerWithAccountAndTransactions extends BankingPreconditions {
    private final CustomerData customer = CustomerData.builder().build();
    private BankingMainPage bankingMainPage;
    private CustomerAccountMenu customerAccountMenu;

    @BeforeMethod
    public void setUp() {
        bankingMainPage = createCustomerAccountWithDepositAndWithdraw(customer)
                .getHeader()
                .goHome();
        customerAccountMenu = bankingMainPage
                .openCustomerLoginPage()
                .selectCustomerNameAndLogin(customer.getFirstName(), customer.getLastName());
    }

    @Test(description = "Verify that the balance displayed matches the customer's expected balance")
    @Feature("Balance")
    @Story("Customer checks account balance")
    @Severity(SeverityLevel.NORMAL)
    public void shouldTestCustomerBalance() {
        Assert.assertEquals(
                customerAccountMenu.getBalance(),
                customer.getBalance(),
                "Balance doesn't match"
                );
    }

    @Test(description = "Verify that a customer can withdraw all funds until balance reaches zero")
    @Feature("Withdrawal")
    @Story("Customer withdraws all available funds")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldWithdrawAllCustomerMoney() {
        BigDecimal startBalance = customerAccountMenu.getBalance();
        WithdrawPage withdrawPage = customerAccountMenu.openWithdrawPage();
        withdrawPage.withdrawAmount(startBalance);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                withdrawPage.getMessageText(),
                "Transaction successful",
                "Message text doesn't match"
        );
        softAssert.assertEquals(
                withdrawPage.getCustomerAccountMenu().getBalance(),
                BigDecimal.ZERO,
                "Balance should be 0"
        );
        softAssert.assertAll();
    }

    @Test(description = "Verify that customer can reset transaction history and balance is updated")
    @Feature("Transactions")
    @Story("Customer resets transaction history")
    @Severity(SeverityLevel.NORMAL)
    public void shouldResetCustomerTransactions() {
        TransactionsPage transactionsPage = customerAccountMenu.openTransactionsPage();
        transactionsPage.resetTransactions();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(transactionsPage.isTransactionsTableEmpty());
        customerAccountMenu = transactionsPage.goBack();

        softAssert.assertEquals(
               customerAccountMenu.getBalance(),
               BigDecimal.ZERO,
                "Balance should be 0"
        );
        softAssert.assertAll();
    }
}

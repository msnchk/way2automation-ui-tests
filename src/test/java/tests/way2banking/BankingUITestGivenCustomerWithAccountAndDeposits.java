package tests.way2banking;

import data.way2banking.customer.*;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.BankingMainPage;
import pages.way2banking.customer.DepositPage;
import pages.way2banking.customer.TransactionsPage;
import pages.way2banking.customer.WithdrawPage;

@Epic("Way2Banking UI")
public class BankingUITestGivenCustomerWithAccountAndDeposits extends BankingPreconditions {
    private final CustomerData customer = CustomerData.builder().build();
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void setUp() {
        bankingMainPage = createCustomerAccountWithDeposit(customer)
                .getHeader()
                .goHome();
    }

    @Test(
            description = "Verify deposit functionality for valid and invalid amounts",
            dataProvider = "depositData",
            dataProviderClass = DepositDataProvider.class)
    @Feature("Deposit")
    @Story("Customer deposits funds")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldTestDepositWithValidAndInvalidData(DepositData depositData) {
        DepositPage depositPage = bankingMainPage
                .openCustomerLoginPage()
                .selectCustomerNameAndLogin(customer.getFirstName(), customer.getLastName())
                .openDepositPage();
        depositPage.depositAmount(depositData.getAmount());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                depositPage.getMessageText(),
                depositData.getExpectedMessageAfterDeposit(),
                "Message text doesn't match"
        );
        TransactionsPage transactionsPage = depositPage
                .getCustomerAccountMenu()
                .openTransactionsPage();

        softAssert.assertEquals(
                transactionsPage.isAmountExists(depositData.getAmount()),
                depositData.getShouldTransactionExist().booleanValue(),
                "Unexpected presence or absence of a transaction");
        softAssert.assertEquals(
                transactionsPage.getTransactionTypeOfAmount(depositData.getAmount()),
                depositData.getExpectedTransactionType(),
                "Unexpected transaction type");
        softAssert.assertAll();
    }

    @Test(
            dataProvider = "withdrawData",
            dataProviderClass = WithdrawDataProvider.class,
            description = "Verify withdrawal functionality for valid and invalid amounts")
    @Feature("Withdrawal")
    @Story("Customer withdraws funds with various scenarios")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldTestWithdrawWithValidAndInvalidData(WithdrawData withdrawData) {
        WithdrawPage withdrawPage = bankingMainPage
                .openCustomerLoginPage()
                .selectCustomerNameAndLogin(customer.getFirstName(), customer.getLastName())
                .openWithdrawPage();
        withdrawPage.withdrawAmount(withdrawData.getAmount());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                withdrawPage.getMessageText(),
                withdrawData.getExpectedMessageAfterWithdraw(),
                "Message text doesn't match"
        );
        TransactionsPage transactionsPage = withdrawPage
                .getCustomerAccountMenu()
                .openTransactionsPage();

        softAssert.assertEquals(
                transactionsPage.isAmountExists(withdrawData.getAmount()),
                withdrawData.getShouldTransactionExist().booleanValue(),
                "Unexpected presence or absence of a transaction");
        softAssert.assertEquals(
                transactionsPage.getTransactionTypeOfAmount(withdrawData.getAmount()),
                withdrawData.getExpectedTransactionType(),
                "Unexpected transaction type");
        softAssert.assertAll();
    }
}

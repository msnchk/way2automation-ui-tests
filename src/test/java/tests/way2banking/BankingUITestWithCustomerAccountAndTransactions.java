package tests.way2banking;

import data.way2banking.customer.*;
import helpers.BankingPreconditions;
import helpers.WaitHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.BankingMainPage;
import pages.way2banking.bankManager.AddCustomerPage;
import pages.way2banking.bankManager.OpenAccountPage;
import pages.way2banking.customer.DepositPage;
import pages.way2banking.customer.TransactionsPage;
import pages.way2banking.customer.WithdrawPage;
import tests.BaseTest;

public class BankingUITestWithCustomerAccountAndTransactions extends BankingPreconditions {
    private final CustomerData customer = CustomerData.builder().build();
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void setUp() {
        bankingMainPage = createCustomerAccountWithDeposit(customer);
    }

//    @BeforeMethod
//    public void createCustomerAndAccount()  {
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
//        DepositPage depositPage = bankingMainPage
//                .openCustomerLoginPage()
//                .selectCustomerNameAndLogin(customer.getFirstName(), customer.getLastName())
//                .openDepositPage();
//        depositPage.depositAmount(customer.getDeposits().get(0).getAmount());
//        depositPage.depositAmount(customer.getDeposits().get(1).getAmount());
//        depositPage.getMessageText();
//        bankingMainPage = depositPage
//                .getCustomerAccountMenu()
//                .getHeader()
//                .goHome();
//    }

    @Test(dataProvider = "depositData", dataProviderClass = DepositDataProvider.class)
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

    @Test(dataProvider = "withdrawData", dataProviderClass = WithdrawDataProvider.class)
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

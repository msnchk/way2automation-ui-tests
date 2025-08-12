package tests.way2banking;

import data.way2banking.customer.CustomerData;
import data.way2banking.customer.DepositData;
import data.way2banking.customer.WithdrawData;
import pages.way2banking.BankingMainPage;
import pages.way2banking.bankManager.AddCustomerPage;
import pages.way2banking.bankManager.BankManagerMenu;
import pages.way2banking.bankManager.OpenAccountPage;
import pages.way2banking.customer.CustomerAccountMenu;
import pages.way2banking.customer.DepositPage;
import pages.way2banking.customer.WithdrawPage;
import tests.BaseTest;

public class BankingPreconditions extends BaseTest {

    public BankManagerMenu createCustomer(CustomerData customer) {
        getDriver().get(AddCustomerPage.ADD_CUSTOMER_URL);
        AddCustomerPage addCustomerPage = new AddCustomerPage(getDriver(), getActions());
        addCustomerPage.fillFormWithCustomerData(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPostCode()
        );
        addCustomerPage.acceptAlert();
        return addCustomerPage.getBankManagerMenu();
    }

    public BankManagerMenu createCustomerWithAccount(CustomerData customer) {
        OpenAccountPage openAccountPage = createCustomer(customer).openAccountPage();
        openAccountPage.openAccountForCustomer(customer.getFirstName(), customer.getLastName(), "Dollar");
        openAccountPage.acceptAlert();
        return openAccountPage.getBankManagerMenu();
    }

    public CustomerAccountMenu createCustomerAccountWithDeposit(CustomerData customer) {
        BankingMainPage bankingMainPage = createCustomerWithAccount(customer)
                .getHeader()
                .goHome();
        DepositPage depositPage = bankingMainPage
                .openCustomerLoginPage()
                .selectCustomerNameAndLogin(customer.getFirstName(), customer.getLastName())
                .openDepositPage();
        for (DepositData deposit : customer.getDeposits()) {
            depositPage.depositAmount(deposit.getAmount());
        }
        return depositPage.getCustomerAccountMenu();
    }

    public CustomerAccountMenu createCustomerAccountWithDepositAndWithdraw(CustomerData customer) {
        WithdrawPage withdrawPage = createCustomerAccountWithDeposit(customer).openWithdrawPage();
        for (WithdrawData withdraw : customer.getWithdrawals()) {
            withdrawPage.withdrawAmount(withdraw.getAmount());
        }
        return withdrawPage.getCustomerAccountMenu();
    }
}

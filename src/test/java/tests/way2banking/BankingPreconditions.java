package helpers;

import data.way2banking.customer.CustomerData;
import pages.way2banking.BankingMainPage;
import pages.way2banking.bankManager.AddCustomerPage;
import pages.way2banking.bankManager.OpenAccountPage;
import pages.way2banking.customer.DepositPage;
import tests.BaseTest;

public class BankingPreconditions extends BaseTest {

    public BankingMainPage createCustomer(CustomerData customer) {
        getDriver().get(AddCustomerPage.ADD_CUSTOMER_URL);
        AddCustomerPage addCustomerPage = new AddCustomerPage(getDriver(), getActions());
        addCustomerPage.fillFormWithCustomerData(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPostCode()
        );
        addCustomerPage.acceptAlert();
        return addCustomerPage.getBankManagerMenu().getHeader().goHome();
    }

    public BankingMainPage createCustomerWithAccount(CustomerData customer) {
        BankingMainPage mainPage = createCustomer(customer);
        OpenAccountPage openAccountPage = mainPage
                .openBankManagerMenu()
                .openAccountPage();
        openAccountPage.openAccountForCustomer(customer.getFirstName(), customer.getLastName(), "Dollar");
        openAccountPage.acceptAlert();
        return openAccountPage.getBankManagerMenu().getHeader().goHome();
    }

    public BankingMainPage createCustomerAccountWithDeposit(CustomerData customer) {
        BankingMainPage mainPage = createCustomerWithAccount(customer);
        DepositPage depositPage = mainPage
                .openCustomerLoginPage()
                .selectCustomerNameAndLogin(customer.getFirstName(), customer.getLastName())
                .openDepositPage();
        customer.getDeposits().forEach(deposit -> depositPage.depositAmount(deposit.getAmount()));
        return depositPage.getCustomerAccountMenu().getHeader().goHome();
    }
}

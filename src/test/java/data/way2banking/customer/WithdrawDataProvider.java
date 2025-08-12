package data.way2banking.customer;

import helpers.RandomAmountHelper;
import org.testng.annotations.DataProvider;

import java.math.BigDecimal;

public class WithdrawDataProvider {
    private static final String DEFAULT_SUCCESS_MESSAGE = "Transaction successful";
    private static final String DEFAULT_TRANSACTION_TYPE = "Debit";

    @DataProvider(name = "withdrawData")
    public Object[][] withdrawData() {
        return new Object[][] {
                {
                        new WithdrawData(
                                RandomAmountHelper.generateRandomAmount(1, 100000),
                                true,
                                DEFAULT_SUCCESS_MESSAGE,
                                DEFAULT_TRANSACTION_TYPE)
                },
                {
                        new WithdrawData(
                                BigDecimal.valueOf(1000000),
                                false,
                                "Transaction Failed. You can not withdraw amount more than the balance.",
                                "")
                }
        };
    }
}

package data.way2banking.customer;

import org.testng.annotations.DataProvider;

import java.math.BigDecimal;

public class DepositDataProvider {
    private static final String DEFAULT_SUCCESS_MESSAGE = "Deposit Successful";
    private static final String DEFAULT_TRANSACTION_TYPE = "Credit";

    @DataProvider(name = "depositData")
    public Object[][] depositData() {
        return new Object[][] {
                {
                    new DepositData(
                            BigDecimal.valueOf(100321),
                            true,
                            DEFAULT_SUCCESS_MESSAGE,
                            DEFAULT_TRANSACTION_TYPE)
                }
//                {
//                    new DepositData(
//                            BigDecimal.ZERO,
//                            false,
//                            "",
//                            "")
//                }
        };
    }
}

package data.way2banking.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
public class WithdrawData {
    private BigDecimal amount;
    private Boolean shouldTransactionExist;
    private String expectedMessageAfterWithdraw;
    private String expectedTransactionType;
}

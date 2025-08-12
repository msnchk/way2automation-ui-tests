package data.way2banking.customer;

import lombok.*;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class DepositData {
    @NonNull
    private BigDecimal amount;
    private Boolean shouldTransactionExist;
    private String expectedMessageAfterDeposit;
    private String expectedTransactionType;
}
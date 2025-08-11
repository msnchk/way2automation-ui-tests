package data.way2banking.customer;

import lombok.*;

import java.math.BigDecimal;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class DepositData {
    @NonNull
    private BigDecimal amount;
    private Boolean shouldTransactionExist;
    private String expectedMessageAfterDeposit;
    private String expectedTransactionType;
}
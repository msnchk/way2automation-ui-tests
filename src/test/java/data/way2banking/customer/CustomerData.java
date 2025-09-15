package data.way2banking.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class CustomerData {
    @Builder.Default
    private String firstName = "Defaultname";

    @Builder.Default
    private String lastName = "Defaultlastname";

    @Builder.Default
    private String postCode = "567890";

    @Builder.Default
    private List<DepositData> deposits = List.of(new DepositData(BigDecimal.valueOf(115000)), new DepositData(BigDecimal.valueOf(5000)));

    @Builder.Default
    private List<WithdrawData> withdrawals = List.of(new WithdrawData(BigDecimal.valueOf(40000)), new WithdrawData(BigDecimal.valueOf(611)));

    public BigDecimal getBalance() {
        BigDecimal balance = BigDecimal.ZERO;
        for (DepositData deposit : deposits) {
            if (deposit.getAmount() != null) {
                balance = balance.add(deposit.getAmount());
            }
        }
        for (WithdrawData withdraw : withdrawals) {
            if (withdraw.getAmount() != null) {
                balance = balance.subtract(withdraw.getAmount());
            }
        }
        return balance;
    }
}

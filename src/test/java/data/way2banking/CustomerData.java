package data.way2banking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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
}

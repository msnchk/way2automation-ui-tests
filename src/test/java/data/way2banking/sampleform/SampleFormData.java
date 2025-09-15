package data.way2banking.sampleform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class SampleFormData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> hobbies;
    private String gender;
}

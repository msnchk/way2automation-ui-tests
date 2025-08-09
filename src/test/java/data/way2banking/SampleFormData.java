package data.way2banking;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SampleFormData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> hobbies;
    private String gender;
}

package data.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDataCase {
    private String username;
    private String password;
    private String usernameDescription;
    private boolean expectedSuccess;
    private String expectedErrorMessage;
    private boolean buttonShouldBeEnabled;
}

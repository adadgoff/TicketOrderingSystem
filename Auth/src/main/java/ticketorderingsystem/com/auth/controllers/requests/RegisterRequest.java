package ticketorderingsystem.com.auth.controllers.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ticketorderingsystem.com.auth.utils.validations.Validation;

public class RegisterRequest {
    public String nickname;
    public String email;
    public String password;

    @JsonIgnore
    public boolean isValid() {
        return Validation.isNicknameValid(nickname) &&
                Validation.isEmailValid(email) &&
                Validation.isPasswordValid(password);
    }
}

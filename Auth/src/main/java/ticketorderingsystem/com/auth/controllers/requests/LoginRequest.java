package ticketorderingsystem.com.auth.controllers.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ticketorderingsystem.com.auth.utils.validations.Validation;

public class LoginRequest {
    public String email;
    public String password;

    @JsonIgnore
    public boolean isValid() {
        return Validation.isEmailValid(email) &&
                Validation.isPasswordValid(password);
    }
}

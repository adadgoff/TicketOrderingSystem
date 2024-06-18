package ticketorderingsystem.com.auth.controllers.apis;

import jakarta.servlet.http.HttpServletRequest;
import ticketorderingsystem.com.auth.controllers.requests.LoginRequest;
import ticketorderingsystem.com.auth.controllers.requests.RegisterRequest;
import ticketorderingsystem.com.auth.dtos.UserDTO;

public interface AuthAPI {
    UserDTO register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);

    UserDTO getUser(String token, HttpServletRequest request);
}

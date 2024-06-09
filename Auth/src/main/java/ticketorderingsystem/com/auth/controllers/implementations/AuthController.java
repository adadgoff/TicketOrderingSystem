package ticketorderingsystem.com.auth.controllers.implementations;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ticketorderingsystem.com.auth.controllers.apis.AuthAPI;
import ticketorderingsystem.com.auth.controllers.requests.LoginRequest;
import ticketorderingsystem.com.auth.controllers.requests.RegisterRequest;
import ticketorderingsystem.com.auth.dtos.UserDTO;
import ticketorderingsystem.com.auth.models.UserModel;
import ticketorderingsystem.com.auth.services.implementations.AuthService;
import ticketorderingsystem.com.auth.services.implementations.TokenService;

import java.util.Optional;

@RestController
public class AuthController implements AuthAPI {
    private final AuthService authService;
    private final TokenService tokenService;

    public AuthController(AuthService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @Override
    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterRequest registerRequest) {
        if (!registerRequest.isValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid register: Use correct data formats.");
        }

        UserModel userModel = authService.register(registerRequest.nickname, registerRequest.email, registerRequest.password);
        return UserDTO.toDTO(userModel);
    }

    @Override
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        if (!loginRequest.isValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password: Use correct data formats.");
        }

        return authService.login(loginRequest.email, loginRequest.password);
    }

    @Override
    @GetMapping("/user/{token}")
    public UserDTO getUser(@PathVariable String token, HttpServletRequest request) {
        Optional<UserModel> currentUserModelOptional = tokenService.authenticateUser(request);
        Optional<UserModel> requestedUserModelOptional = tokenService.getUserFromToken(token);

        if (currentUserModelOptional.isEmpty() || requestedUserModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unknown user.");
        }

        // For security reasons, it is forbidden to use tokens to find users other than yourself.
        if (currentUserModelOptional.get().getId() != requestedUserModelOptional.get().getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied.");
        }

        return UserDTO.toDTO(requestedUserModelOptional.get());
    }
}

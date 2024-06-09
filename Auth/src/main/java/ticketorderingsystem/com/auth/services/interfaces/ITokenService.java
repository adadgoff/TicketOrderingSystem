package ticketorderingsystem.com.auth.services.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import ticketorderingsystem.com.auth.models.UserModel;

import java.util.Optional;

@Service
public interface ITokenService {
    String generateToken(long userId);

    Optional<UserModel> getUserFromToken(String token);

    Optional<UserModel> authenticateUser(HttpServletRequest request);
}

package ticketorderingsystem.com.ticketordering.services.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface ITokenService {
    boolean isTokenValid(String token);

    Long getUserIdFromToken(String token);

    Long authenticateUser(HttpServletRequest request);
}

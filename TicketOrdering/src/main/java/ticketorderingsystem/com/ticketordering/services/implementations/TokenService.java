package ticketorderingsystem.com.ticketordering.services.implementations;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ticketorderingsystem.com.ticketordering.services.interfaces.ITokenService;

import java.util.Base64;
import java.util.Date;

@Service
public class TokenService implements ITokenService {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public boolean isTokenValid(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(secret))
                    .build()
                    .parseSignedClaims(token)
                    .getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long getUserIdFromToken(String token) {
        try {
            return Long.parseLong(
                    Jwts.parser()
                            .setSigningKey(Base64.getDecoder().decode(secret))
                            .build()
                            .parseSignedClaims(token)
                            .getBody()
                            .getSubject()
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long authenticateUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found.");
        }

        String token = authHeader.replace("Bearer ", "");

        if (!isTokenValid(token)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token.");
        }

        return getUserIdFromToken(token);
    }
}

package ticketorderingsystem.com.auth.services.implementations;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ticketorderingsystem.com.auth.models.UserModel;
import ticketorderingsystem.com.auth.repositories.UserRepository;
import ticketorderingsystem.com.auth.services.interfaces.ITokenService;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService implements ITokenService {
    @Value("${jwt.expireTime}")
    private long expireTime;

    @Value("${jwt.secret}")
    private String secret;

    private final UserRepository userRepository;

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String generateToken(long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(secret))
                .compact();
    }

    @Override
    public Optional<UserModel> getUserFromToken(String token) {
        try {
            long userId = Long.parseLong(
                    Jwts.parser()
                            .setSigningKey(Base64.getDecoder().decode(secret))
                            .build()
                            .parseSignedClaims(token)
                            .getBody()
                            .getSubject()
            );

            return userRepository.findById(userId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserModel> authenticateUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found.");
        }

        String token = authHeader.replace("Bearer ", "");
        return getUserFromToken(token);
    }
}

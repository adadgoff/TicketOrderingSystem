package ticketorderingsystem.com.auth.services.implementations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ticketorderingsystem.com.auth.models.TokenModel;
import ticketorderingsystem.com.auth.models.UserModel;
import ticketorderingsystem.com.auth.repositories.TokenRepository;
import ticketorderingsystem.com.auth.repositories.UserRepository;
import ticketorderingsystem.com.auth.services.interfaces.IAuthService;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    @Value("${jwt.expireTime}")
    private long expireTime;

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(TokenRepository tokenRepository, UserRepository userRepository, TokenService tokenService) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public UserModel register(String nickname, String email, String password) {
        Optional<UserModel> userModelOptional = userRepository.findByEmail(email);

        if (userModelOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use.");
        }

        UserModel userModel = UserModel.builder()
                .nickname(nickname)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(userModel);
        return userModel;
    }

    @Override
    @Transactional
    public String login(String email, String password) {
        Optional<UserModel> userModelOptional = userRepository.findByEmail(email);

        if (userModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist.");
        }

        UserModel userModel = userModelOptional.get();
        if (!passwordEncoder.matches(password, userModel.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password is incorrect.");
        }

        String token = tokenService.generateToken(userModel.getId());
        TokenModel tokenModel = tokenRepository.findByUser(userModel)
                .orElse(new TokenModel());

        tokenModel.setToken(token);
        tokenModel.setExpireDate(new Date(System.currentTimeMillis() + expireTime));
        tokenModel.setUser(userModel);

        tokenRepository.save(tokenModel);
        return token;
    }
}

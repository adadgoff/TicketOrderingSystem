package ticketorderingsystem.com.auth.services.interfaces;

import ticketorderingsystem.com.auth.models.UserModel;

public interface IAuthService {
    UserModel register(String nickname, String email, String password);

    String login(String email, String password);
}

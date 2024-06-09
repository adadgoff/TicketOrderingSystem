package ticketorderingsystem.com.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketorderingsystem.com.auth.models.TokenModel;
import ticketorderingsystem.com.auth.models.UserModel;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenModel, Long> {
    Optional<TokenModel> findByUser(UserModel userModel);
}

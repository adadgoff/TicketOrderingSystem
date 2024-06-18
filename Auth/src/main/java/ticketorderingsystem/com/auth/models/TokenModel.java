package ticketorderingsystem.com.auth.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sessions")
public class TokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expire_at", nullable = false, updatable = false)
    private Date expireDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}

package mk.ukim.finki.db.distributorapp.token;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.users.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token_")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long tokenId;

    @Column(name = "t_value", unique = true, nullable = false)
    private String tokenValue;

    @Column(name = "t_date")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "t_type")
    private TokenType tokenType;

    @Column(name = "t_expiry")
    private LocalDateTime tokenExpiryDate;

    @Column(name = "t_validated_at")
    private LocalDateTime tokenValidatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_user", referencedColumnName = "user_id", nullable = false, unique = true)
    private User user;
}

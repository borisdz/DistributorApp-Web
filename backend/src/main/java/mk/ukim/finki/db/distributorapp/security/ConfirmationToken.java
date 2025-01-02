package mk.ukim.finki.db.distributorapp.security;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.entities.Users;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "t_id")
    private Long tokenId;

    @Column(name = "t_value")
    private String confirmationToken;

    @Column(name = "t_date")
    private Date createdDate;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    private Users user;

    public ConfirmationToken(Users user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}

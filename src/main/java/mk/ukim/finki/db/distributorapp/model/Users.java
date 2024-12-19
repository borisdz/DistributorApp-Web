package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String user_surname;

    @Column(nullable = false)
    private String user_password;

    @Column(nullable = false)
    private String user_email;

    @Column(nullable = false)
    private String user_mobile;

    @Column(nullable = false)
    private String user_salt;

    @Column(nullable = false)
    private Boolean user_active;

    private String user_image;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Users(String name, String surname, String safePass, String email, String mobile, String saltValue, Boolean active, String image) {
        this.user_name = name;
        this.user_surname = surname;
        this.user_password = safePass;
        this.user_email = email;
        this.user_mobile = mobile;
        this.user_salt = saltValue;
        this.user_active = active;
        this.user_image = image;
    }
}

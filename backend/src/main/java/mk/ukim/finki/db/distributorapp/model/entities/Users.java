package mk.ukim.finki.db.distributorapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.enumerations.Role;
import mk.ukim.finki.db.distributorapp.security.ConfirmationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "clazz_",
        discriminatorType = DiscriminatorType.STRING
)
@Data
@Table(name = "users")
public class Users implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_surname", nullable = false)
    private String userSurname;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_pass", nullable = false)
    private String userPassword;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_mobile", nullable = false)
    private String userMobile;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_salt", nullable = false)
    private String userSalt;

    @NotNull
    @Column(name = "user_active", nullable = false)
    private Boolean userActive;

    @Size(max = 255)
    @Column(name = "user_image")
    private String userImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role userRole;

    @Size(max = 255)
    @Column(name = "user_rtoken")
    private String userResetToken;

    @Column(name = "user_rtoken_exp")
    private LocalDateTime userResetTokenExpiry;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "clazz_", insertable = false, updatable = false)
    private String clazz_;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ConfirmationToken confirmationToken;

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.getUserRole().name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userActive;
    }
}

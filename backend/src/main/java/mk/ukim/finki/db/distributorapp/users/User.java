package mk.ukim.finki.db.distributorapp.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.distributorapp.city.City;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;
import mk.ukim.finki.db.distributorapp.token.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements UserDetails {
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

    @Column(name = "clazz_", insertable = false, updatable = false)
    private String clazz_;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public User(UsersLoadingDto dto) {
        this.userRole = Role.valueOf(dto.getUserRole());
        this.userId = dto.getUserId();
        this.userName = dto.getUserName();
        this.userSurname = dto.getUserSurname();
        this.userPassword = dto.getUserPassword();
        this.userEmail = dto.getUserEmail();
        this.userMobile = dto.getUserMobile();
        this.userSalt = dto.getUserSalt();
        this.userActive = dto.getUserActive();
        this.userImage = dto.getUserImage();
        this.tokens = null;
        this.city = null;
    }

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

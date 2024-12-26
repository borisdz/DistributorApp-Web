package mk.ukim.finki.db.distributorapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Table(name = "users", schema = "IND0_185022")
public abstract class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "user_surname", nullable = false)
    private String user_surname;

    @Column(name = "user_pass", nullable = false)
    private String user_password;

    @Column(name = "user_email", nullable = false)
    private String user_email;

    @Column(name = "user_mobile", nullable = false)
    private String user_mobile;

    @Column(name = "user_salt", nullable = false)
    private String user_salt;

    @Column(name = "user_email_conf", nullable = false)
    private Boolean user_active;

    @Column(name = "user_image")
    private String user_image;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Override
    public String getUsername() {
        return user_email;
    }

    @Override
    public String getPassword() {
        return user_password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getClass().getSimpleName()));
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
        return user_active;
    }
}

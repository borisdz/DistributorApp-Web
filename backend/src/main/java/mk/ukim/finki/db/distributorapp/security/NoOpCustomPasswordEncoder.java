package mk.ukim.finki.db.distributorapp.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NoOpCustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString(); // No encoding, since we handle it manually
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword); // No matching logic, handled in service
    }
}

package mk.ukim.finki.db.distributorapp.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PassEncryptionPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        // Generate a new salt and hash the password
        throw new UnsupportedOperationException("Salt must be provided explicitly!");
    }

    public String encodeWithSalt(CharSequence rawPassword, String salt) {
        return PassEncryption.generateSecurePassword(rawPassword.toString(), salt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Split stored password into salt and hash
        throw new UnsupportedOperationException("Use matchesWithSalt for explicit salt verification!");
    }

    public boolean matchesWithSalt(CharSequence rawPassword, String encodedPassword, String salt) {
        return PassEncryption.verifyUserPassword(rawPassword.toString(), encodedPassword, salt);
    }
}


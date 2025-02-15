package mk.ukim.finki.db.distributorapp._security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SuperUserAuthenticationProvider implements AuthenticationProvider {

    private static final String SUPERUSER_ENCODED_PASSWORD = "$2a$12$KC9Z0kHyu06iV4xlpKOgL.p2KH3NS9EY/N3g97nGAtC/NCpRXVgXa";

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        if (!"superuser@admin.com".equalsIgnoreCase(username)) {
            return null;
        }

        if (!passwordEncoder.matches(rawPassword, SUPERUSER_ENCODED_PASSWORD)) {
            throw new BadCredentialsException("Invalid superuser credentials");
        }

        UserDetails superUser = User.withUsername("superuser@admin.com")
                .password(SUPERUSER_ENCODED_PASSWORD)
                .roles("ADMIN")
                .build();

        return new UsernamePasswordAuthenticationToken(superUser, superUser.getPassword(), superUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

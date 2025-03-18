package mk.ukim.finki.db.distributorapp._security;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.users.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ("".equals(email) || "".equals(password)) {
            throw new BadCredentialsException("Invalid email or password");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(email);
        String salt = this.userService.findFullUserDtoByEmail(email).getUserSalt();
        if (!PassEncryption.verifyUserPassword(password, userDetails.getPassword(), salt))
            throw new BadCredentialsException("Invalid password");
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}

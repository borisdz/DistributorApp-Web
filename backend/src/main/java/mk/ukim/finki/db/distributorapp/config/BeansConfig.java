package mk.ukim.finki.db.distributorapp.config;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.security.PassEncryptionPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {
    private final UserDetailsService userDetailsService;

//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        CustomUsernamePasswordAuthenticationProvider authProvider = new CustomUsernamePasswordAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PassEncryptionPasswordEncoder();
    }
}

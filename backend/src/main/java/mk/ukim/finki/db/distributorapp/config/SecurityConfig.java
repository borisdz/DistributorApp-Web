package mk.ukim.finki.db.distributorapp.config;

import mk.ukim.finki.db.distributorapp.security.PassEncryptionPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // FOR TESTING:
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);

        return http.build();
    }


    // FOR PRODUCTION:

    /**
     * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     * //        http
     * //                .authorizeHttpRequests(authorize -> authorize
     * //                        .requestMatchers("/login","/register").permitAll()
     * //                        .anyRequest().authenticated()
     * //                )
     * //                .formLogin(form -> form
     * //                        .loginPage("/login")
     * //                        .permitAll()
     * //                )
     * //                .logout(LogoutConfigurer::permitAll);
     * <p>
     * http
     * .csrf(AbstractHttpConfigurer::disable)
     * .authorizeHttpRequests(auth -> auth
     * .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()
     * .requestMatchers("/manager/**").hasAuthority("Manager")
     * .requestMatchers("/driver/**").hasAuthority("Driver")
     * .requestMatchers("/customer/**").hasAuthority("Customer")
     * .anyRequest().authenticated()
     * )
     * .formLogin(login -> login
     * .loginPage("/login")
     * .defaultSuccessUrl("/home", true)
     * .permitAll()
     * )
     * .logout(logout -> logout
     * .logoutUrl("/logout")
     * .logoutSuccessUrl("/login")
     * .permitAll()
     * );
     * <p>
     * return http.build();
     * }
     **/


    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PassEncryptionPasswordEncoder();
    }
}

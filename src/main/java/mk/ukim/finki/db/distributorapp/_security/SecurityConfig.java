package mk.ukim.finki.db.distributorapp._security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp._security.jwt.JwtAuthenticationFilter;
import mk.ukim.finki.db.distributorapp._security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {

    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
    private final SuperUserAuthenticationProvider superUserAuthenticationProvider;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${management.endpoints.web.cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(channel -> channel
                        .anyRequest().requiresSecure() // Force HTTPS for all requests.
                )
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/api/auth/**", "/reset-password/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/customer/**", "/api/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/manager/**", "/api/manager/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/driver/**", "/api/driver/**").hasAnyRole("DRIVER", "ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint((request, response, authException)
                                -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        String cleanedOrigins = allowedOrigins.replace("\"", "");
        List<String> origins = Arrays.stream(cleanedOrigins.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        configuration.setAllowedOriginPatterns(origins);
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        PasswordEncoder standardEncoder = new BCryptPasswordEncoder();
        UserDetails superUser = User.withUsername("superuser@admin.com")
                .password(standardEncoder.encode("supersecret"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(superUser);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http
    ) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.authenticationProvider(superUserAuthenticationProvider);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PassEncryptionPasswordEncoder();
    }

}

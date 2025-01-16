package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp.model.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AuthService {
    String getUserSalt(String username);

    Optional<Users> getUserByEmail(String email);

    ResponseEntity<?> confirmEmail(String confirmationToken);

    Users register(RegisterRequestDto registerRequest);

    Users login(LoginRequestDto loginRequest);
}

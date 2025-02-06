package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.dto.CreateDriverDto;
import mk.ukim.finki.db.distributorapp.model.dto.CreateManagerDto;
import mk.ukim.finki.db.distributorapp.model.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp.model.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AuthService {
    String getUserSalt(String username);

    Optional<Users> getUserByEmail(String email);

    ResponseEntity<?> confirmEmail(String confirmationToken);

    void register(RegisterRequestDto registerRequest) throws Exception;

    void createManager(CreateManagerDto createUserDto) throws Exception;

    void createDriver(CreateDriverDto createDriverDto) throws Exception;

    Users login(LoginRequestDto loginRequest);
}

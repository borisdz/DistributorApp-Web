package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.entities.City;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AuthService {
    String getUserSalt(String username);

    Optional<Users> getUserByEmail(String email);

    ResponseEntity<?> confirmEmail(String confirmationToken);

    Users register(String name, String surname, String email,
                   String password, String mobile, String image, City city);

    Users login(String username, String password);
}

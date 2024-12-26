package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.City;
import mk.ukim.finki.db.distributorapp.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsersService {
    Users register(String name, String surname, String email,
                   String password, String mobile, String image, City city);

    ResponseEntity<?> confirmEmail(String confirmationToken);

    Users login(String username, String password);

    String getUserSalt(String username);

    Optional<Users> getUserByEmail(String email);
}

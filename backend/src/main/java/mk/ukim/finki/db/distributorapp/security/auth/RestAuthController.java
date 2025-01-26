package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp.model.dto.LoginResponseDto;
import mk.ukim.finki.db.distributorapp.model.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth")
public class RestAuthController {

    private final AuthService authService;

    public RestAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute LoginRequestDto loginRequest) {
        Users user = authService.login(loginRequest);
        return ResponseEntity.ok(new LoginResponseDto(user.getUserEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@ModelAttribute RegisterRequestDto registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

}

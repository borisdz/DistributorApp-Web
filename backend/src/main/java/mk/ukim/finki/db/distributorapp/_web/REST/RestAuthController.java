package mk.ukim.finki.db.distributorapp._web.REST;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp._security.auth.AuthService;
import mk.ukim.finki.db.distributorapp._security.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class RestAuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UsersLoadingDto> login(@RequestBody final LoginRequestDto user) {
        UsersLoadingDto loggedUser = authService.login(user);
        return ResponseEntity.ok(loggedUser);
    }
}

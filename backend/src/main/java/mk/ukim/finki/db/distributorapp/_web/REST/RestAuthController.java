package mk.ukim.finki.db.distributorapp._web.REST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.db.distributorapp._security.auth.AuthService;
import mk.ukim.finki.db.distributorapp._security.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp._security.dto.LoginResponseDto;
import mk.ukim.finki.db.distributorapp._security.jwt.JwtTokenProvider;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class RestAuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

//    @PostMapping("/login")
//    public ResponseEntity<UsersLoadingDto> login(@RequestBody final LoginRequestDto user) {
//        UsersLoadingDto loggedUser = authService.login(user);
//        return ResponseEntity.ok(loggedUser);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()
            ));
            String token = jwtTokenProvider.createToken(request.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        }catch (AuthenticationException e){
            return ResponseEntity.status(404).body("Invalid email/password supplied");
        }
    }
}

@Getter
@Setter
class AuthRequest {
    private String email;
    private String password;
}

@Getter
@Setter
@AllArgsConstructor
class AuthResponse {
    private String token;

}

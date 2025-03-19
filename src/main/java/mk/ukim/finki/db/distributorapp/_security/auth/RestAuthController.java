package mk.ukim.finki.db.distributorapp._security.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.db.distributorapp._security.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class RestAuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try{
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            String email = authentication.getName();
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            String token = jwtTokenProvider.createToken(email,roles);
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

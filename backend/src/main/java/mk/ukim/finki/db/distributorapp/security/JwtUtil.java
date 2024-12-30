package mk.ukim.finki.db.distributorapp.security;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class JwtUtil {
  private static final String SECRET_KEY = "secret";
  private static final long EXPIRATION_TIME = 1000*60*60*10;

  private final Key key = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA1");

  public String generateToken(String username){
    return Jwts.builder()
      .setSubject(username)
  }
}

package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.security.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from token " +
                    "where t_value=?1"
    )
    Token findTokenByValue(@NonNull String token);
}

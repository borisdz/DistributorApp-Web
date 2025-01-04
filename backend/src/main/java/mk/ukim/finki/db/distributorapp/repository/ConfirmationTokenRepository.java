package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.security.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from token " +
                    "where t_value=?1"
    )
    ConfirmationToken findConfirmationTokenByToken(@NonNull String token);
}

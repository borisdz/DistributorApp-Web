package mk.ukim.finki.db.distributorapp.repository;

import mk.ukim.finki.db.distributorapp.model.sec.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    @Query(
            nativeQuery = true,
            value = ""
    )
    ConfirmationToken findConfirmationTokenByToken(String token);
}

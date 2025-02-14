package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.TokenDto;
import mk.ukim.finki.db.distributorapp.security.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query(
            nativeQuery = true,
            value = """
                    select
                    from token
                    where t_value = ?1
                    """
    )
    TokenDto findTokenByValue(@NonNull String token);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    insert into token(t_value, t_type, t_expiry, t_user)
                    values (:value, :type, :expiry, :user)
                    """
    )
    Integer create(
            @NonNull @Param("value") String t_value,
            @NonNull @Param("expiry") LocalDateTime t_expiry,
            @NonNull @Param("user") Long user_id,
            @NonNull @Param("type") String t_type
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    update token
                    set t_expiry = :expiry, t_user = :user, t_value = :value, t_type = :type, t_validated_at = :validated_at
                    where t_id = :id
                    """
    )
    Integer edit(
            @NonNull @Param("id") Long t_id,
            @NonNull @Param("value") String t_value,
            @NonNull @Param("expiry") LocalDateTime t_expiry,
            @NonNull @Param("user") Long user_id,
            @NonNull @Param("validated_at") LocalDateTime validated_at,
            @NonNull @Param("type") String t_type
    );
}

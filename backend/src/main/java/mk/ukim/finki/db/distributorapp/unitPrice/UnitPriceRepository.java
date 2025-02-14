package mk.ukim.finki.db.distributorapp.unitPrice;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UnitPriceRepository extends JpaRepository<UnitPrice, UnitPriceId> {
    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    insert into unit_price(unit_id, price_id)
                    values (:unitId, :priceId)
                    """
    )
    Integer create(
            @Param("unitId") @NonNull Long unitId,
            @Param("priceId") @NonNull Long priceId
    );
}

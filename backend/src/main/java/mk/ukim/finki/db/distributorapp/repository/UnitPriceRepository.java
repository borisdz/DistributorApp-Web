package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.UnitPrice;
import mk.ukim.finki.db.distributorapp.model.entities.UnitPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

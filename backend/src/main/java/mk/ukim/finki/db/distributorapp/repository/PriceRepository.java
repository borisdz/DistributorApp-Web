package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from price"
    )
    List<Price> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from price where art_id=:art"
    )
    List<Price> findAllByArticleId(@NonNull @Param("art") Long id);

    @Query(
            nativeQuery = true,
            value = "select * from price where price_id=:id"
    )
    Optional<Price> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into price(price, price_eff_date, art_id) " +
                    "values (:price,:effD,:art)"
    )
    Integer create(
            @NonNull @Param("price") BigDecimal price,
            @NonNull @Param("effD") LocalDateTime price_eff_date,
            @NonNull @Param("art") Long art_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update price " +
                    "set price=:price,price_eff_date=:effD,art_id=:art " +
                    "where price_id=:id"
    )
    Integer edit(
            @NonNull @Param("id") Integer id,
            @NonNull @Param("price") BigDecimal price,
            @NonNull @Param("effD") LocalDateTime price_eff_date,
            @NonNull @Param("art") Long art_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from price where price_id=:id"
    )
    void delete(@NonNull @Param("id") Integer id);
}

package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
            value = "select * from price where art_id=?1"
    )
    List<Price> findAllByArticleId(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = "select * from price where price_id=?1"
    )
    Optional<Price> findById(@NonNull Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into price(price, price_eff_date, art_id) " +
                    "values (?1,?2,?3)"
    )
    Integer create(
            @NonNull BigDecimal price,
            @NonNull LocalDateTime price_eff_date,
            @NonNull Long art_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update price " +
                    "set price=?2,price_eff_date=?3,art_id=?4 " +
                    "where price_id=?1"
    )
    Integer edit(
            @NonNull Integer id,
            @NonNull BigDecimal price,
            @NonNull LocalDateTime price_eff_date,
            @NonNull Long art_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from price where price_id=?1"
    )
    void delete(@NonNull Integer id);
}

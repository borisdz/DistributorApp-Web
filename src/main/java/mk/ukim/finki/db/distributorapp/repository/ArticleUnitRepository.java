package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.ArticleUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleUnitRepository extends JpaRepository<ArticleUnit, Long> {
    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    List<ArticleUnit> listAll();

    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    List<ArticleUnit> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    Optional<ArticleUnit> findById(@NonNull @Param("id") Long id);

    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    List<ArticleUnit> findAllByWarehouse(Integer wh_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    Optional<ArticleUnit> create(
            Date unit_exp_date,
            String unit_ser_number,
            String unit_batch_number,
            Date unit_manufacture_date,
            Double unit_cost_price,
            Long art_id,
            Integer wh_id,
            Long ord_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    Optional<ArticleUnit> edit(
            Long id,
            Date unit_exp_date,
            String unit_ser_number,
            String unit_batch_number,
            Date unit_manufacture_date,
            Double unit_cost_price,
            Long art_id,
            Integer wh_id,
            Long ord_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    ""
    )
    void delete(Long id);
}

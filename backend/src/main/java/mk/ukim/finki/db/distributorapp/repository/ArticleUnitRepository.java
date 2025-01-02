package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.ArticleUnit;
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
            value = "select * from article_unit"
    )
    List<ArticleUnit> listAll();

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from article_unit au join article a on au.art_id = a.art_id " +
                    "where a.art_name like ?1"
    )
    List<ArticleUnit> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "select * from article_unit where unit_id=?1"
    )
    Optional<ArticleUnit> findById(@NonNull @Param("id") Long id);

    @Query(
            nativeQuery = true,
            value = "select * from article_unit where wh_id=?1"
    )
    List<ArticleUnit> findAllByWarehouse(@NonNull @Param("wh") Integer wh_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into article_unit(unit_expiration_date, unit_serial_number, unit_batch_number, unit_manufacture_date, unit_cost_price, art_id, wh_id, ord_id) " +
                    "values (?1,?2,?3,?4,?5,?6,?7,?8)"
    )
    Optional<ArticleUnit> create(
            @NonNull @Param("exp_date") Date unit_exp_date,
            @NonNull @Param("ser_no") String unit_ser_number,
            @NonNull @Param("batch_no") String unit_batch_number,
            @NonNull @Param("man_date") Date unit_manufacture_date,
            @NonNull @Param("cost_price") Double unit_cost_price,
            @NonNull @Param("art") Long art_id,
            @NonNull @Param("wh") Integer wh_id,
            @Param("ord") Long ord_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update article_unit " +
                    "set unit_expiration_date=?2, unit_serial_number=?3, unit_batch_number=?4,unit_manufacture_date=?5,unit_cost_price=?6,art_id=?7,wh_id=?8,ord_id=?9 " +
                    "where unit_id=?1"
    )
    Optional<ArticleUnit> edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("exp_date") Date unit_exp_date,
            @NonNull @Param("ser_no") String unit_ser_number,
            @NonNull @Param("batch_no") String unit_batch_number,
            @NonNull @Param("man_date") Date unit_manufacture_date,
            @NonNull @Param("cost_price") Double unit_cost_price,
            @NonNull @Param("art") Long art_id,
            @NonNull @Param("wh") Integer wh_id,
            @Param("ord") Long ord_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from article_unit where unit_id=?1"
    )
    void delete(@NonNull @Param("id") Long id);
}

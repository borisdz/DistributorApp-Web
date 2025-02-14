package mk.ukim.finki.db.distributorapp.articleUnit;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.articleUnit.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.articleUnit.dto.ArticleUnitSimpleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ArticleUnitRepository extends JpaRepository<ArticleUnit, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from article_unit"
    )
    List<ArticleUnitSimpleDto> listAll();

    @Query(
            nativeQuery = true,
            value = """
                    select au.unit_id as id,
                    au.unit_expiration_date as expiryDate,
                    au.unit_serial_number as serialNo,
                    au.unit_batch_number as batchNo,
                    au.unit_manufacture_date as manufactureDate,
                    au.unit_cost_price as costPrice,
                    a.art_id as artId,
                    a.art_name as artName,
                    au.wh_id as whId,
                    r.region_name as whRegion,
                    c.city_name as whCity,
                    au.ord_id as ordId,
                    u.user_email as customerEmail
                    from article_unit au
                    join warehouse wh on au.wh_id = wh.wh_id
                    join city c on wh.city_id = c.city_id
                    join region r on c.region_id = r.region_id
                    join unit_price up on au.unit_id = up.unit_id
                    join price p on up.price_id = p.price_id
                    join article a on p.art_id = a.art_id
                    join orders o on au.ord_id = o.ord_id
                    join customer cust on o.cust_id = cust.user_id
                    join users u on cust.user_id = u.user_id
                    """
    )
    List<ArticleUnitDto> findAllByWarehouse(@NonNull @Param("wh") Integer wh_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into article_unit(unit_expiration_date, unit_serial_number, unit_batch_number, unit_manufacture_date, unit_cost_price, wh_id, ord_id) " +
                    "values (?1,?2,?3,?4,?5,?6,?7)"
    )
    Integer create(
            @NonNull @Param("exp_date") Date unit_exp_date,
            @NonNull @Param("ser_no") String unit_ser_number,
            @NonNull @Param("batch_no") String unit_batch_number,
            @NonNull @Param("man_date") Date unit_manufacture_date,
            @NonNull @Param("cost_price") BigDecimal unit_cost_price,
            @NonNull @Param("wh") Integer wh_id,
            @Param("ord") Long ord_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update article_unit " +
                    "set unit_expiration_date = ?2, unit_serial_number = ?3, unit_batch_number = ?4,unit_manufacture_date = ?5,unit_cost_price = ?6,wh_id = ?7,ord_id = ?8 " +
                    "where unit_id=?1"
    )
    Integer edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("exp_date") Date unit_exp_date,
            @NonNull @Param("ser_no") String unit_ser_number,
            @NonNull @Param("batch_no") String unit_batch_number,
            @NonNull @Param("man_date") Date unit_manufacture_date,
            @NonNull @Param("cost_price") BigDecimal unit_cost_price,
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

    @Query(
            nativeQuery = true,
            value = """
                    select au.unit_id as id,
                           au.unit_expiration_date as expiryDate,
                           au.unit_serial_number as serialNo,
                           au.unit_batch_number as batchNo,
                           au.unit_manufacture_date as manufactureDate,
                           au.unit_cost_price as costPrice,
                           a.art_id as artId,
                           au.wh_id as whId,
                           au.ord_id as ordId
                    from article_unit au
                    join unit_price up on au.unit_id = up.unit_id
                    join price p on up.price_id = p.price_id
                    join article a on p.art_id = a.art_id
                    where au.wh_id = ?2 and a.art_id = ?1
                    """
    )
    List<ArticleUnitSimpleDto> findAllSimpleByArticleAndWarehouse(Long articleId, Integer warehouseId);
}

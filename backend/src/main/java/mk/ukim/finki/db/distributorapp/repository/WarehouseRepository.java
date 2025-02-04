package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.ArticleUnit;
import mk.ukim.finki.db.distributorapp.model.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from warehouse"
    )
    List<Warehouse> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from warehouse where city_id=?1"
    )
    List<Warehouse> findAllByCity(@NonNull Long city);

    @Query(
            nativeQuery = true,
            value = "select * from warehouse where wh_id=?1"
    )
    Optional<Warehouse> findById(@NonNull Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into warehouse(wh_address, city_id) " +
                    "values (?1,?2)"
    )
    Integer create(
            @NonNull String whAddress,
            @NonNull Long city);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update warehouse " +
                    "set wh_address=?2,city_id=?3 " +
                    "where wh_id=?1"
    )
    Integer edit(
            @NonNull Integer id,
            @NonNull String whAddress,
            @NonNull Long city);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from warehouse where wh_id=?1"
    )
    void delete(@NonNull Integer id);
    //-------------------------------------------------------------------------------------------------------------------

    @Query(
            nativeQuery = true,
            value = """
                    select a.art_name, man.man_name, count(a.art_id) as total_units
                    from warehouse w
                        join manager m on m.wh_id = w.wh_id
                        join article_unit au on au.wh_id = w.wh_id
                        join unit_price up on au.unit_id = up.unit_id
                        join price p on p.price_id=up.price_id
                        join article a on p.art_id=a.art_id
                        join article a on au = a.art_id
                        join manufacturer man on a.man_id = man.man_id
                    where m.user_id = :manager
                    group by a.art_name, man.man_name
                    order by total_units
                    """
    )
    List<ArticleUnit> getInventoryByManager(@NonNull @Param("manager") Long manager_id);

    @Query(
            nativeQuery = true,
            value = """
                    select * from warehouse where city_id=?1
                    """
    )
    Warehouse findWarehouseByCityId(Long id);
}

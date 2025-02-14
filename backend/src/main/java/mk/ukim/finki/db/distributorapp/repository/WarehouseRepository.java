package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.WarehouseDto;
import mk.ukim.finki.db.distributorapp.model.dto.WarehouseInventoryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into warehouse(wh_adr, city_id) " +
                    "values (?1,?2)"
    )
    Integer create(
            @NonNull String whAddress,
            @NonNull Integer city);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update warehouse " +
                    "set wh_adr = ?2,city_id = ?3 " +
                    "where wh_id = ?1"
    )
    Integer edit(
            @NonNull Integer id,
            @NonNull String whAddress,
            @NonNull Integer city);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from warehouse where wh_id = ?1"
    )
    void delete(@NonNull Integer id);
    //-------------------------------------------------------------------------------------------------------------------

    @Query(
            nativeQuery = true,
            value = """
                    select a.art_name as articleName,
                           man.man_name as manufacturerName,
                           count(au.unit_id) as totalUnits
                    from warehouse w
                        join manager m on m.wh_id = w.wh_id
                        join article_unit au on au.wh_id = w.wh_id
                        join unit_price up on au.unit_id = up.unit_id
                        join price p on p.price_id=up.price_id
                        join article a on p.art_id=a.art_id
                        join manufacturer man on a.man_id = man.man_id
                    where m.user_id = :manager
                    group by a.art_name, man.man_name
                    order by totalUnits
                    """
    )
    List<WarehouseInventoryDto> getInventoryByManager(@NonNull @Param("manager") Long manager_id);

    @Query(
            nativeQuery = true,
            value = """
                    with reg as (
                        select r.*
                        from city c1 join region r on c1.region_id = r.region_id
                        where c1.city_id = :city
                    )
                    select w.wh_id as id,
                           w.wh_adr as address,
                           c.city_id as cityId,
                           c.city_name as cityName,
                           r1.region_id as regionId,
                           r1.region_name as regionName
                    from warehouse w
                    join city c on c.city_id = w.city_id
                    join reg r1 on r1.region_id = c.region_id
                    where c.region_id = r1.region_id
                   """
    )
    WarehouseDto findWarehouseDtoByCityId(@NonNull @Param("city") Integer cityId);
}

package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
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
            value = "select * from warehouse where city_id=:city"
    )
    List<Warehouse> findAllByCity(@NonNull @Param("city") Long city);

    @Query(
            nativeQuery = true,
            value = "select * from warehouse where wh_id=:id"
    )
    Optional<Warehouse> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into warehouse(wh_address, city_id) " +
                    "values (:adr,:cty)"
    )
    Integer create(
            @NonNull @Param("adr") String whAddress,
            @NonNull @Param("cty") Long city);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update warehouse " +
                    "set wh_address=:adr,city_id=:cty " +
                    "where wh_id=:id"
    )
    Integer edit(
            @NonNull @Param("id") Integer id,
            @NonNull @Param("adr") String whAddress,
            @NonNull @Param("cty") Long city);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from warehouse where wh_id=:id"
    )
    void delete(@NonNull @Param("id") Integer id);
}

package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
}

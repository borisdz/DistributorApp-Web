package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Warehouse;
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
            value = ""
    )
    List<Warehouse> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Warehouse> findAllByCity(@NonNull @Param("city") Long city);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Warehouse> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Warehouse> create(String whAddress, Long city);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Warehouse> edit(Long id, String whAddress, Long city);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete();
}

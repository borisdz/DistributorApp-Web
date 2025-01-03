package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from region c"
    )
    List<Region> listAll();

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from region " +
                    "where region_id=:id"
    )
    Optional<Region> findById(@NonNull @Param("id") Integer id);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from region " +
                    "where region.region_name like :name"
    )
    List<Region> findByName(@NonNull @Param("name") String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into region(region_name) " +
                    "values (:name)"
    )
    Integer create(
            @NonNull @Param("name") String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update region " +
                    "set region_name=:name " +
                    "where region_id=:id"
    )
    Integer edit(
            @NonNull @Param("id") Integer id,
            @NonNull @Param("name") String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from region " +
                    "where region_id=:id"
    )
    void deleteById(@NonNull @Param("id") Integer id);
}

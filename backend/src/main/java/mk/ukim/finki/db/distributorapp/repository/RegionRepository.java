package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.RegionDto;
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
                    "where region_id=?1"
    )
    Optional<Region> findById(@NonNull Integer id);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from region " +
                    "where region.region_name like ?1"
    )
    List<Region> findByName(@NonNull String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into region(region_name) " +
                    "values (?1)"
    )
    Integer create(
            @NonNull String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update region " +
                    "set region_name=?2 " +
                    "where region_id=?1"
    )
    Integer edit(
            @NonNull Integer id,
            @NonNull String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from region " +
                    "where region_id=?1"
    )
    void deleteById(@NonNull Integer id);

//    -------------------------------------------------------------------------

    @Query(
            nativeQuery = true,
            value = """
                    select r.region_id as id, r.region_name as name
                    from region r join city c on r.region_id = c.region_id
                    where c.city_id = :city
                    """
    )
    RegionDto getRegionByCityId(@NonNull @Param("city") Integer cityId);
}

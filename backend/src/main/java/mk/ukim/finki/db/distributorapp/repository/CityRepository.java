package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from city c"
    )
    List<City> listAll();

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from city c " +
                    "where c.city_id = :id"
    )
    Optional<City> findById(@NonNull @Param("id") Long id);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from city c " +
                    "where c.city_name like :name"
    )
    List<City> findByName(@NonNull @Param("name") String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into city(city_name, region_id) " +
                    "values (:name,:region)"
    )
    Optional<City> create(
            @NonNull @Param("name") String name,
            @NonNull @Param("region") Integer region);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update city " +
                    "set city_name=:name, region_id=:region " +
                    "where city_id=:id"
    )
    Optional<City> edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("name") String name,
            @NonNull @Param("region") Integer region);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from city c " +
                    "where c.city_id=:id"
    )
    void deleteById(@NonNull @Param("id") Long id);
}

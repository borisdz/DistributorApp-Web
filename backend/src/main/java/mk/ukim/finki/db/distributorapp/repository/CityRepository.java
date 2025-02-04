package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.CityDtoRegister;
import mk.ukim.finki.db.distributorapp.model.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(
            nativeQuery = true,
            value = "select c.* " +
                    "from city c join region r on c.region_id = r.region_id"
    )
    List<City> listAll();

    @Query("select new mk.ukim.finki.db.distributorapp.model.dto.CityDtoRegister(c.cityId,c.cityName) from City c")
    List<CityDtoRegister> findAllCityDtos();

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from city c " +
                    "where c.city_id = ?1"
    )
    @Transactional
    Optional<City> findById(@NonNull Long id);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from city c " +
                    "where c.city_name like ?1"
    )
    List<City> findByName(@NonNull String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into city(city_name, region_id) " +
                    "values (?1,?2)"
    )
    Integer create(
            @NonNull String name,
            @NonNull Integer region);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update city " +
                    "set city_name=?2, region_id=?3 " +
                    "where city_id=?1"
    )
    Integer edit(
            @NonNull Long id,
            @NonNull String name,
            @NonNull Integer region);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from city c " +
                    "where c.city_id=?1"
    )
    void deleteById(@NonNull Long id);
}

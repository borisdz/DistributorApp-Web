package mk.ukim.finki.db.distributorapp.city;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.city.dto.CityDtoRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    select c.city_id as id, c.city_name as name
                    from city c
                    """
    )
    List<CityDtoRegister> findAllCityDtos();

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
            @NonNull Integer id,
            @NonNull String name,
            @NonNull Integer region);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from city c " +
                    "where c.city_id=?1"
    )
    void deleteById(@NonNull Integer id);

    @Query(
            nativeQuery = true,
            value = """
                    with manager_region as (select c.region_id as region
                    from city c
                    join users u on u.user_id=c.city_id
                    where u.user_id = ?1)
                    select c1.city_id as id, c1.city_name as name
                    from city c1 join manager_region mr on mr.region = c1.region_id
                    """
    )
    List<CityDtoRegister> findCitiesForManager(Long id);
}

package mk.ukim.finki.db.distributorapp.repository;

import mk.ukim.finki.db.distributorapp.model.City;
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
            value = "set search_path = \"IND0_185022\"; " +
                    "select * " +
                    "from city c"
    )
    List<City> listAll();

    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    "select * " +
                    "from city c " +
                    "where c.city_id = :id"
    )
    Optional<City> findById(@Param("id") Long id);

    @Query(
            nativeQuery = true,
            value = "set search_path  = \"IND0_185022\"; " +
                    "select * " +
                    "from city c " +
                    "where c.city_name like :name"
    )
    List<City> findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "set search_path  = \"IND0_185022\"; " +
                    "insert into city(city_name) values (:name)"
    )
    Optional<City> create(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "set search_path  = \"IND0_185022\"; " +
                    ""
    )
    Optional<City> edit(Long id, String name);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "set search_path = \"IND0_185022\"; " +
                    "delete from city c " +
                    "where c.city_id=:id"
    )
    void deleteById(@Param("id") Long id);

}

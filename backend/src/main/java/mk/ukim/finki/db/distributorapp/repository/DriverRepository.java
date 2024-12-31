package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from driver"
    )
    List<Driver> listAll();

    @Query(
            nativeQuery = true,
            value = "select d.* from driver d join users u on d.user_id = u.user_id " +
                    "where u.user_name like :name"
    )
    List<Driver> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "select * from driver where user_id=:id"
    )
    Optional<Driver> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into driver(user_id, veh_id) " +
                    "values (:id,:veh)"
    )
    Optional<Driver> create(
            @NonNull @Param("id") Long usr_id,
            @NonNull @Param("veh") Integer veh_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update driver " +
                    "set veh_id=:veh " +
                    "where user_id=:id"
    )
    Optional<Driver> edit(
            @NonNull @Param("id") Long usr_id,
            @NonNull @Param("veh") Integer veh_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from driver where user_id=:id"
    )
    void delete(@NonNull @Param("id") Long id);
}

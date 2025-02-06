package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from driver d join users u on d.user_id = u.user_id"
    )
    List<Driver> listAll();

    @Query(
            nativeQuery = true,
            value = "select d.* from driver d join users u on d.user_id = u.user_id " +
                    "where u.user_name like ?1"
    )
    List<Driver> findAllByName(@NonNull String name);

    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from driver d join users u on d.user_id = u.user_id
                    where d.user_id=?1
                    """
    )
    Optional<Driver> findById(@NonNull Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into driver(user_id, veh_id) " +
                    "values (?1,?2)"
    )
    Integer create(
            @NonNull Long usr_id,
            @NonNull Integer veh_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update driver " +
                    "set veh_id=?2 " +
                    "where user_id=?1"
    )
    Integer edit(
            @NonNull Long usr_id,
            @NonNull Integer veh_id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from driver where user_id=?1"
    )
    void delete(@NonNull Long id);
}

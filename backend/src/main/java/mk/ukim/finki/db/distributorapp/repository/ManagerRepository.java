package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from manager"
    )
    List<Manager> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from manager m join users u on m.user_id = u.user_id " +
                    "where user_name like :name"
    )
    List<Manager> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "select * from manager where user_id=:id"
    )
    Optional<Manager> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into manager(user_id, wh_id) " +
                    "values (:id,:wh)"
    )
    Optional<Manager> create(
            @NonNull @Param("id") Long id,
            @NonNull @Param("wh") Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update manager " +
                    "set wh_id=:wh " +
                    "where user_id=:id"
    )
    Optional<Manager> edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("wh") Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from manager where user_id=:id"
    )
    void delete(@NonNull @Param("id") Long id);
}

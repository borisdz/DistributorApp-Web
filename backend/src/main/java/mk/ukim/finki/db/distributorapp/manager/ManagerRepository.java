package mk.ukim.finki.db.distributorapp.manager;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from manager m join users u on m.user_id = u.user_id"
    )
    List<Manager> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from manager m join users u on m.user_id = u.user_id " +
                    "where user_name like ?1"
    )
    List<Manager> findAllByName(@NonNull String name);

    @Query(
            nativeQuery = true,
            value = """
                    select *
                    from manager m join users u on m.user_id = u.user_id
                    where m.user_id=?1
                    """
    )
    Optional<Manager> findById(@NonNull Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into manager(user_id, wh_id) " +
                    "values (?1,?2)"
    )
    Integer create(
            @NonNull Long id,
            @NonNull Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update manager " +
                    "set wh_id=?2 " +
                    "where user_id=?1"
    )
    Integer edit(
            @NonNull Long id,
            @NonNull Integer whId);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from manager where user_id=?1"
    )
    void delete(@NonNull Long id);
}

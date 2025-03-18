package mk.ukim.finki.db.distributorapp.manager;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
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

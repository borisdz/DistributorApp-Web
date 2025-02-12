package mk.ukim.finki.db.distributorapp.proForma;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProFormaStatusRepository extends JpaRepository<ProFormaStatus, Short> {
    @Query(
            nativeQuery = true,
            value = "select * from pro_forma_status"
    )
    List<ProFormaStatus> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from pro_forma_status where pf_status_name like ?1"
    )
    List<ProFormaStatus> findAllByName(@NonNull String name);

    @Query(
            nativeQuery = true,
            value = "select * from pro_forma_status where pf_status_id=?1"
    )
    Optional<ProFormaStatus> findById(@NonNull Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into pro_forma_status(pf_status_name, pf_status_desc) " +
                    "values (?1,?2)"
    )
    Integer create(@NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update pro_forma_status " +
                    "set pf_status_name=?2,pf_status_desc=?3 " +
                    "where pf_status_id=?1"
    )
    Integer edit(@NonNull Short id, @NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from pro_forma_status where pf_status_id=?1"
    )
    void delete(@NonNull Short id);
}

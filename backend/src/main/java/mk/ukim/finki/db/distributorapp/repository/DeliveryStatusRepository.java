package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Short> {
    @Query(
            nativeQuery = true,
            value = "select * from delivery_status"
    )
    List<DeliveryStatus> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from delivery_status where del_status_name like ?1"
    )
    List<DeliveryStatus> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "select * from delivery_status where del_status_id=?1"
    )
    Optional<DeliveryStatus> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into delivery_status(del_status_name, del_status_desc) " +
                    "values (?1,?2)"
    )
    Optional<DeliveryStatus> create(@NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update delivery_status " +
                    "set del_status_name=?2,del_status_desc=?3 " +
                    "where del_status_id=?1"
    )
    Optional<DeliveryStatus> edit(@NonNull Short id, @NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from delivery_status where del_status_id=?1"
    )
    void delete(Short id);
}

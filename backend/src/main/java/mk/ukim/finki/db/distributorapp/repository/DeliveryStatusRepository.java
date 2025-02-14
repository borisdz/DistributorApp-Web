package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Short> {

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into delivery_status(d_status_name, d_status_desc) " +
                    "values (?1,?2)"
    )
    Integer create(@NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update delivery_status " +
                    "set d_status_name=?2,d_status_desc=?3 " +
                    "where d_status_id=?1"
    )
    Integer edit(@NonNull Short id, @NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from delivery_status where d_status_id=?1"
    )
    void delete(@NonNull Short id);
}

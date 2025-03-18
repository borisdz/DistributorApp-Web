package mk.ukim.finki.db.distributorapp.orderStatus;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Short> {
    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into order_status(o_status_name, o_status_desc) " +
                    "values (?1,?2)"
    )
    Integer create(@NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update order_status " +
                    "set o_status_name=?2,o_status_desc=?3 " +
                    "where o_status_id=?1"
    )
    Integer edit(@NonNull Short id, @NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from order_status where o_status_id=?1"
    )
    void delete(@NonNull Short id);
}

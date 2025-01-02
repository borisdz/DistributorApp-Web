package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Short> {
    @Query(
            nativeQuery = true,
            value = "select * from order_status"
    )
    List<OrderStatus> listAll();

    @Query(
            nativeQuery = true,
            value = "select * from order_status where ord_status_name like ?1"
    )
    List<OrderStatus> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "select * from order_status where ord_status_id=?1"
    )
    Optional<OrderStatus> findById(@NonNull @Param("id") Short id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into order_status(ord_status_name, ord_status_desc) " +
                    "values (?1,?2)"
    )
    Optional<OrderStatus> create(@NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update order_status " +
                    "set ord_status_name=?2,ord_status_desc=?3 " +
                    "where ord_status_id=?1"
    )
    Optional<OrderStatus> edit(@NonNull Short id, @NonNull String name, @NonNull String description);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from order_status where ord_status_id=?1"
    )
    void delete(@NonNull Short id);
}

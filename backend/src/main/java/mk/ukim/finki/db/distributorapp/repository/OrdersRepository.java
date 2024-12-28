package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from orders"
    )
    List<Orders> listAll();

    @Query(
            nativeQuery = true,
            value = ""
    )
    List<Orders> findByCustomer(@NonNull @Param("cust") Long id);

    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Orders> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Orders> create(
            LocalDate ord_date,
            Integer ord_sum,
            LocalDateTime ord_fulfillment_date,
            String ord_comment,
            Short ord_status_id,
            Long cust_id,
            Long del_id,
            Long pf_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    Optional<Orders> edit(
            Long id,
            LocalDate ord_date,
            Integer ord_sum,
            LocalDateTime ord_fulfillment_date,
            String ord_comment,
            Short ord_status_id,
            Long cust_id,
            Long del_id,
            Long pf_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = ""
    )
    void delete();
}

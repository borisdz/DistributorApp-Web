package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Orders;
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
            value = "select * from orders where cust_id=:cust"
    )
    List<Orders> findByCustomer(@NonNull @Param("cust") Long id);

    @Query(
            nativeQuery = true,
            value = "select * from orders where ord_id=:id"
    )
    Optional<Orders> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into orders (ord_date, ord_sum, ord_fulfillment_date, ord_comment, ord_status_id, cust_id, del_id, pf_id) " +
                    "values (:date,:sum,:fulDate,:comment,:status,:cust,:del,:pf)"
    )
    Integer create(
            @NonNull @Param("date") LocalDate ord_date,
            @NonNull @Param("sum") Integer ord_sum,
            @Param("fulDate") LocalDateTime ord_fulfillment_date,
            @Param("comment") String ord_comment,
            @NonNull @Param("status") Short ord_status_id,
            @NonNull @Param("cust") Long cust_id,
            @NonNull @Param("del") Long del_id,
            @NonNull @Param("pf") Long pf_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update orders " +
                    "set ord_date=:date,ord_sum=:sum,ord_fulfillment_date=:fulDate,ord_comment=:comment,ord_status_id=:status,cust_id=:cust,del_id=:del,pf_id=:pf " +
                    "where ord_id=:id"
    )
    Integer edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("date") LocalDate ord_date,
            @NonNull @Param("sum") Integer ord_sum,
            @Param("fulDate") LocalDateTime ord_fulfillment_date,
            @Param("comment") String ord_comment,
            @NonNull @Param("status") Short ord_status_id,
            @NonNull @Param("cust") Long cust_id,
            @NonNull @Param("del") Long del_id,
            @NonNull @Param("pf") Long pf_id
    );

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from orders where ord_id=:id"
    )
    void delete(@NonNull @Param("id") Long id);
}

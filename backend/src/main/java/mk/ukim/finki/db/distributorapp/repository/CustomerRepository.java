package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from customer c join users u on c.user_id = u.user_id"
    )
    List<Customer> listAll();

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from customer c " +
                    "where cust_company_name like ?1"
    )
    List<Customer> findAllByName(@NonNull String name);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from customer " +
                    "where user_id=?1"
    )
    Optional<Customer> findById(@NonNull Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into customer(user_id, cust_edb, cust_company_name, cust_address, cust_open_time, cust_close_time, cust_representative_img) " +
                    "values (?1,?2,?3,?4,?5,?6,?7)"
    )
    Integer create(
            @NonNull Long id,
            @NonNull String customerEDB,
            @NonNull String customerName,
            @NonNull String customerStreet,
            @NonNull LocalTime openTime,
            @NonNull LocalTime closeTime,
            @NonNull String customerImage);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update customer " +
                    "set cust_edb=?2,cust_company_name=?3,cust_address=?4,cust_open_time=?5,cust_close_time=?6,cust_representative_img=?7 " +
                    "where user_id=?1"
    )
    Integer edit(
            @NonNull Long id,
            @NonNull String customerEDB,
            @NonNull String customerName,
            @NonNull String customerStreet,
            @NonNull LocalTime openTime,
            @NonNull LocalTime closeTime,
            @NonNull String customerImage);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from customer " +
                    "where user_id=?!"
    )
    void delete(@NonNull Long id);
}

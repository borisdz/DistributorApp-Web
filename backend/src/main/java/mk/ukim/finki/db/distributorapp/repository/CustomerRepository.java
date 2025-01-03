package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from customer"
    )
    List<Customer> listAll();

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from customer c " +
                    "where cust_company_name like :name"
    )
    List<Customer> findAllByName(@NonNull @Param("name") String name);

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "from customer " +
                    "where user_id=:id"
    )
    Optional<Customer> findById(@NonNull @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "insert into customer(user_id, cust_edb, cust_company_name, cust_address, cust_open_time, cust_close_time, cust_representative_img) " +
                    "values (:id,:edb,:name,:adr,:oTime,:cTime,:img)"
    )
    Integer create(
            @NonNull @Param("id") Long id,
            @NonNull @Param("edb") String customerEDB,
            @NonNull @Param("name") String customerName,
            @NonNull @Param("adr") String customerStreet,
            @NonNull @Param("oTime") LocalTime openTime,
            @NonNull @Param("cTime") LocalTime closeTime,
            @NonNull @Param("img") String customerImage);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update customer " +
                    "set cust_edb=:edb,cust_company_name=:name,cust_address=:adr,cust_open_time=:oTime,cust_close_time=:cTime,cust_representative_img=:img " +
                    "where user_id=:id"
    )
    Integer edit(
            @NonNull @Param("id") Long id,
            @NonNull @Param("edb") String customerEDB,
            @NonNull @Param("name") String customerName,
            @NonNull @Param("adr") String customerStreet,
            @NonNull @Param("oTime") LocalTime openTime,
            @NonNull @Param("cTime") LocalTime closeTime,
            @NonNull @Param("img") String customerImage);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "delete from customer " +
                    "where user_id=:id"
    )
    void delete(@NonNull @Param("id") Long id);
}

package mk.ukim.finki.db.distributorapp.repository;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.model.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            value = """
                    select c.user_id as id,
                           u.user_name as name,
                           u.user_email as email,
                           u.user_mobile as phone,
                           c.cust_edb as edb,
                           c.cust_company_name as compName,
                           c.cust_adr as address,
                           c.cust_representative_img
                    from customer c
                    join users u on c.user_id = u.user_id
                    """
    )
    CustomerDto findCustomerById(@NonNull Long id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = """
                    insert into customer(user_id, cust_edb, cust_company_name, cust_adr, cust_representative_img)
                    values (?1,?2,?3,?4,?5)
                    """
    )
    Integer create(
            @NonNull Long id,
            @NonNull String customerEDB,
            @NonNull String customerName,
            @NonNull String customerStreet,
            @NonNull String customerImage);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update customer " +
                    "set cust_edb=?2,cust_company_name=?3,cust_adr=?4,cust_representative_img=?5 " +
                    "where user_id=?1"
    )
    Integer edit(
            @NonNull Long id,
            @NonNull String customerEDB,
            @NonNull String customerName,
            @NonNull String customerStreet,
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

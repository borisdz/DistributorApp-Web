package mk.ukim.finki.db.distributorapp.customer;

import lombok.NonNull;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerFullDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    select cust.user_id as id,
                           u.user_name as name,
                           u.user_email as email,
                           u.user_mobile as phone,
                           cust.cust_edb as edb,
                           cust.cust_company_name as compName,
                           cust.cust_adr as address,
                           cust.cust_representative_img
                    from customer cust
                    join users u on cust.user_id = u.user_id
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
                    "where user_id=?1"
    )
    void delete(@NonNull Long id);

    @Query(
            value = """
                    select cust.user_id
                      from customer cust
                      join users u on cust.user_id = u.user_id
                     where u.user_email = ?1
                    """,
            nativeQuery = true
    )
    Long findCustomerIdByEmail(String email);

    @Query(
            nativeQuery = true,
            value = """
                    select u.user_id as id,
                           u.user_name as firstName,
                           u.user_surname as lastName,
                           u.user_email as email,
                           u.user_mobile as phone,
                           u.user_image as image,
                           cty.city_id as cityId,
                           cty.city_name as cityName,
                           r.region_name as regionName,
                           u.user_role as role,
                           u.clazz_ as clazz_,
                           u.user_active as userActive,
                           cust.cust_edb as edb,
                           cust.cust_company_name as compName,
                           cust.cust_adr as address,
                           cust.cust_representative_img as representativeImg
                    from customer cust
                    join users u on cust.user_id = u.user_id
                    join city cty on u.city_id = cty.city_id
                    join region r on cty.region_id = r.region_id
                    where u.user_email = ?1
                    """
    )
    CustomerFullDto getCustomerProfile(String userEmail);

    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = """
                    update customer
                    set cust_edb=:edb, cust_company_name=:compName, cust_representative_img=:repImg
                    where user_id=:id
                    """
    )
    void updateCustomer(
            @Param(value = "id") Long id,
            @Param(value = "edb") String edb,
            @Param(value = "compName") String compName,
            @Param(value = "repImg") String repImage);

    @Query(
            nativeQuery = true,
            value = """
                    update customer
                    set cust_edb=:edb, cust_company_name=:compName
                    where user_id=:id
                    """
    )
    @Transactional
    @Modifying
    void updateCustomerDetails(
            @Param(value = "id") Long id,
            @Param(value = "edb") String edb,
            @Param(value = "compName") String compName);
}

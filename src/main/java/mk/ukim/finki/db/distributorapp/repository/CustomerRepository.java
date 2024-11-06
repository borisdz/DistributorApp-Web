package mk.ukim.finki.db.distributorapp.repository;

import mk.ukim.finki.db.distributorapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from CUSTOMER u where u.user_id={id}}",
    nativeQuery = true)
    Optional<Customer> findById(Long id);
}

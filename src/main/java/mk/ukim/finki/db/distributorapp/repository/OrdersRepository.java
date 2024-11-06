package mk.ukim.finki.db.distributorapp.repository;

import mk.ukim.finki.db.distributorapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Long> {
    @Query(value = "select * from ORDERS",
            nativeQuery = true)
    List<Order> findAll();
}

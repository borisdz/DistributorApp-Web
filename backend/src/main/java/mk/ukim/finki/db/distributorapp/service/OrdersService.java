package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Customer;
import mk.ukim.finki.db.distributorapp.model.Delivery;
import mk.ukim.finki.db.distributorapp.model.Orders;
import mk.ukim.finki.db.distributorapp.model.ProForma;
import mk.ukim.finki.db.distributorapp.model.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdersService {
    List<Orders> getAllOrders();

    List<Orders> findOrdersByCustomer(Customer customer);

    Optional<Orders> findById(Long id);

    Optional<Orders> create(
            LocalDate ord_date,
            Integer ord_sum,
            LocalDateTime ord_fulfillment_date,
            String ord_comment,
            OrderStatus ord_status,
            Customer customer,
            Delivery delivery,
            ProForma proForma
    );

    Optional<Orders> edit(
            Long id,
            LocalDate ord_date,
            Integer ord_sum,
            LocalDateTime ord_fulfillment_date,
            String ord_comment,
            OrderStatus ord_status,
            Customer customer,
            Delivery delivery,
            ProForma proForma
    );

    void deleteById(Long id);
}

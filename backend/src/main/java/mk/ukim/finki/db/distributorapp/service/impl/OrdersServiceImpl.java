package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Customer;
import mk.ukim.finki.db.distributorapp.model.Delivery;
import mk.ukim.finki.db.distributorapp.model.Orders;
import mk.ukim.finki.db.distributorapp.model.ProForma;
import mk.ukim.finki.db.distributorapp.model.OrderStatus;
import mk.ukim.finki.db.distributorapp.repository.OrdersRepository;
import mk.ukim.finki.db.distributorapp.service.OrdersService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Orders> getAllOrders() {
        return this.ordersRepository.listAll();
    }

    @Override
    public List<Orders> findOrdersByCustomer(Customer customer) {
        return this.ordersRepository.findByCustomer(customer.getUserId());
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return this.ordersRepository.findById(id);
    }

    @Override
    public Optional<Orders> create(LocalDate ord_date, Integer ord_sum, LocalDateTime ord_fulfillment_date, String ord_comment, OrderStatus ord_status, Customer customer, Delivery delivery, ProForma proForma) {
        return this.ordersRepository.create(
                ord_date,
                ord_sum,
                ord_fulfillment_date,
                ord_comment,
                ord_status.getOrderStatusId(),
                customer.getUserId(),
                delivery.getDeliveryId(),
                proForma.getProFormaId()
        );
    }

    @Override
    public Optional<Orders> edit(Long id, LocalDate ord_date, Integer ord_sum, LocalDateTime ord_fulfillment_date, String ord_comment, OrderStatus ord_status, Customer customer, Delivery delivery, ProForma proForma) {
        return this.ordersRepository.edit(
                id,
                ord_date,
                ord_sum,
                ord_fulfillment_date,
                ord_comment,
                ord_status.getOrderStatusId(),
                customer.getUserId(),
                delivery.getDeliveryId(),
                proForma.getProFormaId()
        );
    }

    @Override
    public void deleteById(Long id) {
        this.ordersRepository.deleteById(id);
    }
}

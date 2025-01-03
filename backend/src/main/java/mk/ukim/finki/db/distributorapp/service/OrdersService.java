package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.OrdersDto;
import mk.ukim.finki.db.distributorapp.model.entities.Customer;

import java.util.List;

public interface OrdersService {
    List<OrdersDto> getAllOrders();

    List<OrdersDto> findOrdersByCustomer(Customer customer);

    OrdersDto findById(Long id);

    Integer create(OrdersDto ordersDto);

    Integer edit(OrdersDto ordersDto);

    void deleteById(Long id);
}

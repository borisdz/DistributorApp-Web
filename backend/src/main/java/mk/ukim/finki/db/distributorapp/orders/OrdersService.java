package mk.ukim.finki.db.distributorapp.orders;

import mk.ukim.finki.db.distributorapp.orders.dto.CreateOrderDto;
import mk.ukim.finki.db.distributorapp.orders.dto.OrderManagerDto;
import mk.ukim.finki.db.distributorapp.orders.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.orders.dto.OrdersDto;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

public interface OrdersService {
    List<OrdersDto> getAllOrders();

    List<OrdersDto> findOrdersByCustomer(Long customerId);

    List<OrderSimpleDto> findSimpleOrdersByCustoemr(Long customerId);

    OrdersDto findById(Long id);

    @Transactional
    Integer create(CreateOrderDto createOrderDto, Principal principal);

    Integer edit(OrdersDto ordersDto);

    void deleteById(Long id);

    List<OrdersDto> findCurrentOrdersByCustomer(Long customerId);

    List<OrderManagerDto> getNewOrdersByManager(Long managerId);

    void addOrdersToDelivery(List<Long> orders, Long delId);
}

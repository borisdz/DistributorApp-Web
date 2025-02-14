package mk.ukim.finki.db.distributorapp.order;

import mk.ukim.finki.db.distributorapp.order.dto.CreateOrderDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderManagerDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrdersDto;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

public interface OrdersService {

    List<OrderSimpleDto> findSimpleOrdersByCustomer(Long customerId);

    OrdersDto findById(Long id);

    @Transactional
    Integer create(CreateOrderDto createOrderDto, Principal principal);

    Integer edit(OrdersDto ordersDto);

    void deleteById(Long id);

    List<OrdersDto> findCurrentOrdersByCustomer(Long customerId);

    List<OrderManagerDto> getNewOrdersByManager(Long managerId);

    void addOrdersToDelivery(List<Long> orderIds, Long deliveryId);
}

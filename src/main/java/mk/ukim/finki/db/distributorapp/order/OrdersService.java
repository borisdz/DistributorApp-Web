package mk.ukim.finki.db.distributorapp.order;

import mk.ukim.finki.db.distributorapp.order.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrdersService {

    List<OrderSimpleDto> findSimpleOrdersByCustomer(Long customerId);

    OrdersDto findById(Long id);

    @Transactional
    Integer create(CreateOrderDto createOrderDto, String userEmail);

    Integer edit(OrdersDto ordersDto);

    void deleteById(Long id);

    List<OrdersDto> findCurrentOrdersByCustomer(Long customerId);

    List<OrderSimpleDto> getNewOrdersByManager(Long managerId);

    void addOrdersToDelivery(List<Long> orderIds, Long deliveryId);

    OrderSimpleDto findSimpleOrderById(Long orderId);

    List<OrdersDto> findOrdersByDelivery(Long deliveryId);

    List<OrdersDeliveryDto> findDeliveryOrdersByDelivery(Long deliveryId);

    List<OrderSimpleDto> getUnassignedOrdersByCitiesForWarehouse(List<Integer> cityIdList, Integer id);
}

package mk.ukim.finki.db.distributorapp.orders;

import mk.ukim.finki.db.distributorapp.orders.dto.OrderStatusDto;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatusDto> getAllOrderStatus();

    OrderStatusDto  getOrderStatusById(Short id);

    Integer create(OrderStatusDto orderStatusDto);

    Integer edit(OrderStatusDto orderStatusDto);

    List<OrderStatusDto> getOrderStatusByName(String name);

    void deleteById(Short id);
}

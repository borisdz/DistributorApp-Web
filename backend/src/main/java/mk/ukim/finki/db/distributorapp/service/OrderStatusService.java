package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.OrderStatusDto;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatusDto> listOrderStatus();

    OrderStatusDto  getOrderStatusById(Short id);

    Integer create(OrderStatusDto orderStatusDto);

    Integer edit(OrderStatusDto orderStatusDto);

    List<OrderStatusDto> getOrderStatusByName(String name);

    void delete(Short id);
}

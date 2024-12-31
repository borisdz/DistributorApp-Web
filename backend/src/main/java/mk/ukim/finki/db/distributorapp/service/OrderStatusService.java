package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderStatusService {
    List<OrderStatus> listOrderStatus();

    Optional<OrderStatus> getOrderStatusById(Short id);

    Optional<OrderStatus> createOrderStatus(String name, String description);

    Optional<OrderStatus> updateOrderStatus(Short id, String name, String description);

    List<OrderStatus> getOrderStatusByName(String name);

    void delete(Short id);
}

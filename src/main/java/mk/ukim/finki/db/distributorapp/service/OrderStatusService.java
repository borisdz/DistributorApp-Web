package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.statuses.Order_Status;

import java.util.List;
import java.util.Optional;

public interface OrderStatusService {
    List<Order_Status> listOrderStatus();

    Optional<Order_Status> getOrderStatusById(Short id);

    Optional<Order_Status> createOrderStatus(String name, String description);

    Optional<Order_Status> updateOrderStatus(Short id, String name, String description);

    List<Order_Status> getOrderStatusByName(String name);

    void delete(Short id);
}

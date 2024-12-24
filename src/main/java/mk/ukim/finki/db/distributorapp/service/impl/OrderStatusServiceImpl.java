package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.statuses.Order_Status;
import mk.ukim.finki.db.distributorapp.repository.OrderStatusRepository;
import mk.ukim.finki.db.distributorapp.service.OrderStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }


    @Override
    public List<Order_Status> listOrderStatus() {
        return this.orderStatusRepository.findAll();
    }

    @Override
    public Optional<Order_Status> getOrderStatusById(Short id) {
        return this.orderStatusRepository.findById(id);
    }

    @Override
    public Optional<Order_Status> createOrderStatus(String name, String description) {
        return this.orderStatusRepository.create(name, description);
    }

    @Override
    public Optional<Order_Status> updateOrderStatus(Short id, String name, String description) {
        return this.orderStatusRepository.edit(id, name, description);
    }

    @Override
    public List<Order_Status> getOrderStatusByName(String name) {
        return this.orderStatusRepository.findAllByName(name);
    }

    @Override
    public void delete(Short id) {
        this.orderStatusRepository.deleteById(id);
    }
}

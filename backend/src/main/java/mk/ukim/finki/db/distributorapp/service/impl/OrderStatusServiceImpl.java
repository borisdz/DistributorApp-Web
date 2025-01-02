package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.entities.OrderStatus;
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
    public List<OrderStatus> listOrderStatus() {
        return this.orderStatusRepository.findAll();
    }

    @Override
    public Optional<OrderStatus> getOrderStatusById(Short id) {
        return this.orderStatusRepository.findById(id);
    }

    @Override
    public Optional<OrderStatus> createOrderStatus(String name, String description) {
        return this.orderStatusRepository.create(name, description);
    }

    @Override
    public Optional<OrderStatus> updateOrderStatus(Short id, String name, String description) {
        return this.orderStatusRepository.edit(id, name, description);
    }

    @Override
    public List<OrderStatus> getOrderStatusByName(String name) {
        return this.orderStatusRepository.findAllByName(name);
    }

    @Override
    public void delete(Short id) {
        this.orderStatusRepository.deleteById(id);
    }
}

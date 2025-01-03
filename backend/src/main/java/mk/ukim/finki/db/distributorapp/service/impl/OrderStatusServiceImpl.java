package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.dto.OrderStatusDto;
import mk.ukim.finki.db.distributorapp.model.entities.OrderStatus;
import mk.ukim.finki.db.distributorapp.repository.OrderStatusRepository;
import mk.ukim.finki.db.distributorapp.service.OrderStatusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    private List<OrderStatusDto> buildDto(List<OrderStatus> orderStatuses) {
        List<OrderStatusDto> dtos = new ArrayList<>();
        for (OrderStatus orderStatus : orderStatuses) {
            OrderStatusDto dto = new OrderStatusDto(
                    orderStatus.getOrderStatusId(),
                    orderStatus.getOrderStatusName(),
                    orderStatus.getOrderStatusDescription()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<OrderStatusDto> listOrderStatus() {
        List<OrderStatus> orderStatuses = this.orderStatusRepository.findAll();
        return buildDto(orderStatuses);
    }

    @Override
    public OrderStatusDto getOrderStatusById(Short id) {
        OrderStatus orderStatus = this.orderStatusRepository.findById(id).get();

        return new OrderStatusDto(
                orderStatus.getOrderStatusId(),
                orderStatus.getOrderStatusName(),
                orderStatus.getOrderStatusDescription()
        );
    }

    @Override
    public Integer create(OrderStatusDto orderStatusDto) {
        return this.orderStatusRepository.create(
                orderStatusDto.getStatusName(),
                orderStatusDto.getStatusDescription()
        );
    }

    @Override
    public Integer edit(OrderStatusDto orderStatusDto) {
        return this.orderStatusRepository.edit(
                orderStatusDto.getId(),
                orderStatusDto.getStatusName(),
                orderStatusDto.getStatusDescription()
        );
    }

    @Override
    public List<OrderStatusDto> getOrderStatusByName(String name) {
        List<OrderStatus> orderStatuses = this.orderStatusRepository.findAllByName("'" + name + "'");
        return buildDto(orderStatuses);
    }

    @Override
    public void delete(Short id) {
        this.orderStatusRepository.deleteById(id);
    }
}

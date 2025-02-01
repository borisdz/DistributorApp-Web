package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.OrdersDto;
import mk.ukim.finki.db.distributorapp.model.entities.Customer;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;
import mk.ukim.finki.db.distributorapp.model.entities.Orders;
import mk.ukim.finki.db.distributorapp.repository.OrdersRepository;
import mk.ukim.finki.db.distributorapp.service.OrdersService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;


    private List<OrdersDto> buildDto(List<Orders> orders) {
        List<OrdersDto> dtos = new ArrayList<>();
        for (Orders ord : orders) {
            OrdersDto orderDto = new OrdersDto(
                    ord.getOrderId(),
                    ord.getOrderDate(),
                    ord.getOrderSum(),
                    ord.getOrderFulfillmentDate(),
                    ord.getOrderComment(),
                    ord.getOrderStatus().getOrderStatusId(),
                    ord.getOrderStatus().getOrderStatusName(),
                    ord.getCustomer().getUserId(),
                    ord.getCustomer().getCustomerCompanyName(),
                    ord.getCustomer().getUserMobile(),
                    ord.getCustomer().getUserEmail(),
                    ord.getDelivery().getDeliveryId(),
                    ord.getDelivery().getVehicle().getDriver().getUserId(),
                    ord.getDelivery().getVehicle().getDriver().getUsername(),
                    ord.getDelivery().getVehicle().getDriver().getUserMobile(),
                    ord.getDelivery().getVehicle().getDriver().getUserEmail(),
                    ord.getProForma().getProFormaId(),
                    ord.getProForma().getProFormaStatus().getProFormaStatusName()
            );
            dtos.add(orderDto);
        }
        return dtos;
    }

    @Override
    public List<OrdersDto> getAllOrders() {
        List<Orders> orders = this.ordersRepository.listAll();
        return buildDto(orders);
    }

    @Override
    public List<OrdersDto> findOrdersByCustomer(Customer customer) {
        List<Orders> orders = this.ordersRepository.findByCustomer(customer.getUserId());
        return buildDto(orders);
    }

    @Override
    public OrdersDto findById(Long id) {
        Orders ord = this.ordersRepository.findById(id).get();
        return new OrdersDto(
                ord.getOrderId(),
                ord.getOrderDate(),
                ord.getOrderSum(),
                ord.getOrderFulfillmentDate(),
                ord.getOrderComment(),
                ord.getOrderStatus().getOrderStatusId(),
                ord.getOrderStatus().getOrderStatusName(),
                ord.getCustomer().getUserId(),
                ord.getCustomer().getCustomerCompanyName(),
                ord.getCustomer().getUserMobile(),
                ord.getCustomer().getUserEmail(),
                ord.getDelivery().getDeliveryId(),
                ord.getDelivery().getVehicle().getDriver().getUserId(),
                ord.getDelivery().getVehicle().getDriver().getUsername(),
                ord.getDelivery().getVehicle().getDriver().getUserMobile(),
                ord.getDelivery().getVehicle().getDriver().getUserEmail(),
                ord.getProForma().getProFormaId(),
                ord.getProForma().getProFormaStatus().getProFormaStatusName()
        );
    }

    @Override
    public Integer create(OrdersDto ordersDto) {
        return this.ordersRepository.create(
                ordersDto.getOrdDate(),
                ordersDto.getOrdSum(),
                ordersDto.getOrderFulfillmentDate(),
                ordersDto.getOrdComment(),
                ordersDto.getOStatusId(),
                ordersDto.getCustomerId(),
                ordersDto.getDeliveryId(),
                ordersDto.getPfId()
        );
    }

    @Override
    public Integer edit(OrdersDto ordersDto) {
        return this.ordersRepository.edit(
                ordersDto.getId(),
                ordersDto.getOrdDate(),
                ordersDto.getOrdSum(),
                ordersDto.getOrderFulfillmentDate(),
                ordersDto.getOrdComment(),
                ordersDto.getOStatusId(),
                ordersDto.getCustomerId(),
                ordersDto.getDeliveryId(),
                ordersDto.getPfId()
        );
    }

    @Override
    public void deleteById(Long id) {
        this.ordersRepository.deleteById(id);
    }

    @Override
    public List<OrdersDto> findCurrentOrdersByCustomer(Customer customer) {
        List<Orders> orders = this.ordersRepository.getCurrentOrdersByCustomer(customer.getUserId());
        return buildDto(orders);
    }

    @Override
    public List<OrdersDto> getNewOrdersByManager(Manager manager) {
        List<Orders> orders = this.ordersRepository.getNewOrdersByManager(manager.getUserId());
        return buildDto(orders);
    }
}

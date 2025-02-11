package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.*;
import mk.ukim.finki.db.distributorapp.model.entities.Orders;
import mk.ukim.finki.db.distributorapp.repository.OrdersRepository;
import mk.ukim.finki.db.distributorapp.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final UsersService usersService;
    private final WarehouseService warehouseService;
    private final ArticleUnitService articleUnitService;
    private final CustomerService customerService;
    private final ProFormaService proFormaService;



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
        return this.ordersRepository.listAll();
    }

    @Override
    public List<OrdersDto> findOrdersByCustomer(Long customerId) {
        return this.ordersRepository.findOrdersByCustomer(customerId);
    }

    @Override
    public List<OrderSimpleDto> findSimpleOrdersByCustoemr(Long customerId){
        return this.ordersRepository.findSimpleOrdersByCustomer(customerId);
    }

    @Override
    public OrdersDto findById(Long id) {
        return  this.ordersRepository.findOrderById(id);
    }

    @Override
    @Transactional
    public Integer create(CreateOrderDto createOrderDto, Principal principal) {
        String userEmail = principal.getName();
        UserDto user = this.usersService.findUserDtoByEmail(userEmail);
        CustomerDto customer = this.customerService.findCustomerById(user.getId());

        OrdersDto order = new OrdersDto();

        if(createOrderDto.isProForma()){
            ProFormaDto pf = new ProFormaDto();
            pf.setPfDeadline(LocalDate.now().plusWeeks(1));
            pf.setPfDateCreated(LocalDate.now());
            pf.setStatusId((short)1);
            this.proFormaService.create(pf);
            List<ProFormaDto> proFormaList = this.proFormaService.getAllProForma();
            ProFormaDto createdProForma = proFormaList.get(proFormaList.size()-1);
            order.setPfId(createdProForma.getId());
        }

        order.setCustomerId(customer.getId());
        order.setOrdDate(LocalDate.now());
        order.setOrdFulfillmentDate(null);
        order.setOStatusId((short) 1);
        order.setDeliveryId(null);
        order.setPfId(null);
        order.setOrdComment(null);

        Integer sum = createOrderDto.getOrderItems()
                .stream()
                .map(i->i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add)
                .intValue();
        order.setOrdSum(sum);

        return this.ordersRepository.create(
                order.getOrdDate(),
                order.getOrdSum(),
                order.getOrdFulfillmentDate(),
                order.getOrdComment(),
                order.getOStatusId(),
                order.getCustomerId(),
                order.getDeliveryId(),
                order.getPfId()
        );
    }

    @Override
    public Integer edit(OrdersDto ordersDto) {
        return this.ordersRepository.edit(
                ordersDto.getId(),
                ordersDto.getOrdDate(),
                ordersDto.getOrdSum(),
                ordersDto.getOrdFulfillmentDate(),
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
    public List<OrdersDto> findCurrentOrdersByCustomer(Long customerId) {
        return this.ordersRepository.getCurrentOrdersByCustomer(customerId);
    }

    @Override
    public List<OrdersDto> getNewOrdersByManager(Long managerId) {
         return this.ordersRepository.getNewOrdersByManager(managerId);
    }
}

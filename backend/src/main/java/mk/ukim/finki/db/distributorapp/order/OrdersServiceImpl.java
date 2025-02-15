package mk.ukim.finki.db.distributorapp.order;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.order.dto.CreateOrderDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderManagerDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrdersDto;
import mk.ukim.finki.db.distributorapp.proForma.dto.ProFormaDto;
import mk.ukim.finki.db.distributorapp.customer.CustomerService;
import mk.ukim.finki.db.distributorapp.proForma.ProFormaService;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final UserService userService;
    private final CustomerService customerService;
    private final ProFormaService proFormaService;

    @Override
    public List<OrderSimpleDto> findSimpleOrdersByCustomer(Long customerId) {
        return this.ordersRepository.findSimpleOrdersByCustomer(customerId);
    }

    @Override
    public OrdersDto findById(Long id) {
        return this.ordersRepository.findOrderById(id);
    }

    @Override
    @Transactional
    public Integer create(CreateOrderDto createOrderDto, Principal principal) {
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);
        CustomerDto customer = this.customerService.findCustomerById(user.getId());

        OrdersDto order = new OrdersDto();

        if (createOrderDto.isProForma()) {
            ProFormaDto pf = new ProFormaDto();
            pf.setPfDeadline(LocalDate.now().plusWeeks(1));
            pf.setPfDateCreated(LocalDate.now());
            pf.setStatusId((short) 1);
            this.proFormaService.create(pf);
            List<ProFormaDto> proFormaList = this.proFormaService.getAllProForma();
            ProFormaDto createdProForma = proFormaList.get(proFormaList.size() - 1);
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
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
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
    public List<OrderManagerDto> getNewOrdersByManager(Long managerId) {
        return this.ordersRepository.getNewOrdersByManager(managerId);
    }

    @Override
    public void addOrdersToDelivery(List<Long> orderIds, Long deliveryId) {
        for (Long i : orderIds) {
            OrderSimpleDto order = this.ordersRepository.findSimpleOrdersById(i);
            order.setDeliveryId(deliveryId);
            this.ordersRepository.edit(
                    order.getId(),
                    order.getOrdDate().toLocalDate(),
                    order.getOrdSum(),
                    order.getOrdFulfillmentDate(),
                    order.getOrdComment(),
                    order.getOStatusId(),
                    order.getCustomerId(),
                    order.getDeliveryId(),
                    order.getPfId()
            );
        }
    }
}

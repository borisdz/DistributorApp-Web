package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.customer.CustomerService;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDashboardDto;
import mk.ukim.finki.db.distributorapp.delivery.DeliveryService;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.order.OrdersService;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.proForma.ProFormaService;
import mk.ukim.finki.db.distributorapp.proForma.dto.ProFormaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer")
@CrossOrigin(origins = "*")
public class RestCustomerController {
    private final CustomerService customerService;
    private final OrdersService ordersService;
    private final DeliveryService deliveryService;
    private final ProFormaService proFormaService;

    @GetMapping("/{customerId}/dashboard")
    public ResponseEntity<CustomerDashboardDto> getCustomerDashboard(@PathVariable("customerId") Long customerId) {
        CustomerDashboardDto dashboard = new CustomerDashboardDto();
        List<OrderSimpleDto> userOrders = this.ordersService.findSimpleOrdersByCustomer(customerId);
        List<DeliveryDto> userDeliveries = this.deliveryService.getCurrentDeliveriesByCustomer(customerId);
        List<ProFormaDto> userProFormas = this.proFormaService.getCurentProFormasByCustomer(customerId);
        dashboard.setOrders(userOrders);
        dashboard.setDeliveries(userDeliveries);
        dashboard.setProFormas(userProFormas);
        return ResponseEntity.ok(dashboard);
    }
}

package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.ArticleService;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.articleUnit.ArticleUnitService;
import mk.ukim.finki.db.distributorapp.customer.CustomerService;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerDashboardDto;
import mk.ukim.finki.db.distributorapp.customer.dto.CustomerFullDto;
import mk.ukim.finki.db.distributorapp.delivery.DeliveryService;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.order.OrdersService;
import mk.ukim.finki.db.distributorapp.order.dto.AndroidCreateOrderDto;
import mk.ukim.finki.db.distributorapp.order.dto.CreateOrderDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.proForma.ProFormaService;
import mk.ukim.finki.db.distributorapp.proForma.dto.ProFormaDto;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseService;
import mk.ukim.finki.db.distributorapp.warehouse.dto.WarehouseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer")
@CrossOrigin(origins = "*")
public class RestCustomerController {
    private final ArticleService articleService;
    private final OrdersService ordersService;
    private final DeliveryService deliveryService;
    private final ProFormaService proFormaService;
    private final WarehouseService warehouseService;
    private final UserService userService;
    private final ArticleUnitService articleUnitService;
    private final CustomerService customerService;

    @GetMapping("/{customerId}/dashboard")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
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

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<CustomerFullDto> getProfile(Principal principal) {
        String userEmail = principal.getName();
        CustomerFullDto result = this.customerService.getCustomerProfile(userEmail);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/profile")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<?> updateProfile(@RequestBody CustomerFullDto customerFullDto, Principal principal) {
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);
        CustomerFullDto customer = this.customerService.getCustomerProfile(userEmail);
        if(customerFullDto.getCityId()==null){
            customerFullDto.setCityId(customer.getCityId());
        }
        this.userService.updateUserDetails(
                user.getId(),
                customerFullDto.getFirstName(),
                customerFullDto.getLastName(),
                customerFullDto.getEmail(),
                customerFullDto.getPhone(),
                customerFullDto.getCityId()
        );
        this.customerService.updateCustomerDetails(
                customer.getId(),
                customerFullDto.getEdb(),
                customerFullDto.getCompName()
        );

        return ResponseEntity.ok().build();
    }
}

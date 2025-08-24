package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.customer.CustomerService;
import mk.ukim.finki.db.distributorapp.delivery.Delivery;
import mk.ukim.finki.db.distributorapp.delivery.DeliveryService;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryFullDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryWithOrdersDto;
import mk.ukim.finki.db.distributorapp.driver.DriverService;
import mk.ukim.finki.db.distributorapp.driver.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.order.OrdersService;
import mk.ukim.finki.db.distributorapp.order.dto.OrdersDeliveryDto;
import mk.ukim.finki.db.distributorapp.order.dto.OrdersDto;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RestDeliveryController {
    private final DeliveryService deliveryService;
    private final OrdersService ordersService;
    private final UserService userService;
    private final DriverService driverService;

    @GetMapping("/driver/delivery-with-orders/{deliveryId}")
    @PreAuthorize("hasAnyRole('DRIVER','ADMIN')")
    public ResponseEntity<DeliveryWithOrdersDto> getDeliveryWithOrder(@PathVariable Long deliveryId) {
        DeliveryWithOrdersDto result = new DeliveryWithOrdersDto();
        DeliveryFullDto delivery = this.deliveryService.findDeliveryById(deliveryId);
        result.setDelivery(delivery);
        List<OrdersDeliveryDto> deliveryOrders = this.ordersService.findDeliveryOrdersByDelivery(deliveryId);
        result.setOrders(deliveryOrders);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customer/current-deliveries")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<List<DeliveryDto>> getCurrentDeliveries(Principal principal) {
        String email = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(email);
        List<DeliveryDto> result = this.deliveryService.getCurrentDeliveriesByCustomer(user.getId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/driver/newDeliveries")
    @PreAuthorize("hasAnyRole('DRIVER')")
    public ResponseEntity<?> getNewDeliveries(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getPrincipal().toString();

        UserDto user = this.userService.findUserDtoByEmail(email);

        if(user == null){
            return ResponseEntity.badRequest().body("User not found");
        }

        DriverDto driver = this.driverService.findById(user.getId());
        if(driver == null){
            return ResponseEntity.badRequest().body("Driver not found");
        }

        List<DeliverySimpleDto> deliveries = this.driverService.getNewAssignedDeliveries(driver.getId());

        return ResponseEntity.ok().body(deliveries);
    }
}

package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.DeliveryService;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryCreateDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.manager.ManagerService;
import mk.ukim.finki.db.distributorapp.manager.dto.ManagerDashboardDto;
import mk.ukim.finki.db.distributorapp.manager.dto.ManagerDto;
import mk.ukim.finki.db.distributorapp.order.OrdersService;
import mk.ukim.finki.db.distributorapp.order.dto.OrderSimpleDto;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.vehicle.VehicleService;
import mk.ukim.finki.db.distributorapp.vehicle.dto.VehicleBasicDto;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
@CrossOrigin(origins = "*")
public class RestManagerController {

    private final ManagerService managerService;
    private final OrdersService ordersService;
    private final DeliveryService deliveryService;
    private final UserService userService;
    private final VehicleService vehicleService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<ManagerDashboardDto> getManagerDashboard(Principal principal) {
        ManagerDashboardDto dashboard = new ManagerDashboardDto();
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);
        List<OrderSimpleDto> newOrders = this.ordersService.getNewOrdersByManager(user.getId());
        dashboard.setNewOrders(newOrders);
        List<DeliverySimpleDto> pendingDeliveries = this.deliveryService.getPendingDeliveriesByManager(user.getId());
        dashboard.setPendingDeliveries(pendingDeliveries);
        return ResponseEntity.ok(dashboard);
    }

    @GetMapping("/orders/unassigned")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<List<OrderSimpleDto>> getManagerUnassignedOrders(Principal principal) {
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);
        List<OrderSimpleDto> newOrders = this.ordersService.getNewOrdersByManager(user.getId());
        return ResponseEntity.ok(newOrders);
    }

    @GetMapping("/vehicles")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<List<VehicleBasicDto>> getManagerVehicles(Principal principal) {
        String userEmail = principal.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);
        List<VehicleBasicDto> vehicles = this.vehicleService.getBasicVehiclesByManagerId(user.getId());
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping("/create-delivery")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<?> createDelivery(DeliveryCreateDto newDelivery){
        this.deliveryService.create(newDelivery);

        List<DeliverySimpleDto> deliveries = this.deliveryService.getDeliveriesByVehicle(newDelivery.getVehId());
        DeliverySimpleDto createdDelivery = deliveries.get(0);

        this.ordersService.addOrdersToDelivery(newDelivery.getOrders(), createdDelivery.getDeliveryId());
        return ResponseEntity.ok(createdDelivery.getDeliveryId());
    }
}

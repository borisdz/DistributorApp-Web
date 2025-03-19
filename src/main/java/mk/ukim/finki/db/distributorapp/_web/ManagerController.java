package mk.ukim.finki.db.distributorapp._web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.DeliveryService;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryCreateDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.order.OrdersService;
import mk.ukim.finki.db.distributorapp.users.UserService;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.vehicle.VehicleService;
import mk.ukim.finki.db.distributorapp.warehouse.WarehouseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final UserService userService;
    private final VehicleService vehicleService;
    private final OrdersService ordersService;
    private final WarehouseService warehouseService;
    private final DeliveryService deliveryService;

    @GetMapping({"/dashboard", "/"})
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);

        model.addAttribute("user", user);
        model.addAttribute("currentInventory", warehouseService.getInventoryByManager(user.getId()));
        model.addAttribute("vehicleStatus", vehicleService.getVehiclesByManager(user.getId()));
        model.addAttribute("newOrders", ordersService.getNewOrdersByManager(user.getId()));
        model.addAttribute("currentDeliveries", deliveryService.getCurrentDeliveriesByManager(user.getId()));
        return "home/manager";
    }

    @GetMapping("/all")
    public String allManagers(Model model) {
        return "all-managers";
    }

    @GetMapping("/create-delivery")
    public String createDelivery(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);

        model.addAttribute("user", user);
        model.addAttribute("newDelivery", new DeliveryCreateDto());
        model.addAttribute("newOrders", ordersService.getNewOrdersByManager(user.getId()));
        model.addAttribute("vehicles", vehicleService.getVehiclesByManager(user.getId()));

        return "create-delivery";
    }

    @PostMapping("/create-delivery")
    public String createDelivery(@ModelAttribute DeliveryCreateDto newDelivery) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.userService.findUserDtoByEmail(userEmail);

        this.deliveryService.create(newDelivery);

        List<DeliverySimpleDto> deliveries = this.deliveryService.getDeliveriesByVehicle(newDelivery.getVehId());
        DeliverySimpleDto createdDelivery = deliveries.get(0);

        this.ordersService.addOrdersToDelivery(newDelivery.getOrders(), createdDelivery.getDeliveryId());

        return "redirect:/manager/dashboard";
    }
}

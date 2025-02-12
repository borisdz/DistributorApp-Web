package mk.ukim.finki.db.distributorapp.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    private final UsersService usersService;
    private final VehicleService vehicleService;
    private final OrdersService ordersService;
    private final WarehouseService warehouseService;
    private final DeliveryService deliveryService;


    @GetMapping({"/dashboard","/"})
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.usersService.findUserDtoByEmail(userEmail);

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
    public String createDelivery(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.usersService.findUserDtoByEmail(userEmail);

        return "create-delivery";
    }
}

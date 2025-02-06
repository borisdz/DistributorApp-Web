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
    public String dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.usersService.findUserDtoByEmail(userEmail);

//        model.addAttribute("currentInventory", warehouseService.getInventoryByManager(manager.getId()));
//        model.addAttribute("vehicleStatus", vehicleService.getVehiclesByManager(manager.getId()));
//        model.addAttribute("newOrders", ordersService.getNewOrdersByManager(manager.getId()));
//        model.addAttribute("currentDeliveries", deliveryService.getCurrentDeliveriesByManager(manager.getId()));
        return "home/manager";
    }

    @GetMapping("/all")
    public String allManagers(Model model) {
        return "all-managers";
    }
}

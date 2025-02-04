package mk.ukim.finki.db.distributorapp.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.service.*;
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


    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Users user = this.usersService.findUserByEmail(model.getAttribute("email").toString());
        Manager manager = this.managerService.getManagerByIdObj(user.getUserId());

        model.addAttribute("currentInventory", warehouseService.getInventoryByManager(manager));
        model.addAttribute("vehicleStatus", vehicleService.getVehiclesByManager(manager));
        model.addAttribute("newOrders", ordersService.getNewOrdersByManager(manager));
        model.addAttribute("currentDeliveries", deliveryService.getCurrentDeliveriesByManager(manager));
        return "home/manager";
    }
}

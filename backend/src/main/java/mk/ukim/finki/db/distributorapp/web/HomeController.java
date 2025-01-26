package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.entities.Customer;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final DeliveryService deliveryService;
    private final OrdersService ordersService;
    private final VehicleService vehicleService;
    private final WarehouseService warehouseService;

    public HomeController(DeliveryService deliveryService, OrdersService ordersService, VehicleService vehicleService, WarehouseService warehouseService) {
        this.deliveryService = deliveryService;
        this.ordersService = ordersService;
        this.vehicleService = vehicleService;
        this.warehouseService = warehouseService;
    }

    @GetMapping("/customer")
    public String customerHome(Model model, Customer customer) {
        model.addAttribute("currentOrders", ordersService.findCurrentOrdersByCustomer(customer));
        model.addAttribute("currentDeliveries", deliveryService.getCurrentDeliveriesByCustomer(customer));
        return "home/customer";
    }

    @GetMapping("/manager")
    public String managerHome(Model model, Manager manager) {
        model.addAttribute("currentInventory", warehouseService.getInventoryByManager(manager));
        model.addAttribute("vehicleStatus", vehicleService.getVehiclesByManager(manager));
        model.addAttribute("newOrders", ordersService.getNewOrdersByManager(manager));
        model.addAttribute("currentDeliveries", deliveryService.getCurrentDeliveriesByManager(manager));
        return "home/manager";
    }

    @GetMapping("/driver")
    public String driverHome(Model model, Driver driver) {
        model.addAttribute("newDeliveries", deliveryService.getAllNewDeliveriesByDriver(driver));
        model.addAttribute("doneDeliveries", deliveryService.getAllDeliveriesByDriver(driver));
        return "home/driver";
    }


    @GetMapping
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            model.addAttribute("userType", "Guest");
            return "home";
        }

        Users user = (Users) authentication.getAuthorities();

        switch (user.getUserRole().getAuthority()) {
            case "ROLE_CUSTOMER" -> {
                model.addAttribute("userType", "Customer");
                return "redirect:home/customer";
            }
            case "ROLE_DRIVER" -> {
                model.addAttribute("userType", "Driver");
                return "redirect:home/driver";
            }
            case "ROLE_MANAGER" -> {
                model.addAttribute("userType", "Manager");
                return "redirect:home/manager";
            }
        }
        model.addAttribute("userType", "Guest");
        return "home";
    }
}

package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.entities.Customer;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.service.CustomerService;
import mk.ukim.finki.db.distributorapp.service.DeliveryService;
import mk.ukim.finki.db.distributorapp.service.OrdersService;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final UsersService usersService;
    private final OrdersService ordersService;
    private final DeliveryService deliveryService;

    public CustomerController(CustomerService customerService, UsersService usersService, OrdersService ordersService, DeliveryService deliveryService) {
        this.customerService = customerService;
        this.usersService = usersService;
        this.ordersService = ordersService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Users user = this.usersService.findUserByEmail(userEmail);
        Customer customer = this.customerService.getCustomerObjById(user.getUserId());
        model.addAttribute("customer", customer);
        model.addAttribute("currentOrders", ordersService.findCurrentOrdersByCustomer(customer));
        model.addAttribute("currentDeliveries", deliveryService.getCurrentDeliveriesByCustomer(customer));
        return "home/customer";
    }
}

package mk.ukim.finki.db.distributorapp.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.CustomerDto;
import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
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
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final UsersService usersService;
    private final OrdersService ordersService;
    private final DeliveryService deliveryService;

    @GetMapping({"/dashboard","/"})
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserDto user = this.usersService.findUserDtoByEmail(userEmail);
        CustomerDto customer = this.customerService.findCustomerById(user.getId());
        model.addAttribute("customer", customer);
        model.addAttribute("currentOrders", ordersService.findCurrentOrdersByCustomer(customer.getId()));
        model.addAttribute("currentDeliveries", deliveryService.getCurrentDeliveriesByCustomer(customer.getId()));
        return "home/customer";
    }
}

package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.entities.Customer;
import mk.ukim.finki.db.distributorapp.model.entities.Driver;
import mk.ukim.finki.db.distributorapp.model.entities.Manager;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.service.CustomerService;
import mk.ukim.finki.db.distributorapp.service.DriverService;
import mk.ukim.finki.db.distributorapp.service.ManagerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final CustomerService customerService;
    private final DriverService driverService;
    private final ManagerService managerService;

    public HomeController(CustomerService customerService, DriverService driverService, ManagerService managerService) {
        this.customerService = customerService;
        this.driverService = driverService;
        this.managerService = managerService;
    }

    @GetMapping("/customer")
    public String customerHome(Model model) {
        model.addAttribute("customerData", customerService.getCustomerData());
        return "home/customer";
    }

    @GetMapping("/manager")
    public String managerHome(Model model) {
        model.addAttribute("managerData", managerService.getManagerData());
        return "home/manager";
    }

    @GetMapping("/driver")
    public String driverHome(Model model) {
        model.addAttribute("driverData", driverService.getDriverData());
        return "home/driver";
    }


    @GetMapping
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            model.addAttribute("userType", "Guest");
            return "home";
        }

        Users user = (Users) authentication.getPrincipal();

        if (user instanceof Customer) {
            model.addAttribute("userType", "Customer");
            return "redirect:home/customer";
        } else if (user instanceof Driver) {
            model.addAttribute("userType", "Driver");
            return "redirect:home/driver";
        } else if (user instanceof Manager) {
            model.addAttribute("userType", "Manager");
            return "redirect:home/manager";
        }
        model.addAttribute("userType", "Guest");
        return "home";
    }
}

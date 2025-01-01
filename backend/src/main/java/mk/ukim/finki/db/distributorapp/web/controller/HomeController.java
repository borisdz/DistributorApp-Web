package mk.ukim.finki.db.distributorapp.web.controller;

import mk.ukim.finki.db.distributorapp.model.Customer;
import mk.ukim.finki.db.distributorapp.model.Driver;
import mk.ukim.finki.db.distributorapp.model.Manager;
import mk.ukim.finki.db.distributorapp.model.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

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
            return "redirect:customer/home";
        } else if (user instanceof Driver) {
            model.addAttribute("userType", "Driver");
            return "redirect:driver/home";
        } else if (user instanceof Manager) {
            model.addAttribute("userType", "Manager");
            return "redirect:manager/home";
        }
        model.addAttribute("userType", "Guest");
        return "home";
    }
}

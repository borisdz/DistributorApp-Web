package mk.ukim.finki.db.distributorapp.web.controller;

import mk.ukim.finki.db.distributorapp.model.Customer;
import mk.ukim.finki.db.distributorapp.model.Driver;
import mk.ukim.finki.db.distributorapp.model.Manager;
import mk.ukim.finki.db.distributorapp.model.Users;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UsersService usersService;

    public HomeController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = usersService.getUserByEmail(username).get();

        if(user instanceof Customer){
            return "redirect:customer/home";
        }else if(user instanceof Driver){
            return "redirect:driver/home";
        } else if (user instanceof Manager){
            return "redirect:manager/home";
        }

        return "home";
    }
}

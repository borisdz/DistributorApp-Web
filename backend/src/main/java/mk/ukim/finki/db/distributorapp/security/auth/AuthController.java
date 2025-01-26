package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.entities.City;
import mk.ukim.finki.db.distributorapp.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final CityService cityService;

    public AuthController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerCustomer(Model model) {
        List<City> cities = this.cityService.listCitiesObj();

        model.addAttribute("cities", cities);
        return "register";
    }

    @GetMapping("/changePass")
    public String changePassword(Model model) {
        return "changePass";
    }
}

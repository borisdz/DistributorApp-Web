package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.entities.City;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.db.distributorapp.service.CityService;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UsersService usersService;
    private final CityService cityService;

    public RegisterController(UsersService usersService, CityService cityService) {
        this.usersService = usersService;
        this.cityService = cityService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("bodyContent", "register");
        model.addAttribute("cities", cityService.listCities());
        return "register";
    }

    @PostMapping
    public String register(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String repeatedPassword,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String mobile,
            @RequestParam Long city
    ) {
        try {
            City selectedCity = this.cityService.getCityById(city).get();
            this.usersService.register(name, surname, email, password, repeatedPassword, mobile, selectedCity);
            return "redirect:/login";
        } catch (InvalidArgumentsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}

package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.dto.CityDto;
import mk.ukim.finki.db.distributorapp.model.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp.model.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.db.distributorapp.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final CityService cityService;
    private final AuthService authService;

    public AuthController(CityService cityService, AuthService authService) {
        this.cityService = cityService;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        return "authentication/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto loginRequest, RedirectAttributes redirectAttributes) {
        try{
            Users user = authService.login(loginRequest);

            switch (user.getUserRole()){
                case ROLE_CUSTOMER -> {
                    return "redirect:/home/customer";
                }
                case ROLE_ADMIN -> {
                    return "redirect:/home/admin";
                }
                case ROLE_MANAGER -> {
                    return "redirect:/home/manager";
                }
                case ROLE_DRIVER -> {
                    return "redirect:/home/driver";
                }
                default -> {
                    return "redirect:/home";
                }
            }
        }catch (InvalidUserCredentialsException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials.");
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/register")
    public String registerCustomer(Model model) {
        List<CityDto> cityDtos = this.cityService.listCities();
        model.addAttribute("cityDtos", cityDtos);
        return "authentication/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequestDto registerRequest) throws Exception {
        authService.register(registerRequest);
        return "redirect:/auth/login";
    }

    @GetMapping("/changePass")
    public String changePassword(Model model) {
        return "authentication/changePass";
    }
}

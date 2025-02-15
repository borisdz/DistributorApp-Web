package mk.ukim.finki.db.distributorapp._web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.users.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping({"/", "/home"})
public class HomeController {
    private final UserService userService;

    @GetMapping
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            model.addAttribute("userType", "Guest");
            return "authentication/login";
        }

        String userEmail = authentication.getName();
        if (userEmail.equals("superuser@admin.com")) {
            return "redirect:/admin/dashboard";
        }
        UserDto user = this.userService.findUserDtoByEmail(userEmail);

        switch (user.getRole()) {
            case "ROLE_CUSTOMER" -> {
                return "redirect:/customer/dashboard";
            }
            case "ROLE_DRIVER" -> {
                return "redirect:/driver/dashboard";
            }
            case "ROLE_MANAGER" -> {
                return "redirect:/manager/dashboard";
            }
        }
        model.addAttribute("userType", "Guest");
        return "authentication/login";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("error", "access_denied");
        return "authentication/access_denied";
    }
}

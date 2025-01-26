package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {
    private final UsersService usersService;

    public PasswordResetController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/reset-password-request")
    public String resetPasswordRequest() {
        return "reset-password-request";
    }

    @PostMapping("/reset-password-request")
    public String resetPasswordRequest(@RequestParam("email") String email, Model model) {
        Users user = this.usersService.findUserByEmail(email);
        if(user==null){
            model.addAttribute("error", "No user found with this email.");
            return "reset-password-request";
        }

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusHours(1));
        this.usersService.
    }

}

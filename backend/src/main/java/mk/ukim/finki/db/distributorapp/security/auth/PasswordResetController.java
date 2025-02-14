package mk.ukim.finki.db.distributorapp.security.auth;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.token.dto.TokenDto;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;
import mk.ukim.finki.db.distributorapp.token.TokenType;
import mk.ukim.finki.db.distributorapp.token.TokenRepository;
import mk.ukim.finki.db.distributorapp.security.EmailService;
import mk.ukim.finki.db.distributorapp.security.PassEncryptionPasswordEncoder;
import mk.ukim.finki.db.distributorapp.users.UsersService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reset-password")
public class PasswordResetController {
    private final UsersService usersService;
    private final EmailService emailService;
    private final PassEncryptionPasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;


    @GetMapping("/request")
    public String resetPasswordRequest() {
        return "authentication/reset-password-request";
    }

    @PostMapping("/reset-password-request")
    public String resetPasswordRequest(@RequestParam("email") String email, Model model) {

        UsersLoadingDto user = this.usersService.findFullUserDtoByEmail(email);
        if (user == null) {
            model.addAttribute("error", "No user found with this email.");
            return "authentication/reset-password-request";
        }

        String t_value = UUID.randomUUID().toString();
        tokenRepository.create(
                t_value,
                LocalDateTime.now().plusHours(1),
                user.getUserId(),
                TokenType.TOKEN_RESET.name()
        );
        TokenDto createdToken = this.tokenRepository.findTokenByValue(t_value);

        UserDto dto = this.usersService.findUserDtoByEmail(email);
        this.usersService.edit(dto);

        String resetLink = "https://localhost:8080/reset-password?token=" + createdToken.getT_value();
        String emailBody = "Click the link to reset your password: " + resetLink;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText(emailBody);
        emailService.sendEmail(message);

        model.addAttribute("success", "A password link has been sent to your email.");
        return "authentication/reset-password-request";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String tokenValue, Model model) {

        UsersLoadingDto user = usersService.findUserByResetToken(tokenValue);
        TokenDto token = tokenRepository.findTokenByValue(tokenValue);
        if (user == null || token.getT_expiry().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "Invalid or expired token.");
            return "authentication/reset-password";
        }
        model.addAttribute("token", token);
        return "authentication/reset-password";
    }

    @PostMapping("/reset-password")
    public String handleResetPassword(
            @RequestParam("token") String tokenValue,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        UsersLoadingDto user = usersService.findUserByResetToken(tokenValue);
        TokenDto token = tokenRepository.findTokenByValue(tokenValue);
        if (user.getUserId() == null || token.getT_expiry().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "Invalid or expired token.");
            return "authentication/reset-password";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "authentication/reset-password";
        }

        user.setUserPassword(passwordEncoder.encodeWithSalt(newPassword, user.getUserSalt()));
        token.setT_validated_at(LocalDateTime.now());
        tokenRepository.edit(
                token.getT_id(),
                token.getT_value(),
                token.getT_expiry(),
                token.getUser_id(),
                token.getT_validated_at(),
                token.getT_type()
        );

        UserDto dto = this.usersService.findUserDtoByEmail(user.getUserEmail());
        this.usersService.edit(dto);

        model.addAttribute("success", "Your password has been reset successfully.");
        return "authentication/reset-password";
    }
}

package mk.ukim.finki.db.distributorapp.security.auth;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.users.Users;
import mk.ukim.finki.db.distributorapp.security.TokenType;
import mk.ukim.finki.db.distributorapp.security.TokenRepository;
import mk.ukim.finki.db.distributorapp.security.EmailService;
import mk.ukim.finki.db.distributorapp.security.PassEncryptionPasswordEncoder;
import mk.ukim.finki.db.distributorapp.security.Token;
import mk.ukim.finki.db.distributorapp.users.UsersService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

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

        Users user = this.usersService.findUserByEmail(email);
        if(user==null){
            model.addAttribute("error", "No user found with this email.");
            return "authentication/reset-password-request";
        }

        Token token = new Token(user, TokenType.TOKEN_RESET);
        tokenRepository.save(token);

        UserDto dto = this.usersService.buildDto(user);
        this.usersService.edit(dto);

        String resetLink = "https://localhost:8080/reset-password?token=" + token;
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
    public String showResetPasswordForm(@RequestParam("token") String tokenValue, Model model){

        Users user = usersService.findUserByResetToken(tokenValue);
        Token token = tokenRepository.findTokenByValue(tokenValue);
        if(user==null || token.getTokenExpiryDate().isBefore(LocalDateTime.now())){
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
            Model model){

        Users user = usersService.findUserByResetToken(tokenValue);
        Token token = tokenRepository.findTokenByValue(tokenValue);
        if (user == null || token.getTokenExpiryDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "Invalid or expired token.");
            return "authentication/reset-password";
        }

        if(!newPassword.equals(confirmPassword)){
            model.addAttribute("error", "Passwords do not match.");
            return "authentication/reset-password";
        }

        user.setUserPassword(passwordEncoder.encodeWithSalt(newPassword,user.getUserSalt()));
        token.setTokenValidatedAt(LocalDateTime.now());
        tokenRepository.save(token);

        UserDto dto = this.usersService.buildDto(user);
        this.usersService.edit(dto);

        model.addAttribute("success", "Your password has been reset successfully.");
        return "authentication/reset-password";
    }
}

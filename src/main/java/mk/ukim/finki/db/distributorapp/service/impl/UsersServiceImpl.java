package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.encryption.PassEncryption;
import mk.ukim.finki.db.distributorapp.model.Users;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.db.distributorapp.model.sec.ConfirmationToken;
import mk.ukim.finki.db.distributorapp.model.sec.EmailService;
import mk.ukim.finki.db.distributorapp.repository.ConfirmationTokenRepository;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    public UsersServiceImpl(UsersRepository usersRepository, ConfirmationTokenRepository confirmationTokenRepository, EmailService emailService) {
        this.usersRepository = usersRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
    }

    @Override
    public String getUserSalt(String username) {
        return usersRepository.findUserByUserName(username).get().getUser_salt();
    }

    @Override
    public Users login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        String secPassword = PassEncryption.generateSecurePassword(password,usersRepository.findUserByUserName(username).get().getUser_salt());

        return usersRepository.findUserByUserNameAndUserPassword(username,secPassword).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public Users register(String name, String surname, String email, String password, String mobile, String image) {
        String saltValue = PassEncryption.genSaltValue(30);
        String safePass = PassEncryption.generateSecurePassword(password, saltValue);
        Users user = new Users(name,surname,safePass, email, mobile, saltValue, false, image);
        user.setUser_salt(saltValue);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText(("To confirm your account, please click here: " +
                "https://localhost:8080/register/confirm-account?token="+confirmationToken.getConfirmationToken()));
        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        return this.usersRepository.create(name, surname, safePass, email, mobile, saltValue, false, image);
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = this.confirmationTokenRepository.findConfirmationTokenByToken(confirmationToken);

        if(token != null) {
            Users user = this.usersRepository.findUserByUserEmailIgnoreCase(token.getUser().getUser_email()).get();
//            this.usersRepository.edit(user);
            return ResponseEntity.ok("Email verified successfully!");
        }

        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}

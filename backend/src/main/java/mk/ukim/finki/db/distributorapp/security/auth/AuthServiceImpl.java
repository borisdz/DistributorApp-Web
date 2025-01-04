package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.entities.City;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.db.distributorapp.repository.ConfirmationTokenRepository;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import mk.ukim.finki.db.distributorapp.security.ConfirmationToken;
import mk.ukim.finki.db.distributorapp.security.EmailService;
import mk.ukim.finki.db.distributorapp.security.PassEncryption;
import mk.ukim.finki.db.distributorapp.security.PassEncryptionPasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;
    private final PassEncryptionPasswordEncoder passwordEncoder;

    public AuthServiceImpl(UsersRepository usersRepository, ConfirmationTokenRepository confirmationTokenRepository, EmailService emailService, PassEncryptionPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String getUserSalt(String username) {
        return usersRepository.findUserByUserName(username).get().getUserSalt();
    }

    @Override
    public Optional<Users> getUserByEmail(String email) {
        return this.usersRepository.findUserByUserEmailIgnoreCase(email);
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = this.confirmationTokenRepository.findConfirmationTokenByToken(confirmationToken);

        if (token != null) {
            Users user = this.usersRepository.findUserByUserEmailIgnoreCase(token.getUser().getUserEmail()).get();
//            this.usersRepository.edit(user);
            return ResponseEntity.ok("Email verified successfully!");
        }

        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    public Users register(String name, String surname, String email, String password, String mobile, String image, City city) {
        String saltValue = PassEncryption.genSaltValue(30);
        String safePass = passwordEncoder.encodeWithSalt(password, saltValue);

        this.usersRepository.create(name, surname, safePass, email, mobile, saltValue, false, image, city.getCityId());
        Users user = this.usersRepository.findUserByUserEmailIgnoreCase(email).orElseThrow(InvalidUserCredentialsException::new);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText(("To confirm your account, please click here: " +
                "https://localhost:8080/register/confirm-account?token=" + confirmationToken.getConfirmationToken()));
        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        return user;
    }

    @Override
    public Users login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        String secPassword = passwordEncoder.encodeWithSalt(password, usersRepository.findUserByUserName(username).get().getUserSalt());

        return usersRepository.findUserByUserNameAndUserPassword(username, secPassword).orElseThrow(InvalidUserCredentialsException::new);
    }
}

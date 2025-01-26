package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp.model.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.model.enumerations.Role;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.db.distributorapp.repository.ConfirmationTokenRepository;
import mk.ukim.finki.db.distributorapp.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;

    public AuthServiceImpl(UsersRepository usersRepository, ConfirmationTokenRepository confirmationTokenRepository, EmailService emailService, PassEncryptionPasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
        this.usersRepository = usersRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
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
    public void register(RegisterRequestDto registerRequest) {
        String saltValue = PassEncryption.genSaltValue(30);
        String safePass = passwordEncoder.encodeWithSalt(registerRequest.getPassword(), saltValue);

        this.usersRepository.create(
                "'"+registerRequest.getName()+"'",
                "'"+registerRequest.getSurname()+"'",
                "'"+safePass+"'",
                "'"+registerRequest.getEmail()+"'",
                "'"+registerRequest.getMobile()+"'",
                "'"+saltValue+"'",
                false,
                null,
                registerRequest.getCity(),
                Role.ROLE_CUSTOMER.name(),
                null,
                null);

        Users user = this.usersRepository.findUserByUserEmailIgnoreCase(registerRequest.getEmail()).orElseThrow(InvalidUserCredentialsException::new);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registerRequest.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText(("To confirm your account, please click here: " +
                "https://localhost:8080/register/confirm-account?token=" + confirmationToken.getConfirmationToken()));
        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        this.customerRepository.create(user.getUserId(), registerRequest.getEdb(), registerRequest.getName(), registerRequest.getAddress(),registerRequest.getProfileImage());
    }

    @Override
    public Users login(LoginRequestDto loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty() || loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new InvalidArgumentsException();
        }

        String secPassword = passwordEncoder.encodeWithSalt
                (
                        loginRequest.getPassword(),
                        usersRepository.findUserByUserName(loginRequest.getEmail()).get().getUserSalt()
                );

        return usersRepository.findUserByUserNameAndUserPassword(loginRequest.getEmail(), secPassword).orElseThrow(InvalidUserCredentialsException::new);
    }
}

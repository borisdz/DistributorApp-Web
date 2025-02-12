package mk.ukim.finki.db.distributorapp.security.auth;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.customer.CustomerRepository;
import mk.ukim.finki.db.distributorapp.driver.DriverRepository;
import mk.ukim.finki.db.distributorapp.driver.dto.CreateDriverDto;
import mk.ukim.finki.db.distributorapp.manager.ManagerRepository;
import mk.ukim.finki.db.distributorapp.manager.dto.CreateManagerDto;
import mk.ukim.finki.db.distributorapp.security.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp.security.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.users.Users;
import mk.ukim.finki.db.distributorapp.users.Role;
import mk.ukim.finki.db.distributorapp.security.TokenType;
import mk.ukim.finki.db.distributorapp.exceptions.InvalidArgumentsException;
import mk.ukim.finki.db.distributorapp.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.db.distributorapp.security.*;
import mk.ukim.finki.db.distributorapp.users.UsersRepository;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final PassEncryptionPasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final DriverRepository driverRepository;

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
        Token token = this.tokenRepository.findTokenByValue(confirmationToken);

        if (token != null) {
            Users user = this.usersRepository.findUserByUserEmailIgnoreCase(token.getUser().getUserEmail()).get();
            UserDto userDto = this.usersRepository.findUserDtoByEmail(token.getUser().getUserEmail());
            userDto.setUserActive(true);
//            this.usersRepository.edit(userDto);
            token.setTokenValidatedAt(LocalDateTime.now());
            this.tokenRepository.save(token);
            return ResponseEntity.ok("Email verified successfully!");
        }

        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    @Transactional
    public void register(RegisterRequestDto registerRequest) throws Exception {
        String saltValue = PassEncryption.genSaltValue(30);
        String safePass = passwordEncoder.encodeWithSalt(registerRequest.getPassword(), saltValue);

        Integer res = this.usersRepository.create(
                registerRequest.getName(),
                registerRequest.getSurname(),
                safePass,
                registerRequest.getEmail(),
                registerRequest.getMobile(),
                saltValue,
                false,
                null,
                registerRequest.getCity(),
                Role.ROLE_CUSTOMER.name(),
                null,
                null,
                "CUSTOMER");
        if (res == 0) {
            throw new Exception("User insertion failed");
        }

        Users user = this.usersRepository.findUserByUserEmailIgnoreCase(registerRequest.getEmail()).orElseThrow(InvalidUserCredentialsException::new);

        Token token = new Token(user, TokenType.TOKEN_VERIFICATION);
        tokenRepository.save(token);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registerRequest.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText(("To confirm your account, please click here: " +
                "https://localhost:8080/register/confirm-account?token=" + token.getTokenValue()));
        System.out.println("Confirmation Token: " + token.getTokenValue());
        emailService.sendEmail(mailMessage);

        this.customerRepository.create(
                user.getUserId(),
                registerRequest.getEdb(),
                registerRequest.getName(),
                registerRequest.getAddress(),
                registerRequest.getProfileImage());
    }

    @Override
    @Transactional
    public void createManager(CreateManagerDto createManagerDto) throws Exception {
        String saltValue = PassEncryption.genSaltValue(30);
        String safePass = passwordEncoder.encodeWithSalt(createManagerDto.getPassword(), saltValue);

        Integer res = this.usersRepository.create(
                createManagerDto.getName(),
                createManagerDto.getSurname(),
                safePass,
                createManagerDto.getEmail(),
                createManagerDto.getMobile(),
                saltValue,
                false,
                null,
                createManagerDto.getCity(),
                Role.ROLE_MANAGER.name(),
                null,
                null,
                "MANAGER");

        if (res == 0) {
            throw new Exception("User insertion failed");
        }

        Users user = this.usersRepository.findUserByUserEmailIgnoreCase(createManagerDto.getEmail()).orElseThrow(InvalidUserCredentialsException::new);
        this.managerRepository.create(
                user.getUserId(),
                createManagerDto.getWarehouseId()
        );
    }

    @Override
    @Transactional
    public void createDriver(CreateDriverDto createDriverDto) throws Exception{
        String saltValue = PassEncryption.genSaltValue(30);
        String safePass = passwordEncoder.encodeWithSalt(createDriverDto.getPassword(), saltValue);

        Integer res = this.usersRepository.create(
                createDriverDto.getName(),
                createDriverDto.getSurname(),
                safePass,
                createDriverDto.getEmail(),
                createDriverDto.getMobile(),
                saltValue,
                false,
                null,
                createDriverDto.getCity(),
                Role.ROLE_DRIVER.name(),
                null,
                null,
                "DRIVER");

        if (res == 0) {
            throw new Exception("User insertion failed");
        }

        Users user = this.usersRepository.findUserByUserEmailIgnoreCase(createDriverDto.getEmail()).orElseThrow(InvalidUserCredentialsException::new);
        this.driverRepository.create(
                user.getUserId(),
                createDriverDto.getVehicle()
        );
    }

    @Override
    @Transactional
    public Users login(LoginRequestDto loginRequest) {
        if (loginRequest.getEmail() == null
                || loginRequest.getEmail().isEmpty()
                || loginRequest.getPassword() == null
                || loginRequest.getPassword().isEmpty()) {

            throw new InvalidArgumentsException();
        }

        Users user = this.usersRepository.findUsersByUserEmailIgnoreCase(loginRequest.getEmail())
                .orElseThrow(InvalidUserCredentialsException::new);

        String secPassword = passwordEncoder
                .encodeWithSalt(loginRequest.getPassword(), user.getUserSalt());

        if (!secPassword.equals(user.getPassword())) {
            throw new InvalidUserCredentialsException();
        }

        return user;
    }
}

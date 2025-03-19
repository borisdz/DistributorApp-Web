package mk.ukim.finki.db.distributorapp._security.auth;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.customer.CustomerRepository;
import mk.ukim.finki.db.distributorapp.driver.DriverRepository;
import mk.ukim.finki.db.distributorapp.driver.dto.CreateDriverDto;
import mk.ukim.finki.db.distributorapp.manager.ManagerRepository;
import mk.ukim.finki.db.distributorapp.manager.dto.CreateManagerDto;
import mk.ukim.finki.db.distributorapp.token.TokenRepository;
import mk.ukim.finki.db.distributorapp.users.Role;
import mk.ukim.finki.db.distributorapp.token.TokenType;
import mk.ukim.finki.db.distributorapp._exceptions.InvalidArgumentsException;
import mk.ukim.finki.db.distributorapp._exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.db.distributorapp._security.EmailService;
import mk.ukim.finki.db.distributorapp._security.PassEncryption;
import mk.ukim.finki.db.distributorapp._security.PassEncryptionPasswordEncoder;
import mk.ukim.finki.db.distributorapp._security.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp._security.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.token.dto.TokenDto;
import mk.ukim.finki.db.distributorapp.users.UserRepository;
import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final PassEncryptionPasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final DriverRepository driverRepository;

    @Override
    @Transactional
    public void register(RegisterRequestDto registerRequest) throws Exception {
        String saltValue = PassEncryption.genSaltValue(30);
        String safePass = passwordEncoder.encodeWithSalt(registerRequest.getPassword(), saltValue);

        Integer res = this.userRepository.create(
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
                "CUSTOMER");
        if (res == 0) {
            throw new Exception("User insertion failed");
        }

        UserDto user = this.userRepository.findUserDtoByEmail(registerRequest.getEmail());
        String t_value = UUID.randomUUID().toString();
        tokenRepository.create(
                t_value,
                LocalDateTime.now().plusHours(1),
                user.getId(),
                TokenType.TOKEN_VERIFICATION.name()
        );

        TokenDto createdToken = this.tokenRepository.findTokenByValue(t_value);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registerRequest.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText(("To confirm your account, please click here: " +
                "https://localhost:8080/register/confirm-account?token=" + createdToken.getT_value()));
        System.out.println("Confirmation Token: " + createdToken.getT_value());
        emailService.sendEmail(mailMessage);

        this.customerRepository.create(
                user.getId(),
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

        Integer res = this.userRepository.create(
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
                "MANAGER");

        if (res == 0) {
            throw new Exception("User insertion failed");
        }

        UserDto user = this.userRepository.findUserDtoByEmail(createManagerDto.getEmail());
        this.managerRepository.create(
                user.getId(),
                createManagerDto.getWarehouseId()
        );
    }

    @Override
    @Transactional
    public void createDriver(CreateDriverDto createDriverDto) throws Exception {
        String saltValue = PassEncryption.genSaltValue(30);
        String safePass = passwordEncoder.encodeWithSalt(createDriverDto.getPassword(), saltValue);

        Integer res = this.userRepository.create(
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
                "DRIVER");

        if (res == 0) {
            throw new Exception("User insertion failed");
        }

        UserDto user = this.userRepository.findUserDtoByEmail(createDriverDto.getEmail());
        this.driverRepository.create(
                user.getId(),
                createDriverDto.getVehicle()
        );
    }

    @Override
    @Transactional
    public UsersLoadingDto login(LoginRequestDto loginRequest) {
        if (loginRequest.getEmail() == null
                || loginRequest.getEmail().isEmpty()
                || loginRequest.getPassword() == null
                || loginRequest.getPassword().isEmpty()) {

            throw new InvalidArgumentsException();
        }

        UsersLoadingDto user = this.userRepository.findUsersByUserEmailIgnoreCaseDto(loginRequest.getEmail());

        String secPassword = passwordEncoder
                .encodeWithSalt(loginRequest.getPassword(), user.getUserSalt());

        if (!secPassword.equals(user.getUserPassword())) {
            throw new InvalidUserCredentialsException();
        }

        return user;
    }
}

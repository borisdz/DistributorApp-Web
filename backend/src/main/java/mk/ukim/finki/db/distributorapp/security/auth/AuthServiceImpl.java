package mk.ukim.finki.db.distributorapp.security.auth;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.*;
import mk.ukim.finki.db.distributorapp.model.enumerations.Role;
import mk.ukim.finki.db.distributorapp.model.enumerations.TokenType;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.db.distributorapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.db.distributorapp.repository.*;
import mk.ukim.finki.db.distributorapp.security.EmailService;
import mk.ukim.finki.db.distributorapp.security.PassEncryption;
import mk.ukim.finki.db.distributorapp.security.PassEncryptionPasswordEncoder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

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
                "CUSTOMER");
        if (res == 0) {
            throw new Exception("User insertion failed");
        }

        UserDto user = this.usersRepository.findUserDtoByEmail(registerRequest.getEmail());
        String t_value = UUID.randomUUID().toString();
        tokenRepository.create(
                t_value,
                LocalDateTime.now().plusHours(1),
                user.getId(),
                TokenType.TOKEN_VERIFICATION.name()
        );

        TokenDto createdToken = this .tokenRepository.findTokenByValue(t_value);

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
                "MANAGER");

        if (res == 0) {
            throw new Exception("User insertion failed");
        }

        UserDto user = this.usersRepository.findUserDtoByEmail(createManagerDto.getEmail());
        this.managerRepository.create(
                user.getId(),
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
                "DRIVER");

        if (res == 0) {
            throw new Exception("User insertion failed");
        }

        UserDto user = this.usersRepository.findUserDtoByEmail(createDriverDto.getEmail());
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

        UsersLoadingDto user = this.usersRepository.findUsersByUserEmailIgnoreCaseDto(loginRequest.getEmail());

        String secPassword = passwordEncoder
                .encodeWithSalt(loginRequest.getPassword(), user.getUserSalt());

        if (!secPassword.equals(user.getUserPassword())) {
            throw new InvalidUserCredentialsException();
        }

        return user;
    }
}

package mk.ukim.finki.db.distributorapp._security.auth;

import mk.ukim.finki.db.distributorapp.driver.dto.CreateDriverDto;
import mk.ukim.finki.db.distributorapp.manager.dto.CreateManagerDto;
import mk.ukim.finki.db.distributorapp._security.dto.LoginRequestDto;
import mk.ukim.finki.db.distributorapp._security.dto.RegisterRequestDto;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;

public interface AuthService {
    void register(RegisterRequestDto registerRequest) throws Exception;

    void createManager(CreateManagerDto createUserDto) throws Exception;

    void createDriver(CreateDriverDto createDriverDto) throws Exception;

    UsersLoadingDto login(LoginRequestDto loginRequest);
}

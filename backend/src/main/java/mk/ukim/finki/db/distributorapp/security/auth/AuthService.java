package mk.ukim.finki.db.distributorapp.security.auth;

import mk.ukim.finki.db.distributorapp.model.dto.*;

public interface AuthService {
    void register(RegisterRequestDto registerRequest) throws Exception;

    void createManager(CreateManagerDto createUserDto) throws Exception;

    void createDriver(CreateDriverDto createDriverDto) throws Exception;

    UsersLoadingDto login(LoginRequestDto loginRequest);
}

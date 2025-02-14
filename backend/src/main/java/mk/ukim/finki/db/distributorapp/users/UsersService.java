package mk.ukim.finki.db.distributorapp.users;

import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;

public interface UsersService {
    Integer edit(UserDto userDto);

    void deleteUserById(Long id);

    UsersLoadingDto findUserByResetToken(String token);

    UserDto findUserDtoByEmail(String userEmail);

    UsersLoadingDto findFullUserDtoByEmail(String userEmail);
}
package mk.ukim.finki.db.distributorapp.users;

import mk.ukim.finki.db.distributorapp.users.dto.UserDto;
import mk.ukim.finki.db.distributorapp.users.dto.UsersLoadingDto;

public interface UserService {
    Integer edit(UserDto userDto);

    void deleteUserById(Long id);

    UsersLoadingDto findUserByResetToken(String token);

    UserDto findUserDtoByEmail(String userEmail);

    UsersLoadingDto findFullUserDtoByEmail(String userEmail);

    void updateUser(Long id, String firstName, String lastName, String email, String phone, String image, Integer cityId);

    void updateUserDetails(Long id, String firstName, String lastName, String email, String phone, Integer cityId);
}
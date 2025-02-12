package mk.ukim.finki.db.distributorapp.users;

import mk.ukim.finki.db.distributorapp.users.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<Users> findAllUsers();
    Users findUserById(Long id);
    Users findUserByEmail(String email);
    Integer edit(UserDto userDto);
    Integer deleteUserById(Long id);
    UserDto buildDto(Users user);
    Users findUserByResetToken(String token);
    UserDto findUserDtoByEmail(String userEmail);
}

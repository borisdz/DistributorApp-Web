package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;

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

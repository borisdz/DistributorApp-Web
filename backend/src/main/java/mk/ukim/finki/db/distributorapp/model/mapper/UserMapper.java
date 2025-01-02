package mk.ukim.finki.db.distributorapp.model.mapper;

import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;

public class UserMapper {
    public static UserDto toUserDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setFirstName(userDto.getFirstName());
        userDto.setLastName(userDto.getLastName());
        userDto.setEmail(userDto.getEmail());
        return userDto;
    }
}

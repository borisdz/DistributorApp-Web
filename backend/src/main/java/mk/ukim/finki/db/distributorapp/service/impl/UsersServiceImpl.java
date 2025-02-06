package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.model.entities.Users;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UserDto buildDto(Users user){
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getUserSurname(),
                user.getUserEmail(),
                user.getUserMobile(),
                user.getUserImage(),
                user.getCity().getCityId(),
                user.getCity().getCityName(),
                user.getCity().getRegion().getRegionName(),
                user.getUserRole(),
                user.getUserResetToken(),
                user.getUserResetTokenExpiry(),
                user.getClazz_());
    }

    @Override
    public List<Users> findAllUsers() {
        return this.usersRepository.listAll();
    }

    @Override
    public Users findUserById(Long id) {
        return this.usersRepository.findById(id).get();
    }

    @Override
    public Users findUserByEmail(String email) {
        return this.usersRepository.findUserByUserEmailIgnoreCase(email).get();
    }

    @Override
    public Integer edit(UserDto userDto) {
        Users user = this.usersRepository.findUserByUserEmailIgnoreCase(userDto.getEmail()).get();
        return this.usersRepository.edit(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                user.getUserPassword(),
                userDto.getEmail(),
                userDto.getPhone(),
                user.getUserSalt(),
                user.getUserActive(),
                userDto.getImage(),
                userDto.getCityId(),
                userDto.getRole().name(),
                userDto.getRtoken(),
                userDto.getRtoken_exp(),
                userDto.getClazz_()
        );
    }

    @Override
    public Users findUserByResetToken(String token) {
        return this.usersRepository.findUserByResetToken("'"+token+"'").get();
    }

    @Override
    public Integer deleteUserById(Long id) {
        return 0;
    }
}

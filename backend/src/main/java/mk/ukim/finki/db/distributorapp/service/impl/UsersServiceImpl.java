package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.UserDto;
import mk.ukim.finki.db.distributorapp.model.dto.UsersLoadingDto;
import mk.ukim.finki.db.distributorapp.repository.UsersRepository;
import mk.ukim.finki.db.distributorapp.service.UsersService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public Integer edit(UserDto userDto) {
        UsersLoadingDto user = this.usersRepository.findUsersByUserEmailIgnoreCaseDto(userDto.getEmail());
        return this.usersRepository.edit(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                user.getUserPassword(),
                userDto.getEmail(),
                userDto.getPhone(),
                user.getUserSalt(),
                userDto.getUserActive(),
                userDto.getImage(),
                userDto.getCityId(),
                userDto.getRole(),
                userDto.getClazz_()
        );
    }

    @Override
    public UsersLoadingDto findUserByResetToken(String token) {
        return this.usersRepository.findUserByResetToken(token);
    }

    @Override
    public UserDto findUserDtoByEmail(String userEmail) {
        return this.usersRepository.findUserDtoByEmail(userEmail);
    }

    @Override
    public UsersLoadingDto findFullUserDtoByEmail(String userEmail) {
        return this.usersRepository.findUsersByUserEmailIgnoreCaseDto(userEmail);
    }

    @Override
    public void deleteUserById(Long id) {
        this.usersRepository.delete(id);
    }
}
